package pe.edu.upc.sysadap.spring.security.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.PersonaRepository;

public class PersonaServiceImplTest {
	
	@InjectMocks
	private PersonaServiceImpl service = new PersonaServiceImpl();
	
	@Mock
	private PersonaRepository personaRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		//Persona
		Persona persona = new Persona();
		persona.setName("Angelo");
		//Alumno
		List<Persona> lstPersonas = new ArrayList<>();
		Persona person = new Persona();
		lstPersonas.add(person);
		when(personaRepository.findAll()).thenReturn(lstPersonas);
		when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(person);
		when(personaRepository.saveAndFlush(Mockito.any(Persona.class))).thenReturn(person);
		when(personaRepository.findOne(Mockito.any(Long.class))).thenReturn(person);
		when(personaRepository.getOne(Mockito.any(Long.class))).thenReturn(person);
		when(personaRepository.findByIdApoderado(Mockito.any(Long.class))).thenReturn(person);
		when(personaRepository.findByIdAlumno(Mockito.any(Long.class))).thenReturn(person);
		when(personaRepository.findByIdProfesor(Mockito.any(Long.class))).thenReturn(person);
		when(personaRepository.findByIdApoderado(Mockito.anyString())).thenReturn(lstPersonas);
		
	}

	@Test
	public void testFindByAll() {
		assertNotNull(service.findByAll());
	}

	@Test
	public void testFindByIdApoderadoString() {
		assertNotNull(service.findByIdApoderado(""));
	}

	@Test
	public void testFindById() {
		assertNotNull(service.findById(1L));
	}

	@Test
	public void testFindByIdAlumno() {
		assertNotNull(service.findByIdAlumno(1L));
	}

	@Test
	public void testFindByIdProfesor() {
		assertNotNull(service.findByIdProfesor(1L));
	}

	@Test
	public void testGuardarPersona() {
		assertNotNull(service.guardarPersona(new Persona()));
	}

	@Test
	public void testFindByIdApoderadoLong() {
		assertNotNull(service.findByIdApoderado(1L));
	}

}
