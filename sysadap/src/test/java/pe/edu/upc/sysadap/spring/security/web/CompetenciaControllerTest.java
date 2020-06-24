package pe.edu.upc.sysadap.spring.security.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.Model;

import pe.edu.upc.sysadap.spring.security.model.Competencia;
import pe.edu.upc.sysadap.spring.security.service.CompetenciaService;

public class CompetenciaControllerTest {
	
	@InjectMocks
	@Spy
	private CompetenciaController controller;
	
	@Mock
	private CompetenciaService competenciaService;
	
	Model model = mock(Model.class);
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Long id =1L;
		when(competenciaService.findById(id)).thenReturn(new Competencia());
	}

	@Test
	public void testListCompetencias() {
		try {
			assertEquals(new ArrayList<Competencia>(), controller.listCompetencias(model));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Test
	public void testGetProfesor() {
		Long id = 1L;
		assertNotNull(controller.getProfesor(id));
	}

}
