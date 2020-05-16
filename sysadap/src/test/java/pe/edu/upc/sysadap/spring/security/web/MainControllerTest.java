package pe.edu.upc.sysadap.spring.security.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.ui.Model;

import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.Competencia;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.model.Profesor;
import pe.edu.upc.sysadap.spring.security.repository.UserRepository;
import pe.edu.upc.sysadap.spring.security.service.ClaseService;
import pe.edu.upc.sysadap.spring.security.service.CompetenciaService;

public class MainControllerTest {

	@InjectMocks
	@Spy
	private MainController controller = new MainController();
	@Mock
	private UserRepository userRepository;
	@Mock
	private SecurityContextHolder securityContextHolder;
	@Mock
	private SecurityContextHolderStrategy strategy;
	@Mock
	private CompetenciaService competenciaService;
	@Mock
	private Authentication authen;
	@Mock
	private SecurityContextHolder security;
	@Mock
	private ClaseService claseService;
	
	HttpSession session = Mockito.mock(HttpSession.class);
	Model model = Mockito.mock(Model.class);
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		Persona persona = new Persona();
		
		Profesor profesor = new Profesor();
		List<Clase> listclase = new ArrayList<>();
		Clase clase = new Clase();
		listclase.add(clase);
		profesor.setClase(listclase);
		
		persona.setProfesor(profesor);
		when(userRepository.findByEmail(Mockito.anyString())).thenReturn(persona);

		when((Persona) session.getAttribute(Mockito.anyString())).thenReturn(persona);
		String as = "Angelo";
		when(authen.getName()).thenReturn(as);
		Mockito.doReturn(authen).when(controller).getAuthentication();
	
		List<Competencia> listCompetencia = new ArrayList<Competencia>();
		Competencia competencia = new Competencia();
		listCompetencia.add(competencia);
		when(competenciaService.findByAll()).thenReturn(listCompetencia);
	
		when(claseService.findByAll()).thenReturn(listclase);
	}
	
	@Test
	public void testRoot() {
		assertEquals("index", controller.root(model,session));
	}

	@Test
	public void testLogin() {
		assertEquals("login", controller.login(model));
	}

	@Test
	public void testAccessdenied() {
		assertEquals("accessdenied", controller.accessdenied(model));
	}
/*
	@Test
	public void testViewAlumnos() {
		
	}*/
	
	@Test
	public void testViewAdmin() {
		assertEquals("admin/indexAdmin", controller.viewAdmin(model, session));
	}
	
	@Test
	public void testViewProf() {
		assertEquals("admin/mantProfesor", controller.viewProf(model, session));
	}
	@Test
	public void testMantAlumno() {
		assertEquals("admin/mantAlumno", controller.mantAlumno(model, session));
	}

	@Test
	public void testMantComp() {
		assertEquals("admin/mantComp", controller.mantComp(model, session));
	}
	
	@Test
	public void testViewEA() {
		assertEquals("indexEstiloAprendisaje", controller.viewEA(model, session));
	}

	@Test
	public void testUserIndex() {
		assertEquals("user/index", controller.userIndex());
	}
	@Test
	public void testgetAuthentication() {
		assertNotNull(controller.getAuthentication());
	}

}
