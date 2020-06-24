package pe.edu.upc.sysadap.spring.security.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.sysadap.spring.security.model.PasswordResetToken;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.PasswordResetTokenRepository;
import pe.edu.upc.sysadap.spring.security.service.EmailService;
import pe.edu.upc.sysadap.spring.security.service.UserService;
import pe.edu.upc.sysadap.spring.security.web.dto.PasswordForgotDto;

public class PasswordForgotControllerTest {
	
	@InjectMocks
	@Spy
	private PasswordForgotController controller = new PasswordForgotController();
	
	@Mock
	private UserService userService;
	
	@Mock
	private PasswordResetTokenRepository tokenRepository;
	
	@Mock
	private EmailService emailService;
	
	Model model = mock(Model.class);
	
	BindingResult result = mock(BindingResult.class);
	
	HttpServletRequest request = mock(HttpServletRequest.class);
	
	PasswordForgotDto passwordForgotDto = new PasswordForgotDto();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Persona persona = new Persona();
		persona.setId(1L);
		persona.setName("Oscar");
		when(userService.findByEmail(Mockito.anyString())).thenReturn(persona);
		when(tokenRepository.save(Mockito.any(PasswordResetToken.class))).thenReturn(new PasswordResetToken());
		
	}

	@Test
	public void testForgotPasswordDto() {
		assertNotNull(controller.forgotPasswordDto());
	}

	@Test
	public void testDisplayForgotPasswordPage() {
		assertEquals("forgot-password", controller.displayForgotPasswordPage());
	}

	@Test
	public void testProcessForgotPasswordForm() throws Exception {
		when(result.hasErrors()).thenReturn(true);
		assertEquals("forgot-password", controller.processForgotPasswordForm(passwordForgotDto, result, request));
	}

	@Test
	public void testProcessForgotPasswordForm2() throws Exception {
		when(result.hasErrors()).thenReturn(false);
		passwordForgotDto.setEmail("");
		assertEquals("redirect:/forgot-password?success", controller.processForgotPasswordForm(passwordForgotDto, result, request));
	}
}
