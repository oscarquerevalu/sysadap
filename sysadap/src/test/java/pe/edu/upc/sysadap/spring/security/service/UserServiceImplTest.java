package pe.edu.upc.sysadap.spring.security.service;
//package pe.edu.upc.sysadap.spring.security.service.test;
//
//import static org.junit.Assert.*;
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.security.authentication.encoding.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import pe.edu.upc.sysadap.spring.security.model.Persona;
//import pe.edu.upc.sysadap.spring.security.repository.UserRepository;
//import pe.edu.upc.sysadap.spring.security.service.UserServiceImpl;
//
//
//public class UserServiceImplTest {
//
//	@InjectMocks
//	private UserServiceImpl service = new UserServiceImpl();
//	@Mock
//	private BCryptPasswordEncoder passwordEncoder;
//	@Mock
//	private UserRepository userRepository;
//	
//	Persona registration = new Persona();
//	@Before
//	public void init() {
//		MockitoAnnotations.initMocks(this);
//		registration.setPassword("Pswrd");
//		when(passwordEncoder.encode(registration.getPassword())).thenReturn("Pswrd");
//	    when(userRepository.save(Mockito.any(Persona.class))).thenReturn(registration);
//	}
//	/*
//	@Test
//	public void testFindByEmail() {
//		fail("Not yet implemented");
//	}
//*/
//	@Test
//	public void testSave() {
//		registration.setPassword("Pswrd");
//		
//		assertEquals(registration.getPassword(), service.save(registration).getPassword());
//	}
///*
//	@Test
//	public void testUpdatePassword() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testLoadUserByUsername() {
//		fail("Not yet implemented");
//	}
//*/
//}
