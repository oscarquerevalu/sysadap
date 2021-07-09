package pe.edu.upc.sysadap.spring.security.web;

import static org.junit.Assert.assertEquals;
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
import org.mockito.Spy;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.model.Profesor;
import pe.edu.upc.sysadap.spring.security.service.PersonaService;
import pe.edu.upc.sysadap.spring.security.service.ProfesorService;
import pe.edu.upc.sysadap.spring.security.web.dto.ProfesorDto;

public class ProfesorControllerTest {

	@InjectMocks
	@Spy
	private ProfesorController controller = new ProfesorController();
	
	@Mock
	private ProfesorService profesorService;
	
	@Mock
	private PersonaService personaService;
	
	Model model = mock(Model.class);
	
	BindingResult result = mock(BindingResult.class);
	
	ProfesorDto profesorDto = new ProfesorDto(); 
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(personaService.findByIdProfesor(Mockito.anyLong())).thenReturn(new Persona());
		
	}
	
	@Test
	public void testProfesorDto() {
		assertNotNull(controller.profesorDto());
	}

	@Test
	public void testListaProfesores() {
		List<Profesor> profesores = new ArrayList<Profesor>();
		Profesor profesor = new Profesor();
		profesor.setId(1L);
		profesores.add(profesor);
		when(profesorService.findByAll()).thenReturn(profesores);
		assertNotNull(controller.listaProfesores(model));
	}
	
	@Test
	public void testListaProfesores2() {
		when(profesorService.findByAll()).thenReturn(null);
		controller.listaProfesores(model);
	}

	@Test
	public void testGetProfesor() {
		Long id = 1L;
		when(personaService.findById(id)).thenReturn(new Persona());
		assertNotNull(controller.getProfesor(id));
	}

	@Test
	public void testGuardarProfesor() {
		when(result.hasErrors()).thenReturn(true);
		assertEquals("admin/mantProfesor",controller.guardarProfesor(profesorDto, result, model));
	}
	
	@Test
	public void testGuardarProfesor2() {
		when(result.hasErrors()).thenReturn(false);
		assertEquals("redirect:/mantProfesor?success",controller.guardarProfesor(profesorDto, result, model));
	}

}
