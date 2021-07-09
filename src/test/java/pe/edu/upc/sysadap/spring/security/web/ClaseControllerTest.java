package pe.edu.upc.sysadap.spring.security.web;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import pe.edu.upc.sysadap.spring.security.model.Alumno;
import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.Competencia;
import pe.edu.upc.sysadap.spring.security.model.Profesor;
import pe.edu.upc.sysadap.spring.security.service.ClaseService;
import pe.edu.upc.sysadap.spring.security.service.CompetenciaService;

public class ClaseControllerTest {

	@InjectMocks
	private ClaseController controller = new ClaseController();
	@Mock
	private ClaseService claseService;
	@Mock
	private CompetenciaService competenciaService;
	
	
	List<Alumno> listAlumno1 = new ArrayList<Alumno>();
	Alumno alumno = new Alumno();
	
	Model model = mock(Model.class);
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Competencia competencia = new Competencia();
		competencia.setId(1L);
		competencia.setNombre("ABC");
		Clase clase = new Clase();
		Profesor profesor = new Profesor();
		profesor.setId(1L);
		clase.setProfesor(profesor);
		Alumno alumno = new Alumno();
		alumno.setCalificado("20");
		clase.setCompetencias("1,2,3,4");
		when(claseService.findById(Mockito.anyLong())).thenReturn(clase);
		when(competenciaService.findById(Mockito.anyLong())).thenReturn(competencia);
	}
	
	@Test
	public void testListClase() {
		List<Clase> listClase = new ArrayList<Clase>();
		Clase clase = new Clase();
		Profesor profesor = new Profesor();
		profesor.setId(1L);
		clase.setProfesor(profesor);
		List<Alumno> listAlumno = new ArrayList<Alumno>();
		Alumno alumno = new Alumno();
		alumno.setCalificado("20");
		listAlumno.add(alumno);
		clase.setCompetencias("1,2,3,4");
		listClase.add(clase);
		when(claseService.findByAll()).thenReturn(listClase);
		alumno.setCalificado("20");
		assertNotNull(controller.listClases(model));
	}
	
	@Test
	public void testListClase2() {
		when(claseService.findByAll()).thenReturn(null);
		controller.listClases(model);
	}
	
	@Test
	public void testGetProfesor() {
		alumno.setCalificado("20");
		assertNotNull( controller.getProfesor(1L));
	}
	
	

}
