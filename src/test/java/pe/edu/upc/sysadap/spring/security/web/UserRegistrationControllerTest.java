package pe.edu.upc.sysadap.spring.security.web;

import static org.junit.Assert.*;
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
import pe.edu.upc.sysadap.spring.security.service.UserService;
import pe.edu.upc.sysadap.spring.security.web.dto.PersonaDTO;

public class UserRegistrationControllerTest {
	
	@InjectMocks
	@Spy
	private UserRegistrationController controller = new UserRegistrationController();
	
	@Mock
	private UserService userService;
	
	Model model = mock(Model.class);
	
	BindingResult result = mock(BindingResult.class);
	
	PersonaDTO personaDTO = new PersonaDTO();
	
	Persona persona = new Persona();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(result.hasErrors()).thenReturn(true);
		personaDTO.setEmail("hola@email.com");
	}
	
	@Test
	public void testUserRegistrationDto() {
		assertNotNull(controller.userRegistrationDto());
	}

	@Test
	public void testShowRegistrationForm() {
		assertEquals("registration", controller.showRegistrationForm(model));
	}

	@Test
	public void testRegisterUserAccount() {
		personaDTO.setPassword("Hola1414");
		personaDTO.setConfirmPassword("Hola1414");
		personaDTO.setEmail("hola@email.com");
		personaDTO.setConfirmEmail("hola@email.com");
		assertEquals("registration", controller.registerUserAccount(personaDTO, result));
	}
	
	@Test
	public void testRegisterUserAccount2() {
		personaDTO.setPassword("Hola1414");
		personaDTO.setConfirmPassword("Hola1411");
		when(userService.findByEmail(Mockito.anyString())).thenReturn(persona);
		when(result.hasErrors()).thenReturn(false);
		assertEquals("redirect:/registration?success", controller.registerUserAccount(personaDTO, result));
	}
	
	@Test
	public void testRegisterUserAccount3() {
		personaDTO.setPassword("Hola1414");
		personaDTO.setConfirmPassword("Hola1414");
		personaDTO.setEmail("hola1@email.com");
		personaDTO.setConfirmEmail("hola@email.com");
		when(result.hasErrors()).thenReturn(false);
		assertEquals("redirect:/registration?success", controller.registerUserAccount(personaDTO, result));
	}
	
	@Test
	public void testRegisterUserAccount4() {
		personaDTO.setPassword("Hola1414");
		personaDTO.setConfirmPassword("Hola1411");
		when(result.hasErrors()).thenReturn(false);
		assertEquals("redirect:/registration?success", controller.registerUserAccount(personaDTO, result));
	}

}
