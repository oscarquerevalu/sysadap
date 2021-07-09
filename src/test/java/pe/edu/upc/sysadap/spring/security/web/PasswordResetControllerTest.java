package pe.edu.upc.sysadap.spring.security.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.sysadap.spring.security.model.PasswordResetToken;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.PasswordResetTokenRepository;
import pe.edu.upc.sysadap.spring.security.service.UserService;
import pe.edu.upc.sysadap.spring.security.web.dto.PasswordResetDto;

public class PasswordResetControllerTest {
	
	@InjectMocks
	@Spy
	private PasswordResetController controller = new PasswordResetController();
	
	@Mock
	private PasswordResetTokenRepository tokenRepository;
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	
	@Mock
	private UserService userService;
	
	Model model = mock(Model.class);
	
	BindingResult result = mock(BindingResult.class);
	
	HttpServletRequest request = mock(HttpServletRequest.class);
	
	PasswordResetDto passwordResetDto = new PasswordResetDto();
	
	RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(passwordEncoder.encode(Mockito.anyString())).thenReturn("");
		
	}

	@Test
	public void testPasswordReset() {
		assertNotNull(controller.passwordReset());
	}

	@Test
	public void testDisplayResetPasswordPage() {
		when(tokenRepository.findByToken("")).thenReturn(null);
		assertNotNull(controller.displayResetPasswordPage("", model));
	}

	@Test
	public void testHandlePasswordReset() {
		when(result.hasErrors()).thenReturn(true);
		assertEquals("redirect:/reset-password?token=null", controller.handlePasswordReset(passwordResetDto, result, redirectAttributes, request));
	}
	
	@Test
	public void testHandlePasswordReset2() {
		when(result.hasErrors()).thenReturn(false);
		passwordResetDto.setToken("");
		PasswordResetToken token = new PasswordResetToken();
		token.setUser(new Persona());
		when(tokenRepository.findByToken(Mockito.anyString())).thenReturn(token);
		assertNotNull(controller.handlePasswordReset(passwordResetDto, result, redirectAttributes, request));
	}
	
	@Test
	public void testHandlePasswordReset3() {
		when(result.hasErrors()).thenReturn(false);
		passwordResetDto.setToken("");
		PasswordResetToken token = new PasswordResetToken();
		token.setUser(new Persona());
		token.setExpiryDate(new Date());
		when(tokenRepository.findByToken(Mockito.anyString())).thenReturn(token);
		assertNotNull(controller.displayResetPasswordPage("", model));
	}
	
	@Test
	public void testHandlePasswordReset4() {
		when(result.hasErrors()).thenReturn(false);
		passwordResetDto.setToken("");
		PasswordResetToken token = new PasswordResetToken();
		token.setUser(new Persona());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateInString = "31-08-2020 10:20:56";
		try {
			Date date = sdf.parse(dateInString);
			token.setExpiryDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		when(tokenRepository.findByToken(Mockito.anyString())).thenReturn(token);
		assertNotNull(controller.displayResetPasswordPage("", model));
	}
	
	

}
