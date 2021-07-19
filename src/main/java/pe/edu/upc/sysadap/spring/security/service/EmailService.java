package pe.edu.upc.sysadap.spring.security.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import pe.edu.upc.sysadap.spring.security.model.Alumno;
import pe.edu.upc.sysadap.spring.security.model.Apoderado;
import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;
import pe.edu.upc.sysadap.spring.security.model.ClaseAlumnoActividades;
import pe.edu.upc.sysadap.spring.security.model.Competencia;
import pe.edu.upc.sysadap.spring.security.model.Mail;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.ClaseAlumnoActividadesRepository.Actidad;
import pe.edu.upc.sysadap.spring.security.repository.EstiloAlumnoRepository.PromId;

@Service
public class EmailService {
	
	Logger logger = LoggerFactory.getLogger(EmailService.class);
	String confidentialMarkerText = "CONFIDENTIAL";
    Marker confidentialMarker = MarkerFactory.getMarker(confidentialMarkerText);
    @Value("${accountSid}")
	private String ACCOUNT_SID;
    
    @Value("${authToken}")
	private String AUTH_TOKEN;
    
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;
    
    @Autowired
    private ApoderadoService apoderadoService;
    
    @Autowired
    private PersonaService personaService;

    @Autowired
    private ClaseService claseService;
    
    @Autowired
    private ClaseAlumnoService claseAlumnoService;
    
    @Autowired
    private ClaseAlumnoActividadesService claseAlumnoActividadesService;
    
    @Autowired
    private EstiloAlumnoService estiloAlumnoService;
    
    @Autowired
    private CompetenciaService competenciaService;
    
	private String r1= "Cuaderno de Dibujo";
	private String r2= "Reproductor de sonido";
	private String r3= "Telefono";
	private String r4= "Pelota";
	private String r5= "Puzzle";
	private String r6= "Flores";
	private String r7= "Libro de animaciones";
	private String r8= "Castillo";
	private String r9= "Plastilina";
	private String r10= "Cubos";
	private String r11= "Papel de seda";
	private String r12= "Tambor";
	private String error= "Error";
	
	@Value("${accessKey}")
	private String accessKey;

	@Value("${secretKey}")
	private String secretKey;

	@Value("${region}")
	private String region;
    
    public String html(String valueSt, Context context){
    	return templateEngine.process(valueSt, context);
    }
    
