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

import pe.edu.upc.sysadap.spring.security.model.Apoderado;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.ApoderadoRepository;

public class ApoderadoServiceImplTest {
	
	@InjectMocks
	private ApoderadoServiceImpl service = new ApoderadoServiceImpl();
	
	@Mock
	private ApoderadoRepository apoderadoRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		//Persona
		Persona persona = new Persona();
		persona.setName("Angelo");
		//Alumno
		List<Apoderado> listApoderados = new ArrayList<>();
		Apoderado apoderado = new Apoderado();
		listApoderados.add(apoderado);
		when(apoderadoRepository.findAll()).thenReturn(listApoderados);
		when(apoderadoRepository.save(Mockito.any(Apoderado.class))).thenReturn(apoderado);
		when(apoderadoRepository.findOne(Mockito.any(Long.class))).thenReturn(apoderado);
	}

	@Test
	public void testSave() {
		assertNotNull(service.save(new Apoderado()));
	}

	@Test
	public void testFindById() {
		assertNotNull(service.findById(1L));
	}

	@Test
	public void testFindAll() {
		assertNotNull(service.findAll());
	}

}
