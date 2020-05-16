package pe.edu.upc.sysadap.spring.security.web;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import pe.edu.upc.sysadap.spring.security.model.Alumno;
import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;
import pe.edu.upc.sysadap.spring.security.model.ClaseAlumnoActividades;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.service.AlumnoService;
import pe.edu.upc.sysadap.spring.security.service.ClaseAlumnoActividadesService;
import pe.edu.upc.sysadap.spring.security.service.ClaseAlumnoService;
import pe.edu.upc.sysadap.spring.security.service.PersonaService;

public class AlumnoControllerTest {

	@InjectMocks
	private AlumnoController controller = new AlumnoController();
	@Mock
	private AlumnoService alumnoService;
	@Mock
	private PersonaService personaService;
	@Mock
	private ClaseAlumnoService claseAlumnoService;
	@Mock
	private ClaseAlumnoActividadesService claseAlumnoActividadesService;
	@Mock
	private ClaseAlumnoActividades claseAlumnoActividadesComparator;
	
	List<ClaseAlumno> listClaseAlumno = new ArrayList<ClaseAlumno>();
	ClaseAlumno ClaseAlumno = new ClaseAlumno();
	
	ClaseAlumnoActividades ClaseAlumnoActividades = new ClaseAlumnoActividades();
	ClaseAlumnoActividades ClaseAlumnoActividades1 = new ClaseAlumnoActividades();
	Model model;
	Map<String, Object> map = new HashMap<String, Object>();
	Map<String, String> body = new HashMap<String, String>();
	List<Map<String, Object>> listReturn = new ArrayList<Map<String,Object>>(); 
	Alumno Alumno = new Alumno();
	@Before
	public void init() {
		
		MockitoAnnotations.initMocks(this);
		
		List<Alumno> listAlumno = new ArrayList<Alumno>();

		Persona persona = new Persona();
		Alumno.setPersona(persona);
		Alumno.setId(15L);
		listAlumno.add(Alumno);
		ClaseAlumno.setId(15L);
		when(alumnoService.findByAll()).thenReturn(listAlumno);
		when(alumnoService.findByIdClase(Mockito.anyLong())).thenReturn(listAlumno);
		
		when(personaService.findByIdAlumno(Alumno.getId())).thenReturn(persona);

	    when(claseAlumnoService.findByFechaIdAlumno(Mockito.anyString(), Mockito.anyLong(),Mockito.anyLong())).thenReturn(listClaseAlumno);
	    when(claseAlumnoService.findByAll()).thenReturn(listClaseAlumno);
	    when(claseAlumnoService.guardar(Mockito.any(ClaseAlumno.class))).thenReturn(ClaseAlumno);
	    
	    
	    List<ClaseAlumnoActividades> listClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
	    listClaseAlumnoActividades.add(ClaseAlumnoActividades);
	    listClaseAlumnoActividades.add(ClaseAlumnoActividades1);
	    when(claseAlumnoActividadesService.findByIdClasealumno(ClaseAlumno.getId())).thenReturn(listClaseAlumnoActividades);
	    when(claseAlumnoActividadesService.findAll()).thenReturn(listClaseAlumnoActividades);
	    
	}
	
	@Test
	public void testListaClase() {
		Long esp = 15L;
		assertEquals(esp, controller.listaClase(model).get(0).getId());
	}
	/* PENDIENTE CONDICIÓN NULL
	@Test
	public void testListaClase1() {
		Long esp = 15L;
		when(personaService.findByIdAlumno(Mockito.anyLong())).thenReturn(null);
		assertEquals(esp, controller.listaClase(model).get(0).getId());
	}
	*/
	@Test
	public void testListaClaseCatch() {
		when(alumnoService.findByAll()).thenReturn(null);
		assertNull(controller.listaClase(model));
	}

