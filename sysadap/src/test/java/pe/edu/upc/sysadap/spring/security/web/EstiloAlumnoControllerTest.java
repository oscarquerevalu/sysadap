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

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;
import pe.edu.upc.sysadap.spring.security.model.EstiloAlumno;
import pe.edu.upc.sysadap.spring.security.service.ClaseAlumnoService;
import pe.edu.upc.sysadap.spring.security.service.EstiloAlumnoService;

public class EstiloAlumnoControllerTest {

	@InjectMocks
	private EstiloAlumnoController controller = new EstiloAlumnoController();
	@Mock
	private EstiloAlumnoService estiloAlumnoService;
	@Mock
	private ClaseAlumnoService claseAlumnoService;
	
	Map<String, String> body = new HashMap<String, String>();
	List<ClaseAlumno> listClaseAlumno = new ArrayList<ClaseAlumno>();
	Map<String, Object> map = new HashMap<String, Object>();
	EstiloAlumno estiloAlumno = new EstiloAlumno();
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		
		when(estiloAlumnoService.guardar(Mockito.any(EstiloAlumno.class))).thenReturn(estiloAlumno);
		List<EstiloAlumno> listEstiloAlumno = new ArrayList<EstiloAlumno>();
		estiloAlumno.setFecha("22/04/2020");
		
		listEstiloAlumno.add(estiloAlumno);
 	    when(estiloAlumnoService.findByFechaIdAlumno(Mockito.anyString(), Mockito.anyString(), Mockito.anyLong())).thenReturn(listEstiloAlumno);
		
		
		ClaseAlumno ClaseAlumno = new ClaseAlumno();
		ClaseAlumno.setId_estilo(15L);
		listClaseAlumno.add(ClaseAlumno);
		
	    when(claseAlumnoService.guardar(listClaseAlumno.get(0))).thenReturn(ClaseAlumno);
	}
	
	@Test
	public void testCreate() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "1");
		body.put("valores", "1");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate0_1() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "1");
		body.put("valores", "-1,0");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate1() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "2");
		body.put("valores", "1");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate1_1() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "2");
		body.put("valores", "-1,0");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate2() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "3");
		body.put("valores", "1");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate2_1() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "3");
		body.put("valores", "-1,0");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate3() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "4");
		body.put("valores", "1");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate3_1() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "4");
		body.put("valores", "-1,0");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate4() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "5");
		body.put("valores", "1");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate4_1() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "5");
		body.put("valores", "-1,0");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate5() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "6");
		body.put("valores", "1");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate5_1() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "6");
		body.put("valores", "-1,0");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate6() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "7");
		body.put("valores", "1");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate6_1() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "7");
		body.put("valores", "-1,0");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate7() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "8");
		body.put("valores", "1");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreate7_1() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "8");
		body.put("valores", "-1,0");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClaseAlumno);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreateElse() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "10");
		body.put("valores", "-1,0");
		body.put("idClase", "1");
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(null);
		assertEquals("", controller.create(body));
	}
	@Test
	public void testCreatelistVacia() {
		body.put("fecha", "22/04/2020");
		body.put("idAlumno", "1");
		body.put("recursos", "10");
		body.put("valores", "-1,0");
		body.put("idClase", "1");
		List<ClaseAlumno> listClasAl = new ArrayList<ClaseAlumno>();
		when(claseAlumnoService.findByFechaIdAlumno(body.get("fecha"),new Long(body.get("idAlumno")),new Long(body.get("idClase")))).thenReturn(listClasAl);
		assertEquals("", controller.create(body));
	}

	@Test
	public void testAlumnoxFecna() {
		String fechaIni = "";
		String fechaFin = "";
		Long alumno = 15L;
		map.put("lineTension", 0);
		assertEquals(map.get("lineTension"), controller.alumnoxFecna(fechaIni, fechaFin, alumno).get(0).get("lineTension"));
	}
	@Test
	public void testAlumnoxFecnaSetValor() {
		String fechaIni = "";
		String fechaFin = "";
		Long alumno = 15L;
		estiloAlumno.setValor1(0.0);
		estiloAlumno.setValor2(0.1);
		estiloAlumno.setValor3(0.2);
		estiloAlumno.setValor4(0.3);
		estiloAlumno.setValor5(0.4);
		estiloAlumno.setValor6(0.5);
		estiloAlumno.setValor7(0.6);
		estiloAlumno.setValor8(0.7);
		map.put("lineTension", 0);
		assertEquals(map.get("lineTension"), controller.alumnoxFecna(fechaIni, fechaFin, alumno).get(0).get("lineTension"));
	}
	@Test
	public void testAlumnoxFecnaCATCH() {
		String fechaIni = "";
		String fechaFin = "";
		Long alumno = 15L;
		map.put("lineTension", 0);
		when(estiloAlumnoService.findByFechaIdAlumno(Mockito.anyString(), Mockito.anyString(), Mockito.anyLong())).thenReturn(null);
		assertNotNull(controller.alumnoxFecna(fechaIni, fechaFin, alumno));
	}

}
