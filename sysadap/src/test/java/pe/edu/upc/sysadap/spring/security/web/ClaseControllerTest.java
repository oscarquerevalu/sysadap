package pe.edu.upc.sysadap.spring.security.web;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.swagger.models.Model;
import pe.edu.upc.sysadap.spring.security.model.Alumno;
import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.service.ClaseService;

public class ClaseControllerTest {

	@InjectMocks
	private ClaseController controller = new ClaseController();
	@Mock
	private ClaseService claseService;
	
	List<Alumno> listAlumno1 = new ArrayList<Alumno>();
	Alumno alumno = new Alumno();
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		List<Clase> listClase = new ArrayList<Clase>();
		Clase clase = new Clase();
		List<Alumno> listAlumno = new ArrayList<Alumno>();
		Alumno alumno = new Alumno();
		alumno.setCalificado("20");
		listAlumno.add(alumno);
		clase.setAlumnos(listAlumno);
		listClase.add(clase);
		when(claseService.findByAll()).thenReturn(listClase);
	}
	
	@Test
	public void testListaClase() {
		alumno.setCalificado("20");
		assertEquals(listAlumno1, controller.listaClase(Mockito.any(org.springframework.ui.Model.class)));
	}

}
