package pe.edu.upc.sysadap.spring.security.service.test;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumnoActividades;
import pe.edu.upc.sysadap.spring.security.repository.ClaseAlumnoActividadesRepository;
import pe.edu.upc.sysadap.spring.security.service.ClaseAlumnoActividadesServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClaseAlumnoActividadesServiceImplTest {

	@InjectMocks
	private ClaseAlumnoActividadesServiceImpl service = new ClaseAlumnoActividadesServiceImpl();
	@Mock
	private ClaseAlumnoActividadesRepository claseAlumnoActividadesRepository;
	
	@Before
	public void init() {
	
		ClaseAlumnoActividades Clase = new ClaseAlumnoActividades();
		Clase.setId(10L);
		when(claseAlumnoActividadesRepository.saveAndFlush(Mockito.any(ClaseAlumnoActividades.class))).thenReturn(Clase);
		
		List<ClaseAlumnoActividades> listClase = new ArrayList<ClaseAlumnoActividades>();
		listClase.add(Clase);
		
		when(claseAlumnoActividadesRepository.findAll()).thenReturn(listClase);
	
		when(claseAlumnoActividadesRepository.findByIdClasealumno(Mockito.anyLong())).thenReturn(listClase);
	}
	
	@Test
	public void testGuardarActividad() {
		service.guardarActividad(Mockito.any(ClaseAlumnoActividades.class));
	}

	@Test
	public void testFindAll() {
		
		List<ClaseAlumnoActividades> listClase = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades Clase = new ClaseAlumnoActividades();
		Clase.setId(10L);
		listClase.add(Clase);
		
		assertEquals(Clase.getId(),service.findAll().get(0).getId());
	}

	@Test
	public void testFindByIdClasealumno() {
		
		List<ClaseAlumnoActividades> listClase = new ArrayList<ClaseAlumnoActividades>();
		ClaseAlumnoActividades Clase = new ClaseAlumnoActividades();
		Clase.setId(10L);
		listClase.add(Clase);
		
		assertEquals(Clase.getId(),service.findByIdClasealumno(Mockito.anyLong()).get(0).getId());
	}

}
