package pe.edu.upc.sysadap.spring.security.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import pe.edu.upc.sysadap.spring.security.model.Alumno;
import pe.edu.upc.sysadap.spring.security.model.Apoderado;
import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;
import pe.edu.upc.sysadap.spring.security.model.ClaseAlumnoActividades;
import pe.edu.upc.sysadap.spring.security.model.Competencia;
import pe.edu.upc.sysadap.spring.security.model.Mail;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.model.Profesor;
import pe.edu.upc.sysadap.spring.security.repository.ClaseAlumnoActividadesRepository.Actidad;
import pe.edu.upc.sysadap.spring.security.repository.EstiloAlumnoRepository.PromId;

public class EmailServiceTest {

	@InjectMocks 
    @Spy
	private EmailService service;
	@Mock
	private JavaMailSender emailSender;
	@Mock
	private SpringTemplateEngine templateEngine;
	@Mock
	private ApoderadoService apoderadoService;
	@Mock
	private PersonaService personaService;
	@Mock
	private ClaseAlumnoService claseAlumnoService;
	@Mock
	private ClaseAlumnoActividadesService claseAlumnoActividadesService;
	@Mock
	private CompetenciaService competenciaService;
	@Mock
	private EstiloAlumnoService estiloAlumnoService;
	@Mock
	private ClaseService claseService;
	
	
	
	Mail mail = new Mail();
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		MimeMessage message = mock(MimeMessage.class);
		when(emailSender.createMimeMessage()).thenReturn(message);		
		//Apoderados
		List<Apoderado> lstApoderados = new ArrayList<Apoderado>();
		Apoderado apoderado = new Apoderado();
		apoderado.setId(1L);
		lstApoderados.add(apoderado);
		
		//Alumnos
		Set<Alumno> alumnos = new HashSet<Alumno>();
		Alumno alumno = new Alumno();
		alumno.setId(1L);
		alumnos.add(alumno);
		
		Clase clase = new Clase();
		clase.setId(1L);
		clase.setNombre("ABC123");
		//Profesor
		Profesor profesor = new Profesor();
		profesor.setId(1L);
		clase.setProfesor(profesor);
		alumno.setClase(clase);
		apoderado.setAlumnos(alumnos);
		
		List<Clase> clases = new ArrayList<Clase>();
		clases.add(clase);
		//Persona
		Persona persona = new Persona();
		persona.setId(1L);
		persona.setName("Oscar");
		persona.setDocumento("46857939");
		
		//ClaseAlumnos
		List<ClaseAlumno> claseAlumnos = new ArrayList<ClaseAlumno>();
		ClaseAlumno claseAlumno = new ClaseAlumno();
		claseAlumno.setId(1L);
		claseAlumno.setId_alumno(1L);
		claseAlumno.setId_clase(1L);
		claseAlumno.setId_estilo(1L);
		claseAlumno.setFecha("01/01/2020");
		claseAlumnos.add(claseAlumno);
		
		//Actividades
		List<Actidad> actidades = new ArrayList<>();
		Actidad actidad = new Actidad() {
			
			@Override
			public Long getActividad() {
				
				return 1L;
			}
		};
		
		Actidad actidad2 = new Actidad() {
			
			@Override
			public Long getActividad() {
				
				return 2L;
			}
		};
		
		actidades.add(actidad);
		actidades.add(actidad2);
		
		//Competencia
		Competencia competencia = new Competencia();
		competencia.setId(1L);
		competencia.setNombre("ABC");
		
		//Promedios
		List<PromId> promids = new ArrayList<>();
		PromId promId = new PromId() {
			
			@Override
			public Double getVal8() {
				
				return 0.1;
			}
			
			@Override
			public Double getVal7() {
				
				return 0.2;
			}
			
			@Override
			public Double getVal6() {
				
				return 0.3;
			}
			
			@Override
			public Double getVal5() {
				
				return 0.4;
			}
			
			@Override
			public Double getVal4() {
				
				return 0.5;
			}
			
			@Override
			public Double getVal3() {
				
				return 0.6;
			}
			
			@Override
			public Double getVal2() {
				
				return 0.7;
			}
			
			@Override
			public Double getVal1() {
				
				return 0.8;
			}
		};
		
