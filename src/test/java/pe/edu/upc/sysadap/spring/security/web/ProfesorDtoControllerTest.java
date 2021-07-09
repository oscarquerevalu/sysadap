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

import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.service.PersonaService;
import pe.edu.upc.sysadap.spring.security.web.dto.ProfesorDto;

public class ProfesorDtoControllerTest {
	
	@InjectMocks
	@Spy
	private ProfesorDtoController controller = new ProfesorDtoController();
	
	@Mock
	private PersonaService personaService;
	
	Model model = mock(Model.class);
	
	BindingResult result = mock(BindingResult.class);
	
	ProfesorDto profesorDto = new ProfesorDto(); 
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(personaService.findById(Mockito.anyLong())).thenReturn(new Persona());
		
	}

	@Test
	public void testShowRegistrationForm() {
		assertEquals("registration", controller.showRegistrationForm(model));
	}

	@Test
	public void testRegisterProfesor() {
		when(result.hasErrors()).thenReturn(true);
		assertEquals("admin/mantProfesor :: profesor-form", controller.registerProfesor(model, profesorDto, result));
	}
	
	@Test
	public void testRegisterProfesor2() {
		when(result.hasErrors()).thenReturn(false);
		profesorDto.setId(1L);
		assertEquals("redirect:/mantProf?success", controller.registerProfesor(model, profesorDto, result));
	}

}
