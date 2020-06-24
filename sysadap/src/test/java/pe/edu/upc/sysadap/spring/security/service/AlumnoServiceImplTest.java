package pe.edu.upc.sysadap.spring.security.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import pe.edu.upc.sysadap.spring.security.model.Alumno;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.AlumnoRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AlumnoServiceImplTest {

	@InjectMocks
	private AlumnoServiceImpl service = new AlumnoServiceImpl();
	@Mock
	private AlumnoRepository alumnoRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		//Persona
		Persona persona = new Persona();
		persona.setName("Angelo");
		//Alumno
		List<Alumno> listAlumno = new ArrayList<>();
		Alumno alumno = new Alumno();
		alumno.setPersona(persona);
		listAlumno.add(alumno);
		when(alumnoRepository.findAll()).thenReturn(listAlumno);
		when(alumnoRepository.saveAndFlush(Mockito.any(Alumno.class))).thenReturn(new Alumno());
		
	}
	
	@Test
	public void testFindByAll() {
		//Persona
		Persona persona = new Persona();
		persona.setName("Angelo");
		//Alumno
		List<Alumno> listAlumno = new ArrayList<>();
		Alumno alumno = new Alumno();
		alumno.setPersona(persona);
		listAlumno.add(alumno);
		//Afirmación
		assertEquals(listAlumno.get(0).getPersona().getName(), service.findByAll().get(0).getPersona().getName());
	}
	
	@Test
	public void testFindById() {
		assertNotNull(service.findByIdClase(1L));
	}

	@Test
	public void testSave() {
		assertNotNull(service.save(new Alumno()));
	}

}