		promids.add(promId);
		
		doReturn("").when(service).html(Mockito.anyString(), Mockito.any(Context.class));
		when(apoderadoService.findAll()).thenReturn(lstApoderados);
		when(personaService.findByIdApoderado(Mockito.anyLong())).thenReturn(persona);
		when(personaService.findByIdAlumno(Mockito.anyLong())).thenReturn(persona);
		when(claseAlumnoService.findByPeriodoIdAlumno(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyLong())).thenReturn(claseAlumnos);
		when(claseAlumnoActividadesService.findByIdClasealumnoActividad(Mockito.anyLong())).thenReturn(actidades);
		when(competenciaService.findById(Mockito.anyLong())).thenReturn(competencia);
		when(estiloAlumnoService.findByFechasIdByMonth(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyLong())).thenReturn(promids);
		when(claseService.findByAll()).thenReturn(clases);
		when(claseAlumnoService.findByPeriodo(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyLong())).thenReturn(claseAlumnos);
		when(estiloAlumnoService.findByPeriodo(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyLong())).thenReturn(promids);
		
	}
	
	@Test
	public void testSendEmailMail() throws Exception {
		mail.setModel(new HashMap<>());
		mail.setTo("asdas");
		mail.setSubject("asdasd");
		mail.setFrom("asasd");
		service.sendEmail(mail);
	}
	@Test
	public void testSendEmailMailCath(){
		service.sendEmail(mail);
	}

	@Test
	public void testHtml() {
		String valueSt = "";
		Context context = new Context();
		assertNotNull(service.html(valueSt, context));;
	}
	
	@Test
	public void testSendEmail() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		ClaseAlumnoActividades claseAlumnoActividad8 = new ClaseAlumnoActividades();
		claseAlumnoActividad8.setId(1L);
		claseAlumnoActividad8.setId_actividad(1L);
		claseAlumnoActividad8.setId_clasealumno(1L);
		claseAlumnoActividad8.setId_recurso(7L);
		claseAlumnoActividad8.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad8);
		
		ClaseAlumnoActividades claseAlumnoActividad9 = new ClaseAlumnoActividades();
		claseAlumnoActividad9.setId(1L);
		claseAlumnoActividad9.setId_actividad(1L);
		claseAlumnoActividad9.setId_clasealumno(1L);
		claseAlumnoActividad9.setId_recurso(8L);
		claseAlumnoActividad9.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad9);
		
		ClaseAlumnoActividades claseAlumnoActividad10 = new ClaseAlumnoActividades();
		claseAlumnoActividad10.setId(1L);
		claseAlumnoActividad10.setId_actividad(1L);
		claseAlumnoActividad10.setId_clasealumno(1L);
		claseAlumnoActividad10.setId_recurso(9L);
		claseAlumnoActividad10.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad10);
		
		ClaseAlumnoActividades claseAlumnoActividad11 = new ClaseAlumnoActividades();
		claseAlumnoActividad11.setId(1L);
		claseAlumnoActividad11.setId_actividad(1L);
		claseAlumnoActividad11.setId_clasealumno(1L);
		claseAlumnoActividad11.setId_recurso(10L);
		claseAlumnoActividad11.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad11);
		
		ClaseAlumnoActividades claseAlumnoActividad12 = new ClaseAlumnoActividades();
		claseAlumnoActividad12.setId(1L);
		claseAlumnoActividad12.setId_actividad(1L);
		claseAlumnoActividad12.setId_clasealumno(1L);
		claseAlumnoActividad12.setId_recurso(11L);
		claseAlumnoActividad12.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad12);
		
		ClaseAlumnoActividades claseAlumnoActividad13 = new ClaseAlumnoActividades();
		claseAlumnoActividad13.setId(1L);
		claseAlumnoActividad13.setId_actividad(1L);
		claseAlumnoActividad13.setId_clasealumno(1L);
		claseAlumnoActividad13.setId_recurso(12L);
		claseAlumnoActividad13.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad13);
		
		ClaseAlumnoActividades claseAlumnoActividad14 = new ClaseAlumnoActividades();
		claseAlumnoActividad14.setId(1L);
		claseAlumnoActividad14.setId_actividad(1L);
		claseAlumnoActividad14.setId_clasealumno(1L);
		claseAlumnoActividad14.setId_recurso(12L);
		claseAlumnoActividad14.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad14);
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmail();
	}
	
	@Test
	public void testSendEmail2() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		ClaseAlumnoActividades claseAlumnoActividad8 = new ClaseAlumnoActividades();
		claseAlumnoActividad8.setId(1L);
		claseAlumnoActividad8.setId_actividad(1L);
		claseAlumnoActividad8.setId_clasealumno(1L);
		claseAlumnoActividad8.setId_recurso(7L);
		claseAlumnoActividad8.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad8);
		
		ClaseAlumnoActividades claseAlumnoActividad9 = new ClaseAlumnoActividades();
		claseAlumnoActividad9.setId(1L);
		claseAlumnoActividad9.setId_actividad(1L);
		claseAlumnoActividad9.setId_clasealumno(1L);
		claseAlumnoActividad9.setId_recurso(8L);
		claseAlumnoActividad9.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad9);
		
		ClaseAlumnoActividades claseAlumnoActividad10 = new ClaseAlumnoActividades();
		claseAlumnoActividad10.setId(1L);
		claseAlumnoActividad10.setId_actividad(1L);
		claseAlumnoActividad10.setId_clasealumno(1L);
		claseAlumnoActividad10.setId_recurso(9L);
		claseAlumnoActividad10.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad10);
		
		ClaseAlumnoActividades claseAlumnoActividad11 = new ClaseAlumnoActividades();
		claseAlumnoActividad11.setId(1L);
		claseAlumnoActividad11.setId_actividad(1L);
		claseAlumnoActividad11.setId_clasealumno(1L);
		claseAlumnoActividad11.setId_recurso(10L);
		claseAlumnoActividad11.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad11);
		
		ClaseAlumnoActividades claseAlumnoActividad12 = new ClaseAlumnoActividades();
		claseAlumnoActividad12.setId(1L);
		claseAlumnoActividad12.setId_actividad(1L);
		claseAlumnoActividad12.setId_clasealumno(1L);
		claseAlumnoActividad12.setId_recurso(11L);
		claseAlumnoActividad12.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad12);
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmail();
	}
	
	@Test
	public void testSendEmail3()  throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		ClaseAlumnoActividades claseAlumnoActividad8 = new ClaseAlumnoActividades();
		claseAlumnoActividad8.setId(1L);
		claseAlumnoActividad8.setId_actividad(1L);
		claseAlumnoActividad8.setId_clasealumno(1L);
		claseAlumnoActividad8.setId_recurso(7L);
		claseAlumnoActividad8.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad8);
		
		ClaseAlumnoActividades claseAlumnoActividad9 = new ClaseAlumnoActividades();
		claseAlumnoActividad9.setId(1L);
		claseAlumnoActividad9.setId_actividad(1L);
		claseAlumnoActividad9.setId_clasealumno(1L);
		claseAlumnoActividad9.setId_recurso(8L);
		claseAlumnoActividad9.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad9);
		
		ClaseAlumnoActividades claseAlumnoActividad10 = new ClaseAlumnoActividades();
		claseAlumnoActividad10.setId(1L);
		claseAlumnoActividad10.setId_actividad(1L);
		claseAlumnoActividad10.setId_clasealumno(1L);
		claseAlumnoActividad10.setId_recurso(9L);
		claseAlumnoActividad10.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad10);
		
		ClaseAlumnoActividades claseAlumnoActividad11 = new ClaseAlumnoActividades();
		claseAlumnoActividad11.setId(1L);
		claseAlumnoActividad11.setId_actividad(1L);
		claseAlumnoActividad11.setId_clasealumno(1L);
		claseAlumnoActividad11.setId_recurso(10L);
		claseAlumnoActividad11.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad11);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmail();
	}
	
	@Test
	public void testSendEmail4() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		ClaseAlumnoActividades claseAlumnoActividad8 = new ClaseAlumnoActividades();
		claseAlumnoActividad8.setId(1L);
		claseAlumnoActividad8.setId_actividad(1L);
		claseAlumnoActividad8.setId_clasealumno(1L);
		claseAlumnoActividad8.setId_recurso(7L);
		claseAlumnoActividad8.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad8);
		
		ClaseAlumnoActividades claseAlumnoActividad9 = new ClaseAlumnoActividades();
		claseAlumnoActividad9.setId(1L);
		claseAlumnoActividad9.setId_actividad(1L);
		claseAlumnoActividad9.setId_clasealumno(1L);
		claseAlumnoActividad9.setId_recurso(8L);
		claseAlumnoActividad9.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad9);
		
		ClaseAlumnoActividades claseAlumnoActividad10 = new ClaseAlumnoActividades();
		claseAlumnoActividad10.setId(1L);
		claseAlumnoActividad10.setId_actividad(1L);
		claseAlumnoActividad10.setId_clasealumno(1L);
		claseAlumnoActividad10.setId_recurso(9L);
		claseAlumnoActividad10.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad10);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmail();
	}
	
	@Test
	public void testSendEmail5() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		ClaseAlumnoActividades claseAlumnoActividad8 = new ClaseAlumnoActividades();
		claseAlumnoActividad8.setId(1L);
		claseAlumnoActividad8.setId_actividad(1L);
		claseAlumnoActividad8.setId_clasealumno(1L);
		claseAlumnoActividad8.setId_recurso(7L);
		claseAlumnoActividad8.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad8);
		
		ClaseAlumnoActividades claseAlumnoActividad9 = new ClaseAlumnoActividades();
		claseAlumnoActividad9.setId(1L);
		claseAlumnoActividad9.setId_actividad(1L);
		claseAlumnoActividad9.setId_clasealumno(1L);
		claseAlumnoActividad9.setId_recurso(8L);
		claseAlumnoActividad9.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad9);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmail();
	}
	
	@Test
	public void testSendEmail6() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		ClaseAlumnoActividades claseAlumnoActividad8 = new ClaseAlumnoActividades();
		claseAlumnoActividad8.setId(1L);
		claseAlumnoActividad8.setId_actividad(1L);
		claseAlumnoActividad8.setId_clasealumno(1L);
		claseAlumnoActividad8.setId_recurso(7L);
		claseAlumnoActividad8.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad8);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmail();
	}
	
	@Test
	public void testSendEmail7() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmail();
	}
	
	@Test
	public void testSendEmail8() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmail();
	}
	
	@Test
	public void testSendEmail9() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmail();
	}
	
	@Test
	public void testSendEmail10() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmail();
	}
	
	@Test
	public void testSendEmail11() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmail();
	}
	
	@Test
	public void testSendEmail12() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmail();
	}
	
	@Test
	public void testSendEmailProfesor() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		ClaseAlumnoActividades claseAlumnoActividad8 = new ClaseAlumnoActividades();
		claseAlumnoActividad8.setId(1L);
		claseAlumnoActividad8.setId_actividad(1L);
		claseAlumnoActividad8.setId_clasealumno(1L);
		claseAlumnoActividad8.setId_recurso(7L);
		claseAlumnoActividad8.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad8);
		
		ClaseAlumnoActividades claseAlumnoActividad9 = new ClaseAlumnoActividades();
		claseAlumnoActividad9.setId(1L);
		claseAlumnoActividad9.setId_actividad(1L);
		claseAlumnoActividad9.setId_clasealumno(1L);
		claseAlumnoActividad9.setId_recurso(8L);
		claseAlumnoActividad9.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad9);
		
		ClaseAlumnoActividades claseAlumnoActividad10 = new ClaseAlumnoActividades();
		claseAlumnoActividad10.setId(1L);
		claseAlumnoActividad10.setId_actividad(1L);
		claseAlumnoActividad10.setId_clasealumno(1L);
		claseAlumnoActividad10.setId_recurso(9L);
		claseAlumnoActividad10.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad10);
		
		ClaseAlumnoActividades claseAlumnoActividad11 = new ClaseAlumnoActividades();
		claseAlumnoActividad11.setId(1L);
		claseAlumnoActividad11.setId_actividad(1L);
		claseAlumnoActividad11.setId_clasealumno(1L);
		claseAlumnoActividad11.setId_recurso(10L);
		claseAlumnoActividad11.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad11);
		
		ClaseAlumnoActividades claseAlumnoActividad12 = new ClaseAlumnoActividades();
		claseAlumnoActividad12.setId(1L);
		claseAlumnoActividad12.setId_actividad(1L);
		claseAlumnoActividad12.setId_clasealumno(1L);
		claseAlumnoActividad12.setId_recurso(11L);
		claseAlumnoActividad12.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad12);
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmailProfesor();
	}
	
	@Test
	public void testSendEmailProfesor2() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		ClaseAlumnoActividades claseAlumnoActividad8 = new ClaseAlumnoActividades();
		claseAlumnoActividad8.setId(1L);
		claseAlumnoActividad8.setId_actividad(1L);
		claseAlumnoActividad8.setId_clasealumno(1L);
		claseAlumnoActividad8.setId_recurso(7L);
		claseAlumnoActividad8.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad8);
		
		ClaseAlumnoActividades claseAlumnoActividad9 = new ClaseAlumnoActividades();
		claseAlumnoActividad9.setId(1L);
		claseAlumnoActividad9.setId_actividad(1L);
		claseAlumnoActividad9.setId_clasealumno(1L);
		claseAlumnoActividad9.setId_recurso(8L);
		claseAlumnoActividad9.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad9);
		
		ClaseAlumnoActividades claseAlumnoActividad10 = new ClaseAlumnoActividades();
		claseAlumnoActividad10.setId(1L);
		claseAlumnoActividad10.setId_actividad(1L);
		claseAlumnoActividad10.setId_clasealumno(1L);
		claseAlumnoActividad10.setId_recurso(9L);
		claseAlumnoActividad10.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad10);
		
		ClaseAlumnoActividades claseAlumnoActividad11 = new ClaseAlumnoActividades();
		claseAlumnoActividad11.setId(1L);
		claseAlumnoActividad11.setId_actividad(1L);
		claseAlumnoActividad11.setId_clasealumno(1L);
		claseAlumnoActividad11.setId_recurso(10L);
		claseAlumnoActividad11.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad11);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmailProfesor();
	}
	
	@Test
	public void testSendEmailProfesor3() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		ClaseAlumnoActividades claseAlumnoActividad8 = new ClaseAlumnoActividades();
		claseAlumnoActividad8.setId(1L);
		claseAlumnoActividad8.setId_actividad(1L);
		claseAlumnoActividad8.setId_clasealumno(1L);
		claseAlumnoActividad8.setId_recurso(7L);
		claseAlumnoActividad8.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad8);
		
		ClaseAlumnoActividades claseAlumnoActividad9 = new ClaseAlumnoActividades();
		claseAlumnoActividad9.setId(1L);
		claseAlumnoActividad9.setId_actividad(1L);
		claseAlumnoActividad9.setId_clasealumno(1L);
		claseAlumnoActividad9.setId_recurso(8L);
		claseAlumnoActividad9.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad9);
		
		ClaseAlumnoActividades claseAlumnoActividad10 = new ClaseAlumnoActividades();
		claseAlumnoActividad10.setId(1L);
		claseAlumnoActividad10.setId_actividad(1L);
		claseAlumnoActividad10.setId_clasealumno(1L);
		claseAlumnoActividad10.setId_recurso(9L);
		claseAlumnoActividad10.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad10);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmailProfesor();
	}
	
	@Test
	public void testSendEmailProfesor4() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		ClaseAlumnoActividades claseAlumnoActividad8 = new ClaseAlumnoActividades();
		claseAlumnoActividad8.setId(1L);
		claseAlumnoActividad8.setId_actividad(1L);
		claseAlumnoActividad8.setId_clasealumno(1L);
		claseAlumnoActividad8.setId_recurso(7L);
		claseAlumnoActividad8.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad8);
		
		ClaseAlumnoActividades claseAlumnoActividad9 = new ClaseAlumnoActividades();
		claseAlumnoActividad9.setId(1L);
		claseAlumnoActividad9.setId_actividad(1L);
		claseAlumnoActividad9.setId_clasealumno(1L);
		claseAlumnoActividad9.setId_recurso(8L);
		claseAlumnoActividad9.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad9);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmailProfesor();
	}
	
	@Test
	public void testSendEmailProfesor5() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		ClaseAlumnoActividades claseAlumnoActividad8 = new ClaseAlumnoActividades();
		claseAlumnoActividad8.setId(1L);
		claseAlumnoActividad8.setId_actividad(1L);
		claseAlumnoActividad8.setId_clasealumno(1L);
		claseAlumnoActividad8.setId_recurso(7L);
		claseAlumnoActividad8.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad8);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmailProfesor();
	}
	
	@Test
	public void testSendEmailProfesor6() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		ClaseAlumnoActividades claseAlumnoActividad7 = new ClaseAlumnoActividades();
		claseAlumnoActividad7.setId(1L);
		claseAlumnoActividad7.setId_actividad(1L);
		claseAlumnoActividad7.setId_clasealumno(1L);
		claseAlumnoActividad7.setId_recurso(6L);
		claseAlumnoActividad7.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad7);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmailProfesor();
	}
	
	@Test
	public void testSendEmailProfesor7() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		ClaseAlumnoActividades claseAlumnoActividad6 = new ClaseAlumnoActividades();
		claseAlumnoActividad6.setId(1L);
		claseAlumnoActividad6.setId_actividad(1L);
		claseAlumnoActividad6.setId_clasealumno(1L);
		claseAlumnoActividad6.setId_recurso(5L);
		claseAlumnoActividad6.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad6);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmailProfesor();
	}
	
	@Test
	public void testSendEmailProfesor8() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		ClaseAlumnoActividades claseAlumnoActividad5 = new ClaseAlumnoActividades();
		claseAlumnoActividad5.setId(1L);
		claseAlumnoActividad5.setId_actividad(1L);
		claseAlumnoActividad5.setId_clasealumno(1L);
		claseAlumnoActividad5.setId_recurso(4L);
		claseAlumnoActividad5.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad5);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmailProfesor();
	}
	
	@Test
	public void testSendEmailProfesor9() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		ClaseAlumnoActividades claseAlumnoActividad4 = new ClaseAlumnoActividades();
		claseAlumnoActividad4.setId(1L);
		claseAlumnoActividad4.setId_actividad(1L);
		claseAlumnoActividad4.setId_clasealumno(1L);
		claseAlumnoActividad4.setId_recurso(3L);
		claseAlumnoActividad4.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad4);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmailProfesor();
	}
	
	@Test
	public void testSendEmailProfesor10() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		ClaseAlumnoActividades claseAlumnoActividad3 = new ClaseAlumnoActividades();
		claseAlumnoActividad3.setId(1L);
		claseAlumnoActividad3.setId_actividad(1L);
		claseAlumnoActividad3.setId_clasealumno(1L);
		claseAlumnoActividad3.setId_recurso(2L);
		claseAlumnoActividad3.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad3);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmailProfesor();
	}
	
	@Test
	public void testSendEmailProfesor11() throws Exception {
		//ClaseAlumnoActividades
		List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades claseAlumnoActividad = new ClaseAlumnoActividades();
		claseAlumnoActividad.setId(1L);
		claseAlumnoActividad.setId_actividad(1L);
		claseAlumnoActividad.setId_clasealumno(1L);
		claseAlumnoActividad.setId_recurso(1L);
		claseAlumnoActividad.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad);
		
		ClaseAlumnoActividades claseAlumnoActividad2 = new ClaseAlumnoActividades();
		claseAlumnoActividad2.setId(1L);
		claseAlumnoActividad2.setId_actividad(1L);
		claseAlumnoActividad2.setId_clasealumno(1L);
		claseAlumnoActividad2.setId_recurso(2L);
		claseAlumnoActividad2.setValor(1.1);
		lstClaseAlumnoActividades.add(claseAlumnoActividad2);
		
		when(claseAlumnoActividadesService.findByIdClasealumno(Mockito.anyLong())).thenReturn(lstClaseAlumnoActividades);
		service.sendEmailProfesor();
	}

}
