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

import pe.edu.upc.sysadap.spring.security.model.EstiloAlumno;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.EstiloAlumnoRepository;
import pe.edu.upc.sysadap.spring.security.repository.EstiloAlumnoRepository.PromId;

public class EstiloAlumnoServiceImplTest {
	
	@InjectMocks
	private EstiloAlumnoServiceImpl service = new EstiloAlumnoServiceImpl();
	
	@Mock
	private EstiloAlumnoRepository estiloAlumnoRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		//Persona
		Persona persona = new Persona();
		persona.setName("Angelo");
		//Alumno
		List<EstiloAlumno> lstEstiloAlumnos = new ArrayList<>();
		EstiloAlumno estiloAlumno = new EstiloAlumno();
		lstEstiloAlumnos.add(estiloAlumno);
		List<PromId> promIds = new ArrayList<EstiloAlumnoRepository.PromId>();
		when(estiloAlumnoRepository.findAll()).thenReturn(lstEstiloAlumnos);
		when(estiloAlumnoRepository.save(Mockito.any(EstiloAlumno.class))).thenReturn(estiloAlumno);
		when(estiloAlumnoRepository.saveAndFlush(Mockito.any(EstiloAlumno.class))).thenReturn(estiloAlumno);
		when(estiloAlumnoRepository.findOne(Mockito.any(Long.class))).thenReturn(estiloAlumno);
		when(estiloAlumnoRepository.findByFechasIdAlumno(Mockito.anyString(),Mockito.anyString(),Mockito.any(Long.class))).thenReturn(lstEstiloAlumnos);
		when(estiloAlumnoRepository.findByFechasIdByMonth(Mockito.any(Integer.class),Mockito.any(Integer.class),Mockito.any(Long.class))).thenReturn(promIds);
		when(estiloAlumnoRepository.findByPeriodo(Mockito.any(Integer.class),Mockito.any(Integer.class),Mockito.any(Long.class))).thenReturn(promIds);
	}

	@Test
	public void testFindByAll() {
		assertNotNull(service.findByAll());
	}

	@Test
	public void testFindByFechaIdAlumno() {
		assertNotNull(service.findByFechaIdAlumno("02/04/2020", "05/04/2020", 1l));
	}

	@Test
	public void testGuardar() {
		assertNotNull(service.guardar(new EstiloAlumno()));
	}

	@Test
	public void testFindByFechasIdByMonth() {
		assertNotNull(service.findByFechasIdByMonth(1, 1, 1l));
	}

	@Test
	public void testFindByPeriodo() {
		assertNotNull(service.findByPeriodo(1, 1, 1l));
	}

}
