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

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.ClaseAlumnoRepository;

public class ClaseAlumnoServiceImplTest {
	
	@InjectMocks
	private ClaseAlumnoServiceImpl service = new ClaseAlumnoServiceImpl();
	
	@Mock
	private ClaseAlumnoRepository claseAlumnoRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		//Persona
		Persona persona = new Persona();
		persona.setName("Angelo");
		//Alumno
		List<ClaseAlumno> lstClaseAlumnos = new ArrayList<>();
		ClaseAlumno claseAlumno = new ClaseAlumno();
		lstClaseAlumnos.add(claseAlumno);
		when(claseAlumnoRepository.findAll()).thenReturn(lstClaseAlumnos);
		when(claseAlumnoRepository.save(Mockito.any(ClaseAlumno.class))).thenReturn(claseAlumno);
		when(claseAlumnoRepository.saveAndFlush(Mockito.any(ClaseAlumno.class))).thenReturn(claseAlumno);
		when(claseAlumnoRepository.findOne(Mockito.any(Long.class))).thenReturn(claseAlumno);
		when(claseAlumnoRepository.findByFechaIdAlumno(Mockito.anyString(), Mockito.any(Long.class), Mockito.any(Long.class))).thenReturn(lstClaseAlumnos);
		when(claseAlumnoRepository.findByPeriodoIdAlumno(Mockito.any(Integer.class), Mockito.any(Integer.class), Mockito.any(Long.class))).thenReturn(lstClaseAlumnos);
		when(claseAlumnoRepository.findByPeriodo(Mockito.any(Integer.class), Mockito.any(Integer.class), Mockito.any(Long.class))).thenReturn(lstClaseAlumnos);
	}
	
	

	@Test
	public void testFindByAll() {
		assertNotNull(service.findByAll());
	}

	@Test
	public void testFindByFechaIdAlumno() {
		assertNotNull(service.findByFechaIdAlumno("", 1L, 1L));
	}

	@Test
	public void testGuardar() {
		assertNotNull(service.guardar(new ClaseAlumno()));
	}

	@Test
	public void testFindByPeriodoIdAlumno() {
		assertNotNull(service.findByPeriodoIdAlumno(1, 1, 1L));
	}

	@Test
	public void testFindByPeriodo() {
		assertNotNull(service.findByPeriodo(1, 1, 1L));
	}

}
