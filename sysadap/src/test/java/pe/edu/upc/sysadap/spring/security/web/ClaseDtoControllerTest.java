package pe.edu.upc.sysadap.spring.security.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.Profesor;
import pe.edu.upc.sysadap.spring.security.service.ClaseService;
import pe.edu.upc.sysadap.spring.security.service.ProfesorService;
import pe.edu.upc.sysadap.spring.security.web.dto.ClaseDto;

public class ClaseDtoControllerTest {

	@InjectMocks
	@Spy
	private ClaseDtoController controller;
	@Mock
	private ClaseService claseService;
	@Mock
	private ProfesorService profesorService;
	
	Model model = mock(Model.class);
	Long[] competencias = {1L, 2l};
	BindingResult result = mock(BindingResult.class);
	ClaseDto claseDto = new ClaseDto();
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		Clase clase = new Clase();
		when(claseService.findById(Mockito.anyLong())).thenReturn(clase);
		when(claseService.guardarClase(Mockito.any(Clase.class))).thenReturn(clase);
	
		Profesor profesor = new Profesor();
		when(profesorService.findByID(Mockito.anyLong())).thenReturn(profesor);
	}
	
	@Test
	public void testShowRegistrationForm() {
		assertEquals("registration", controller.showRegistrationForm(model));
	}

	@Test
	public void testRegisterProfesorError() {
		when(result.hasErrors()).thenReturn(true);
		assertEquals("admin/mantAulSec :: clase-form", controller.registerProfesor(model, claseDto, competencias, result));
	}
	@Test
	public void testRegisterProfesor() {
		Long[] competencias = {1L, 2l};
		claseDto.setId(1L);
		claseDto.setIdProfesor(1L);
		assertEquals("redirect:/mantAulSec?success", controller.registerProfesor(model, claseDto, competencias, result));
	}
	@Test
	public void testRegisterProfesorCompNull() {
		Long[] competencias = null;
		claseDto.setId(1L);
		claseDto.setIdProfesor(1L);
		assertEquals("redirect:/mantAulSec?success", controller.registerProfesor(model, claseDto, competencias, result));
	}

}
