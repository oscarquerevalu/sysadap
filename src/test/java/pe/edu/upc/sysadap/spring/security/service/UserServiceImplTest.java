package pe.edu.upc.sysadap.spring.security.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.model.Persona.Role;
import pe.edu.upc.sysadap.spring.security.repository.ApoderadoRepository;
import pe.edu.upc.sysadap.spring.security.repository.ProfesorRepository;
import pe.edu.upc.sysadap.spring.security.repository.UserRepository;


public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl service = new UserServiceImpl();
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private ProfesorRepository profesorRepository;
	
	@Mock
	private ApoderadoRepository apoderadoRepository;
	
	Persona registration = new Persona();
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		registration.setPassword("Pswrd");
		when(passwordEncoder.encode(registration.getPassword())).thenReturn("Pswrd");
	    when(userRepository.save(Mockito.any(Persona.class))).thenReturn(registration);
	}
	
	@Test
	public void testFindByEmail() {
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(registration);
		assertNotNull(service.findByEmail(""));
	}

	@Test
	public void testSave() {
		registration.setPassword("Pswrd");
		registration.setRole(Role.ROLE_USER);
		assertEquals(registration.getPassword(), service.save(registration).getPassword());
	}
	
	@Test
	public void testSave2() {
		registration.setPassword("Pswrd");
		registration.setRole(Role.ROLE_APODE);
		assertEquals(registration.getPassword(), service.save(registration).getPassword());
	}

	@Test
	public void testUpdatePassword() {
		service.updatePassword("", 1L);
	}

	@Test
	public void testLoadUserByUsername() {
		registration.setPassword("Pswrd");
		registration.setEmail("email@email.com");;
		registration.setRole(Role.ROLE_USER);
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(registration);
		assertNotNull(service.loadUserByUsername(""));
	}
	
	@Test(expected = UsernameNotFoundException.class)
	public void testLoadUserByUsername2() {
		registration.setPassword("Pswrd");
		registration.setEmail("email@email.com");;
		registration.setRole(Role.ROLE_USER);
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);
		service.loadUserByUsername("");
	}

}