    public void sendEmail(Mail mail){
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = html("email/email-template", context);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

            emailSender.send(message);
        } catch (RuntimeException|MessagingException e){
        	logger.error(error, e);
        } 
    }
    
    public void sendEmail(){
    	Date fecha = new Date();
		LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = 5;//localDate.getMonthValue();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		int year = calendar.get(Calendar.YEAR);
		String periodo ="Periodo: "+ StringUtils.leftPad(""+month, 2, "0")+"/"+year;
    	List<Apoderado> apoderados = apoderadoService.findAll();
        try {
        	for (Apoderado apoderado : apoderados) {
        		Set<Alumno> alumnos = apoderado.getAlumnos();
        		Persona apoderadoPer =  personaService.findByIdApoderado(apoderado.getId());
        		if(!alumnos.isEmpty()) { 
        			for (Alumno alumno : alumnos) {
        				Persona alumnoPer =  personaService.findByIdAlumno(alumno.getId());
        				
        				List<ClaseAlumno> listaClaseAlumno = claseAlumnoService.findByPeriodoIdAlumno(month,year,alumno.getId());
        				Map<String, Object> promedioClase = new HashMap<String, Object>();
        				List<Map<String, Object>> listPromedio = new ArrayList<Map<String,Object>>(); 
        				List<ClaseAlumnoActividades> claseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
        				
        				List<Actidad> competenciasArray =  new ArrayList<Actidad>();
        				List<Competencia> competenciasLst = new ArrayList<Competencia>();
        				
        				for (ClaseAlumno claseAlumno : listaClaseAlumno) {
        					List<Actidad> competencias = claseAlumnoActividadesService.findByIdClasealumnoActividad(claseAlumno.getId());
        					
        					for (Actidad actividadAl : competencias) {
        						boolean exists = false;
        						for (Actidad actividad : competenciasArray) {
            						if(actividad.getActividad().equals(actividadAl.getActividad()))
            							exists = true;
    							}
        						if(!exists)
        							competenciasArray.add(actividadAl);
							}
        					
        					claseAlumnoActividades.addAll(claseAlumnoActividadesService.findByIdClasealumno(claseAlumno.getId()));
						}
        				for (Actidad actividad : competenciasArray) {
							logger.debug(confidentialMarker,"message {}",actividad.getActividad());
							Competencia competencia = competenciaService.findById(actividad.getActividad());
							if(competencia !=null)
								competenciasLst.add(competencia);
						}
        				
        				Collections.sort(claseAlumnoActividades, ClaseAlumnoActividades.claseAlumnoActividadesComparator);
    					Long idRecurso = 0L;
    		    		Double sum = new Double(0);
    		    		int cant = 0;
    		    		boolean reinicio = false;
    		    		for (ClaseAlumnoActividades actividad : claseAlumnoActividades) {
    		    			
    		    			if(idRecurso.equals(0L)) {
    		    				idRecurso = actividad.getId_recurso();
    		    			}
    		    			
    		    			if(idRecurso.compareTo(actividad.getId_recurso()) == 0) {
    		    				cant++;
    		    				sum += actividad.getValor()!=null?actividad.getValor():new Double(0);
    		    				reinicio = true;
    		    			}else {
    		    				Double prom = !sum.equals(new Double(0)) && cant != 0? sum/cant:0;
    		    				promedioClase = new HashMap<String, Object>();
    		    				promedioClase.put("index", idRecurso);
    		    				setRecProm(idRecurso, promedioClase);
    		    				promedioClase.put("value", round((prom*100), 2));
    		    				listPromedio.add(promedioClase);
    		    				idRecurso = actividad.getId_recurso();
    		    				cant=1;
    		    				reinicio = false;
    		    				sum = actividad.getValor()!=null?actividad.getValor():new Double(0);
    		    			}
    					} 
    		    		
    		    		if(reinicio || cant==1) {
    		    			Double prom = !sum.equals(new Double(0)) && cant != 0? sum/cant:0;
    		    			promedioClase = new HashMap<String, Object>();
    						promedioClase.put("index", idRecurso);
    						setRecProm(idRecurso, promedioClase);
    						promedioClase.put("value", round((prom*100), 2));
    						listPromedio.add(promedioClase);
    		    		}
    		    		
        				//Promedios de Estilos 
        				List<PromId> promedios = estiloAlumnoService.findByFechasIdByMonth(year, month, alumno.getId());
        				File BarChart = new File( "BarChart.jpeg" ); 
        				if(!promedios.isEmpty()) {
        					for (PromId promedio : promedios) {
        						JFreeChart pieChart = ChartFactory.createPieChart3D
                	            		(periodo, createDataset(
                	            new Double[]{   promedio.getVal1()!=null?promedio.getVal1():0.0,
                	            				promedio.getVal2()!=null?promedio.getVal2():0.0,
                	            				promedio.getVal3()!=null?promedio.getVal3():0.0,
                	            				promedio.getVal4()!=null?promedio.getVal4():0.0,
                	            				promedio.getVal5()!=null?promedio.getVal5():0.0,
                	            				promedio.getVal6()!=null?promedio.getVal6():0.0,
                	            				promedio.getVal7()!=null?promedio.getVal7():0.0,
                	            				promedio.getVal8()!=null?promedio.getVal8():0.0
                	            				}), true, true, true);
                	               
                	            int width = 640;    /* Width of the image */
                	            int height = 480;   /* Height of the image */ 
                	            ChartUtilities.saveChartAsJPEG( BarChart , pieChart , width , height );
    						}
        				}
        				
        				
        				List<ClaseAlumno> listaClaseAlumnoProf = claseAlumnoService.findByPeriodo(month,year,alumno.getClase().getId());
        	    		List<Map<String, Object>> listPromedioProf = new ArrayList<Map<String,Object>>(); 
        	    		List<ClaseAlumnoActividades> claseAlumnoActividadesProf = new ArrayList<ClaseAlumnoActividades>();

        	    		List<Actidad> competenciasArrayProf =  new ArrayList<Actidad>();
        	    		List<Competencia> competenciasLstProf = new ArrayList<Competencia>();
        	    		File barChart2 = new File( "BarChart2.jpeg" );  
        	    		consultarReporte(listaClaseAlumnoProf, competenciasArrayProf, claseAlumnoActividadesProf, competenciasLstProf, listPromedioProf, year, month, alumno.getClase(), (periodo+" Clase: "+alumno.getClase().getNombre()), barChart2);
        				
        				Mail mail = new Mail();
        	            mail.setFrom("sysadapini@gmail.com"); 
        	            mail.setTo("oscarquerevalu@gmail.com");
        	            mail.setSubject("Reporte Inteligencias Multiples");

        	            Map<String, Object> model = new HashMap<>(); 
        	            model.put("token", "");
        	            model.put("user", "");
        	            model.put("alumno", alumnoPer.getName());   
        	            model.put("recursos", listPromedio);
        	            model.put("competencias", competenciasLst);
        	            model.put("apoderado", apoderadoPer.getName());
        	            model.put("clase", alumno.getClase().getNombre());
        	            
        	            model.put("signature", "https://sistemadaptativo.com"); 
        	            mail.setModel(model);
        	            MimeMessage message = emailSender.createMimeMessage();
        	            MimeMessageHelper helper = new MimeMessageHelper(message,
        	                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
        	                    StandardCharsets.UTF_8.name());

        	            Context context = new Context();
        	            context.setVariables(mail.getModel());
        	            context.setVariable("imageResourceName", "img1.jpg");
        	            context.setVariable("imageResourceName2", "img2.jpg");
        	            String html = html("email/index", context);

        	            helper.setTo(mail.getTo());
        	            helper.setText(html, true);
        	            helper.setSubject(mail.getSubject());
        	            helper.setFrom(mail.getFrom());
        	            
        	            helper.addInline("img1.jpg", BarChart);
        	            helper.addInline("img2.jpg", barChart2);

        	            emailSender.send(message);
        	            
        			}
        		}
        		
			}
        } catch (RuntimeException|IOException|MessagingException e){
        	logger.error(error, e);
        }
//        sendEmailProfesor();
        enviaNotiWp("oscarquerevalu@gmail.com", "941103487");
        enviaNotiSms("oscarquerevalu@gmail.com", "941103487");
        enviaNotiWp("oscarquerevalu@gmail.com", "952937072");
        enviaNotiSms("oscarquerevalu@gmail.com", "952937072");
    }
    
    public void sendEmailProfesor(){
    	Date fecha = new Date();
//    	LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//    	int month = localDate.getMonthValue();
    	LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = 5;//localDate.getMonthValue();
    	Calendar calendar = new GregorianCalendar();
    	calendar.setTime(fecha);
    	int year = calendar.get(Calendar.YEAR);
    	String periodo ="Periodo: "+ StringUtils.leftPad(""+month, 2, "0")+"/"+year;
    	try {
    		List<Clase> clases = claseService.findByAll();
    		if(!clases.isEmpty()) {
    			for (Clase clase : clases) {
    	    		List<ClaseAlumno> listaClaseAlumnoProf = claseAlumnoService.findByPeriodo(month,year,clase.getId());
    	    		List<Map<String, Object>> listPromedioProf = new ArrayList<Map<String,Object>>(); 
    	    		List<ClaseAlumnoActividades> claseAlumnoActividadesProf = new ArrayList<ClaseAlumnoActividades>();

    	    		List<Actidad> competenciasArrayProf =  new ArrayList<Actidad>();
    	    		List<Competencia> competenciasLstProf = new ArrayList<Competencia>();
    	    		File barChart = new File( "BarChart.jpeg" ); 
    	    		consultarReporte(listaClaseAlumnoProf, competenciasArrayProf, claseAlumnoActividadesProf, competenciasLstProf, listPromedioProf, year, month, clase, periodo, barChart);
    	    		for (Alumno alumno : clase.getAlumnos()) {
						Persona persona = personaService.findByIdAlumno(alumno.getId());
						logger.debug(confidentialMarker,"Alumno: {}", persona.getName());
						alumno.setPersona(persona);
					}
    	    		
    	    		Persona profesor = personaService.findByIdAlumno(clase.getProfesor().getId());

    	    		Mail mail = new Mail();
    	    		mail.setFrom("sysadapini@gmail.com");
    	    		mail.setTo("oscarquerevalu@gmail.com");
    	    		mail.setSubject("Reporte Inteligencias Multiples");

    	    		Map<String, Object> model = new HashMap<>();
    	    		model.put("token", "");
    	    		model.put("user", "");
    	    		model.put("profesor", profesor.getName());
    	    		model.put("alumnos", clase.getAlumnos());
    	    		model.put("recursos", listPromedioProf);
    	    		model.put("competencias", competenciasLstProf);
    	    		model.put("clase", clase.getNombre());

    	    		model.put("signature", "https://sistemadaptativo.com");
    	    		mail.setModel(model);
    	    		MimeMessage message = emailSender.createMimeMessage();
    	    		MimeMessageHelper helper = new MimeMessageHelper(message,
    	    				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
    	    				StandardCharsets.UTF_8.name());

    	    		Context context = new Context();
    	    		context.setVariables(mail.getModel());
    	    		context.setVariable("imageResourceName", "img1.jpg");
    	    		String html = html("email/email-template-clase", context);

    	    		helper.setTo(mail.getTo());
    	    		helper.setText(html, true);
    	    		helper.setSubject(mail.getSubject());
    	    		helper.setFrom(mail.getFrom());
    	    		helper.addInline("img1.jpg", barChart);

    	    		emailSender.send(message);
				}
    		}
    		
    	} catch (RuntimeException|IOException|MessagingException e){
    		logger.error(error, e);
        }
    }
    
    private void consultarReporte(	List<ClaseAlumno> listaClaseAlumnoProf,List<Actidad> competenciasArrayProf,
    								List<ClaseAlumnoActividades> claseAlumnoActividadesProf,
    								List<Competencia> competenciasLstProf,
    								List<Map<String, Object>> listPromedioProf,
    								int year,
    								int month,
    								Clase clase,
    								String periodo,
    								File barChart) throws IOException {
    	Map<String, Object> promedioClaseProfesor = new HashMap<String, Object>();
    	for (ClaseAlumno claseAlumno : listaClaseAlumnoProf) {
			List<Actidad> competencias = claseAlumnoActividadesService.findByIdClasealumnoActividad(claseAlumno.getId());

			for (Actidad actividadAl : competencias) {
				boolean exists = false;
				for (Actidad actividad : competenciasArrayProf) {
					if(actividad.getActividad().equals(actividadAl.getActividad()))
						exists = true;
				}
				if(!exists)
					competenciasArrayProf.add(actividadAl);
			}

			claseAlumnoActividadesProf.addAll(claseAlumnoActividadesService.findByIdClasealumno(claseAlumno.getId()));
		}
		for (Actidad actividad : competenciasArrayProf) {
			logger.debug(confidentialMarker,"message {}",actividad.getActividad());
			Competencia competencia = competenciaService.findById(actividad.getActividad());
			if(competencia !=null)
				competenciasLstProf.add(competencia);
		}

		Collections.sort(claseAlumnoActividadesProf, ClaseAlumnoActividades.claseAlumnoActividadesComparator);
		Long idRecursoProf = 0L;
		Double sum = new Double(0);
		int cant = 0;
		boolean reinicioProf = false;
		for (ClaseAlumnoActividades actividad : claseAlumnoActividadesProf) {

			if(idRecursoProf.equals(0L)) {
				idRecursoProf = actividad.getId_recurso();
			}

			if(idRecursoProf.compareTo(actividad.getId_recurso()) == 0) {
				cant++;
				sum += actividad.getValor()!=null?actividad.getValor():new Double(0);
				reinicioProf = true;
			}else {
				Double prom = !sum.equals(new Double(0)) && cant != 0? sum/cant:0;
				promedioClaseProfesor = new HashMap<String, Object>();
				promedioClaseProfesor.put("index", idRecursoProf);
				setRecProm(idRecursoProf, promedioClaseProfesor);
				promedioClaseProfesor.put("value", round((prom*100), 2));
				listPromedioProf.add(promedioClaseProfesor);
				idRecursoProf = actividad.getId_recurso();
				cant=1;
				reinicioProf = false;
				sum = actividad.getValor()!=null?actividad.getValor():new Double(0);
			}
		} 

		if(reinicioProf || cant==1) {
			Double prom = !sum.equals(new Double(0)) && cant != 0? sum/cant:0;
			promedioClaseProfesor = new HashMap<String, Object>();
			promedioClaseProfesor.put("index", idRecursoProf);
			setRecProm(idRecursoProf, promedioClaseProfesor);
			promedioClaseProfesor.put("value", round((prom*100), 2));
			listPromedioProf.add(promedioClaseProfesor);
		}

		//Promedios de Estilos 
		List<PromId> promedios = estiloAlumnoService.findByPeriodo(year, month, clase.getId());
		if(!promedios.isEmpty()) {
			for (PromId promedio : promedios) {
				JFreeChart pieChart = ChartFactory.createPieChart3D
						(periodo, createDataset(
								new Double[]{   promedio.getVal1()!=null?promedio.getVal1():0.0,
										promedio.getVal2()!=null?promedio.getVal2():0.0,
												promedio.getVal3()!=null?promedio.getVal3():0.0,
														promedio.getVal4()!=null?promedio.getVal4():0.0,
																promedio.getVal5()!=null?promedio.getVal5():0.0,
																		promedio.getVal6()!=null?promedio.getVal6():0.0,
																				promedio.getVal7()!=null?promedio.getVal7():0.0,
																						promedio.getVal8()!=null?promedio.getVal8():0.0
								}), true, true, true);

				int width = 640;    /* Width of the image */
				int height = 480;   /* Height of the image */ 
				ChartUtilities.saveChartAsJPEG( barChart , pieChart , width , height );
			}
		}
    }
    
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    
    private DefaultPieDataset createDataset(Double[] valores) {
    	DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("LINGÜÍSTICO- VERBAL "+round(valores[0],2)+"%", valores[0]);
        dataset.setValue("LÓGICA MATEMÁTICA "+round(valores[1],2)+"%", valores[1]);
        dataset.setValue("ESPACIAL "+round(valores[2],2)+"%", valores[2]);
        dataset.setValue("CORPORAL KINESTÉSICA "+round(valores[3],2)+"%", valores[3]);
        dataset.setValue("MUSICAL "+round(valores[4],2)+"%", valores[4]);
        dataset.setValue("INTERPERSONAL "+round(valores[5],2)+"%", valores[5]);
        dataset.setValue("INTRAPERSONAL "+round(valores[6],2)+"%", valores[6]);
        dataset.setValue("NATURALISTA "+round(valores[7],2)+"%", valores[7]);
        return dataset;
    }
    
    private void setRecProm(Long idRecurso, Map<String, Object> promedioClase) {
    	switch (idRecurso.intValue()+"") {
		case "1":
			promedioClase.put("rec", r1);
			break;
		case "2":
			promedioClase.put("rec", r2);
			break;
		case "3":
			promedioClase.put("rec", r3);
			break;
		case "4":
			promedioClase.put("rec", r4);
			break;
		case "5":
			promedioClase.put("rec", r5);
			break;
		case "6":
			promedioClase.put("rec", r6);
			break;
		case "7":
			promedioClase.put("rec", r7);
			break;
		case "8":
			promedioClase.put("rec", r8);
			break;
		case "9":
			promedioClase.put("rec", r9);
			break;
		case "10":
			promedioClase.put("rec", r10);
			break;
		case "11":
			promedioClase.put("rec", r11);
			break;
		case "12":
			promedioClase.put("rec", r12);
			break;
		default:
			break;
		}
    }
    
    private void enviaNotiWp(String correo,String number) {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        Message message = Message.creator( 
                new com.twilio.type.PhoneNumber("whatsapp:+51"+number), 
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),  
                "Buenos días, le ha llegado una notificación al correo :" + correo + " con el reporte de sysadap mensual, por favor revise.")      
            .create(); 
 
        System.out.println(message.getSid());
    }
    
    private void enviaNotiSms(String correo,String number) {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        Message message = Message.creator( 
        		new com.twilio.type.PhoneNumber("+51"+number),
                "MG583ac56769bd73cb3a073fb6d839bd1e", "Buenos días, le ha llegado una notificación al correo :" + correo + " con el reporte de sysadap mensual.")     
            .create(); 

        System.out.println(message.getSid()); 
    }
    
    

}
