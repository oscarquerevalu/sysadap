package pe.edu.upc.sysadap.spring.security.service;

import static org.junit.Assert.assertNotNull;
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
import pe.edu.upc.sysadap.spring.security.model.Profesor;
import pe.edu.upc.sysadap.spring.security.repository.ProfesorRepository;

public class ProfesorServiceImplTest {
	
	@InjectMocks
	private ProfesorServiceImpl service = new ProfesorServiceImpl();
	
	@Mock
	private ProfesorRepository profesorRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		//Persona
		Persona persona = new Persona();
		persona.setName("Angelo");
		//Alumno
		List<Profesor> lstProfesores = new ArrayList<>();
		Profesor profesor = new Profesor();
		lstProfesores.add(profesor);
		when(profesorRepository.findAll()).thenReturn(lstProfesores);
		when(profesorRepository.save(Mockito.any(Profesor.class))).thenReturn(profesor);
		when(profesorRepository.saveAndFlush(Mockito.any(Profesor.class))).thenReturn(profesor);
		when(profesorRepository.findOne(Mockito.any(Long.class))).thenReturn(profesor);
	}

	@Test
	public void testSave() {
		assertNotNull(service.save(new Profesor()));
	}

	@Test
	public void testFindByAll() {
		assertNotNull(service.findByAll());
	}

	@Test
	public void testFindByID() {
		assertNotNull(service.findByID(1L));
	}

}