	@Test
	public void testListalumnosxFecnaCalif() {	
		String fecha="";
		Long datoL = 15L;
		listClaseAlumno.add(ClaseAlumno);
		assertEquals("SI", controller.listalumnosxFecna(fecha,datoL).get(0).getCalificado());
	}
	@Test
	public void testListalumnosxFecnaNoCalif() {
		String fecha="";
		Long datoL = 15L;
		assertEquals("NO", controller.listalumnosxFecna(fecha,datoL).get(0).getCalificado());
	}

	@Test
	public void testAlumnoxFecnaVal1() {
		String fecha = "";
		Long clase = 15L;
		Long alumno = 10L;
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(1L);
		map.put("val1", 0.0);
		assertEquals(map.get("val1"), controller.alumnoxFecna(fecha, clase, alumno).get("val1"));
	}
	@Test
	public void testAlumnoxFecnaVal2() {
		String fecha = "";
		Long clase = 15L;
		Long alumno = 10L;
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(2L);
		map.put("val2", 0.0);
		assertEquals(map.get("val2"), controller.alumnoxFecna(fecha, clase, alumno).get("val2"));
	}
	@Test
	public void testAlumnoxFecnaVal3() {
		String fecha = "";
		Long clase = 15L;
		Long alumno = 10L;
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(3L);
		map.put("val3", 0.0);
		assertEquals(map.get("val3"), controller.alumnoxFecna(fecha, clase, alumno).get("val3"));
	}
	@Test
	public void testAlumnoxFecnaVal4() {
		String fecha = "";
		Long clase = 15L;
		Long alumno = 10L;
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(4L);
		map.put("val4", 0.0);
		assertEquals(map.get("val4"), controller.alumnoxFecna(fecha, clase, alumno).get("val4"));
	}
	@Test
	public void testAlumnoxFecnaVal5() {
		String fecha = "";
		Long clase = 15L;
		Long alumno = 10L;
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(5L);
		map.put("val5", 0.0);
		assertEquals(map.get("val5"), controller.alumnoxFecna(fecha, clase, alumno).get("val5"));
	}
	@Test
	public void testAlumnoxFecnaVal6() {
		String fecha = "";
		Long clase = 15L;
		Long alumno = 10L;
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(6L);
		map.put("val6", 0.0);
		assertEquals(map.get("val6"), controller.alumnoxFecna(fecha, clase, alumno).get("val6"));
	}
	@Test
	public void testAlumnoxFecnaVal7() {
		String fecha = "";
		Long clase = 15L;
		Long alumno = 10L;
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(7L);
		map.put("val7", 0.0);
		assertEquals(map.get("val7"), controller.alumnoxFecna(fecha, clase, alumno).get("val7"));
	}
	@Test
	public void testAlumnoxFecnaVal8() {
		String fecha = "";
		Long clase = 15L;
		Long alumno = 10L;
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(8L);
		map.put("val8", 0.0);
		assertEquals(map.get("val8"), controller.alumnoxFecna(fecha, clase, alumno).get("val8"));
	}
	@Test
	public void testAlumnoxFecnaVal9() {
		String fecha = "";
		Long clase = 15L;
		Long alumno = 10L;
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(9L);
		map.put("val9", 0.0);
		assertEquals(map.get("val9"), controller.alumnoxFecna(fecha, clase, alumno).get("val9"));
	}
	@Test
	public void testAlumnoxFecnaVal10() {
		String fecha = "";
		Long clase = 15L;
		Long alumno = 10L;
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(10L);
		map.put("val10", 0.0);
		assertEquals(map.get("val10"), controller.alumnoxFecna(fecha, clase, alumno).get("val10"));
	}
	@Test
	public void testAlumnoxFecnaVal11() {
		String fecha = "";
		Long clase = 15L;
		Long alumno = 10L;
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(11L);
		map.put("val11", 0.0);
		assertEquals(map.get("val11"), controller.alumnoxFecna(fecha, clase, alumno).get("val11"));
	}
	@Test
	public void testAlumnoxFecnaVal12() {
		String fecha = "";
		Long clase = 15L;
		Long alumno = 10L;
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(12L);
		map.put("val12", 0.0);
		assertEquals(map.get("val12"), controller.alumnoxFecna(fecha, clase, alumno).get("val12"));
	}
	@Test
	public void testGetClaseAlumnoRecurso1() {
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(1L);
		
		map.put("recurso1", 0.0);
		assertEquals(map.get("recurso1"), controller.getClaseAlumno().get(0).get("recurso1"));
	}
	@Test
	public void testGetClaseAlumnoRecurso2() {
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(2L);
		
		map.put("recurso2", 0.0);
		assertEquals(map.get("recurso2"), controller.getClaseAlumno().get(0).get("recurso2"));
	}
	@Test
	public void testGetClaseAlumnoRecurso3() {
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(3L);
		
		map.put("recurso3", 0.0);
		assertEquals(map.get("recurso3"), controller.getClaseAlumno().get(0).get("recurso3"));
	}
	@Test
	public void testGetClaseAlumnoRecurso4() {
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(4L);
		
		map.put("recurso4", 0.0);
		assertEquals(map.get("recurso4"), controller.getClaseAlumno().get(0).get("recurso4"));
	}
	@Test
	public void testGetClaseAlumnoRecurso5() {
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(5L);
		
		map.put("recurso5", 0.0);
		assertEquals(map.get("recurso5"), controller.getClaseAlumno().get(0).get("recurso5"));
	}
	@Test
	public void testGetClaseAlumnoRecurso6() {
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(6L);
		
		map.put("recurso6", 0.0);
		assertEquals(map.get("recurso6"), controller.getClaseAlumno().get(0).get("recurso6"));
	}
	@Test
	public void testGetClaseAlumnoRecurso7() {
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(7L);
		
		map.put("recurso7", 0.0);
		assertEquals(map.get("recurso7"), controller.getClaseAlumno().get(0).get("recurso7"));
	}
	@Test
	public void testGetClaseAlumnoRecurso8() {
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(8L);
		
		map.put("recurso8", 0.0);
		assertEquals(map.get("recurso8"), controller.getClaseAlumno().get(0).get("recurso8"));
	}
	@Test
	public void testGetClaseAlumnoRecurso9() {
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(9L);
		
		map.put("recurso9", 0.0);
		assertEquals(map.get("recurso9"), controller.getClaseAlumno().get(0).get("recurso9"));
	}
	@Test
	public void testGetClaseAlumnoRecurso10() {
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(10L);
		
		map.put("recurso10", 0.0);
		assertEquals(map.get("recurso10"), controller.getClaseAlumno().get(0).get("recurso10"));
	}
	@Test
	public void testGetClaseAlumnoRecurso11() {
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(11L);
		
		map.put("recurso11", 0.0);
		assertEquals(map.get("recurso11"), controller.getClaseAlumno().get(0).get("recurso11"));
	}
	@Test
	public void testGetClaseAlumnoRecurso12() {
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		ClaseAlumnoActividades.setId_recurso(12L);
		
		map.put("recurso12", 0.0);
		assertEquals(map.get("recurso12"), controller.getClaseAlumno().get(0).get("recurso12"));
	}

	@Test
	public void testCreate() {
		body.put("idAlumno", "15");
		body.put("recursos", "1");
		body.put("valores", "10");
		body.put("actividad", "10");
		body.put("idClase", "1");
		assertEquals("15", controller.create(body));
	}

	@Test
	public void testCreateEstilo() {
		body.put("recursos", "1");
		body.put("valores", "10");
		body.put("estilo", "10");
		assertEquals("{\"success\":1}", controller.createEstilo(body));
	}

	@Test
	public void testPromRecursosId0() {
		Map<String, Object> promedio = new HashMap<String, Object>();
		promedio.put("index", 1L);
		listReturn.add(promedio);
		ClaseAlumnoActividades.setId_recurso(1L);
		ClaseAlumnoActividades1.setId_recurso(2L);
		assertEquals(listReturn.get(0).get("index"), controller.promRecursos(model).get(0).get("index"));
	}

}
