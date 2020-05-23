package pe.edu.upc.sysadap.spring.security.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import javax.mail.internet.MimeMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring4.SpringTemplateEngine;

import pe.edu.upc.sysadap.spring.security.model.Mail;
import pe.edu.upc.sysadap.spring.security.service.EmailService;

public class EmailServiceTest {

	@InjectMocks 
    @Spy
	private EmailService service;
	@Mock
	private JavaMailSender emailSender;
	@Mock
	private SpringTemplateEngine templateEngine;
	
	Mail mail = new Mail();
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		MimeMessage message = mock(MimeMessage.class);
		when(emailSender.createMimeMessage()).thenReturn(message);		
	
		doReturn("").when(service).html(Mockito.anyString(), Mockito.any(Context.class));
	}
	
	@Test
	public void testSendEmailMail() {
		mail.setModel(new HashMap<>());
		mail.setTo("asdas");
		mail.setSubject("asdasd");
		mail.setFrom("asasd");
		service.sendEmail(mail);
	}
	@Test (expected = Exception.class)
	public void testSendEmailMailCath() {
		service.sendEmail(mail);
	}

	@Test
	public void testHtml() {
		String valueSt = "";
		Context context = new Context();
		service.html(valueSt, context);
	}
	
	@Test
	public void testSendEmail() {
		
	}

}
