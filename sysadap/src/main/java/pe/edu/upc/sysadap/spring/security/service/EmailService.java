package pe.edu.upc.sysadap.spring.security.service;

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

import org.apache.commons.lang.StringUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;

import javax.imageio.ImageIO;
import javax.mail.internet.MimeMessage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;
    
    @Autowired
    private ApoderadoService apoderadoService;
    
    @Autowired
    private PersonaService personaService;
    
    @Autowired
    private ClaseAlumnoService claseAlumnoService;
    
    @Autowired
    private ClaseAlumnoActividadesService claseAlumnoActividadesService;
    
    @Autowired
    private EstiloAlumnoService estiloAlumnoService;
    
    @Autowired
    private CompetenciaService competenciaService;
    
    public void sendEmail(Mail mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = templateEngine.process("email/email-template", context);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

            emailSender.send(message);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public void sendEmail() {
    	Date fecha = new Date();
		LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int month = localDate.getMonthValue();
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
        				System.out.println(alumnoPer.getName());
        				
        				System.out.println("month: "+ month);
        				System.out.println("year: "+ year);
        				System.out.println("periodo: "+ periodo);
        				
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
            						if(actividad.getActividad() == actividadAl.getActividad())
            							exists = true;
    							}
        						if(!exists)
        							competenciasArray.add(actividadAl);
							}
        					
        					claseAlumnoActividades.addAll(claseAlumnoActividadesService.findByIdClasealumno(claseAlumno.getId()));
						}
        				System.out.println("Competencias");
        				for (Actidad actividad : competenciasArray) {
							System.out.println(actividad.getActividad());
							Competencia competencia = competenciaService.findById(actividad.getActividad());
							if(competencia !=null)
								competenciasLst.add(competencia);
						}
        				
        				Collections.sort(claseAlumnoActividades, ClaseAlumnoActividades.claseAlumnoActividadesComparator);
    					Long id_recurso = 0L;
    		    		Double sum = new Double(0);
    		    		int cant = 0;
    		    		boolean reinicio = false;
    		    		for (ClaseAlumnoActividades actividad : claseAlumnoActividades) {
    		    			
    		    			if(id_recurso.equals(0L)) {
    		    				id_recurso = actividad.getId_recurso();
    		    			}
    		    			
    		    			if(id_recurso.compareTo(actividad.getId_recurso()) == 0) {
    		    				cant++;
    		    				sum += actividad.getValor()!=null?actividad.getValor():new Double(0);
    		    				reinicio = true;
    		    			}else {
    		    				Double prom = !sum.equals(new Double(0)) && cant != 0? sum/cant:0;
    		    				promedioClase = new HashMap<String, Object>();
    		    				promedioClase.put("index", id_recurso);
    		    				switch (id_recurso.intValue()+"") {
								case "1":
									promedioClase.put("rec", "Cuaderno de Dibujo");
									break;
								case "2":
									promedioClase.put("rec", "Reproductor de sonido");
									break;
								case "3":
									promedioClase.put("rec", "Telefono");
									break;
								case "4":
									promedioClase.put("rec", "Pelota");
									break;
								case "5":
									promedioClase.put("rec", "Puzzle");
									break;
								case "6":
									promedioClase.put("rec", "Flores");
									break;
								case "7":
									promedioClase.put("rec", "Libro de animaciones");
									break;
								case "8":
									promedioClase.put("rec", "Castillo");
									break;
								case "9":
									promedioClase.put("rec", "Plastilina");
									break;
								case "10":
									promedioClase.put("rec", "Cubos");
									break;
								case "11":
									promedioClase.put("rec", "Papel de seda");
									break;
								case "12":
									promedioClase.put("rec", "Tambor");
									break;
								}
    		    				promedioClase.put("value", round((prom*100), 2));
    		    				listPromedio.add(promedioClase);
    		    				id_recurso = actividad.getId_recurso();
    		    				cant=1;
    		    				reinicio = false;
    		    				sum = actividad.getValor()!=null?actividad.getValor():new Double(0);
    		    			}
    					} 
    		    		
    		    		if(reinicio || cant==1) {
    		    			Double prom = !sum.equals(new Double(0)) && cant != 0? sum/cant:0;
    		    			promedioClase = new HashMap<String, Object>();
    						promedioClase.put("index", id_recurso);
    						switch (id_recurso.intValue()+"") {
							case "1":
								promedioClase.put("rec", "Cuaderno de Dibujo");
								break;
							case "2":
								promedioClase.put("rec", "Reproductor de sonido");
								break;
							case "3":
								promedioClase.put("rec", "Telefono");
								break;
							case "4":
								promedioClase.put("rec", "Pelota");
								break;
							case "5":
								promedioClase.put("rec", "Puzzle");
								break;
							case "6":
								promedioClase.put("rec", "Flores");
								break;
							case "7":
								promedioClase.put("rec", "Libro de animaciones");
								break;
							case "8":
								promedioClase.put("rec", "Castillo");
								break;
							case "9":
								promedioClase.put("rec", "Plastilina");
								break;
							case "10":
								promedioClase.put("rec", "Cubos");
								break;
							case "11":
								promedioClase.put("rec", "Papel de seda");
								break;
							case "12":
								promedioClase.put("rec", "Tambor");
								break;
							}
    						promedioClase.put("value", round((prom*100), 2));
    						listPromedio.add(promedioClase);
    		    		}
    		    		
    		    		for (Map<String, Object> map : listPromedio) {
							System.out.println("index "+map.get("index"));
							System.out.println("rec "+map.get("rec"));
							System.out.println("value "+map.get("value"));
						}
        				
        				//Promedios de Estilos 
        				List<PromId> promedios = estiloAlumnoService.findByFechasIdByMonth(year, month, alumno.getId());
        				File BarChart = new File( "BarChart.jpeg" ); 
        				if(!promedios.isEmpty()) {
        					for (PromId promedio : promedios) {
        						System.out.println("ID alumno: "+alumno.getId());
        						System.out.println("Valor 1: "+promedio.getVal1());
        						System.out.println("Valor 2: "+promedio.getVal2());
        						System.out.println("Valor 3: "+promedio.getVal3());
        						System.out.println("Valor 4: "+promedio.getVal4());
        						System.out.println("Valor 5: "+promedio.getVal5());
        						System.out.println("Valor 6: "+promedio.getVal6());
        						System.out.println("Valor 7: "+promedio.getVal7());
        						System.out.println("Valor 8: "+promedio.getVal8());
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
        				
        				Mail mail = new Mail();
        	            mail.setFrom("no-reply@sistemadaptativo.com");
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
//        	            context.setVariable("imageResourceName2", "img2.jpg");
        	            String html = templateEngine.process("email/index", context);

        	            helper.setTo(mail.getTo());
        	            helper.setText(html, true);
        	            helper.setSubject(mail.getSubject());
        	            helper.setFrom(mail.getFrom());
        	            
//        	            final String fiat = "FIAT";
//        	            final String audi = "AUDI";
//        	            final String ford = "FORD";
//        	            final String speed = "Speed";
//        	            final String millage = "Millage";
//        	            final String userrating = "User Rating";
//        	            final String safety = "safety";

//        	            final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
//        	            dataset.addValue( 1.0 , fiat , speed );
//        	            dataset.addValue( 3.0 , fiat , userrating );
//        	            dataset.addValue( 5.0 , fiat , millage );
//        	            dataset.addValue( 5.0 , fiat , safety );
//
//        	            dataset.addValue( 5.0 , audi , speed );
//        	            dataset.addValue( 6.0 , audi , userrating );
//        	            dataset.addValue( 10.0 , audi , millage );
//        	            dataset.addValue( 4.0 , audi , safety );
//
//        	            dataset.addValue( 4.0 , ford , speed );
//        	            dataset.addValue( 2.0 , ford , userrating );
//        	            dataset.addValue( 3.0 , ford , millage );
//        	            dataset.addValue( 6.0 , ford , safety );

//        	            JFreeChart barChart = ChartFactory.createPieChart(
//        	               "SISTEMA ADAPTATIVO PREDICTIVO", 
//        	               "Category", "Score", 
//        	               dataset,PlotOrientation.VERTICAL, 
//        	               true, true, false);
        	            
        	            
//        	            Slice s1 = Slice.newSlice(30, Color.newColor("CACACA"), "Safari", "Apple"); 
//        	            Slice s2 = Slice.newSlice(30, Color.newColor("DF7417"), "Firefox", "Mozilla"); 
//        	            Slice s3 = Slice.newSlice(30, Color.newColor("951800"), "Chrome", "Google"); 
//        	            Slice s4 = Slice.newSlice(10, Color.newColor("01A1DB"), "Internet Explorer", "Microsoft"); 
//        	     
//        	            PieChart chart = GCharts.newPieChart(s1, s2, s3, s4); 
//        	            chart.setTitle("SISTEMA ADAPTATIVO PREDICTIVO", Color.newColor("01A1DB"), 16); 
//        	            chart.setSize(500, 200); 
//        	            chart.setThreeD(true); 
//        	            String url = chart.toURLString(); 
//        	            URL url2 = new URL(url);
//        	            Resource res = new InputStreamResource(url2.openStream());
        	            helper.addInline("img1.jpg", BarChart);
//        	            BufferedImage img = ImageIO.read(url2);
//        	            File file = new File("downloaded.jpg");
//        	            ImageIO.write(img, "jpg", file);
//        	            helper.addInline("img2.jpg", file);
//        	            helper.addAttachment("MyImageName.jpg", new ByteArrayResource(value.getBytes()));

        	            emailSender.send(message);
        			}
        		}
        		
			}
        } catch (Exception e){
            throw new RuntimeException(e);
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
        dataset.setValue("LINGÜÍSTICO- VERBAL "+valores[0]+"%", valores[0]);
        dataset.setValue("LÓGICA MATEMÁTICA "+valores[1]+"%", valores[1]);
        dataset.setValue("ESPACIAL "+valores[2]+"%", valores[2]);
        dataset.setValue("CORPORAL KINESTÉSICA "+valores[3]+"%", valores[3]);
        dataset.setValue("MUSICAL "+valores[4]+"%", valores[4]);
        dataset.setValue("INTERPERSONAL "+valores[5]+"%", valores[5]);
        dataset.setValue("INTRAPERSONAL "+valores[6]+"%", valores[6]);
        dataset.setValue("NATURALISTA "+valores[7]+"%", valores[7]);
        return dataset;
    }

}
