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

import pe.edu.upc.sysadap.spring.security.model.Competencia;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.CompetenciaRepository;

public class CompetenciaServiceImplTest {
	
	@InjectMocks
	private CompetenciaServiceImpl service = new CompetenciaServiceImpl();
	
	@Mock
	private CompetenciaRepository competenciaRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		//Persona
		Persona persona = new Persona();
		persona.setName("Angelo");
		//Alumno
		List<Competencia> lstCompetencias = new ArrayList<>();
		Competencia competencia = new Competencia();
		lstCompetencias.add(competencia);
		when(competenciaRepository.findAll()).thenReturn(lstCompetencias);
		when(competenciaRepository.save(Mockito.any(Competencia.class))).thenReturn(competencia);
		when(competenciaRepository.saveAndFlush(Mockito.any(Competencia.class))).thenReturn(competencia);
		when(competenciaRepository.findOne(Mockito.any(Long.class))).thenReturn(competencia);
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
	public void testGuardarCompetencia() {
		assertNotNull(service.guardarCompetencia(new Competencia()));
	}

}
