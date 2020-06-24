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

import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.ClaseRepository;

public class ClaseServiceImplTest {
	
	@InjectMocks
	private ClaseServiceImpl service = new ClaseServiceImpl();
	
	@Mock
	private ClaseRepository claseRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		//Persona
		Persona persona = new Persona();
		persona.setName("Angelo");
		//Alumno
		List<Clase> lstClases = new ArrayList<>();
		Clase clase = new Clase();
		lstClases.add(clase);
		when(claseRepository.findAll()).thenReturn(lstClases);
		when(claseRepository.save(Mockito.any(Clase.class))).thenReturn(clase);
		when(claseRepository.saveAndFlush(Mockito.any(Clase.class))).thenReturn(clase);
		when(claseRepository.findOne(Mockito.any(Long.class))).thenReturn(clase);
	}
	
	
	@Test
	public void testFindByAll() {
		assertNotNull(service.findByAll());
	}

	@Test
	public void testFindById() {
		assertNotNull(service.findById(1L));
	}

	@Test
	public void testGuardarClase() {
		assertNotNull(service.guardarClase(new Clase()));
	}

}
