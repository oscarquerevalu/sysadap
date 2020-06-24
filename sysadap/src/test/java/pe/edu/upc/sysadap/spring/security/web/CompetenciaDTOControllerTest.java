package pe.edu.upc.sysadap.spring.security.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import pe.edu.upc.sysadap.spring.security.model.Competencia;
import pe.edu.upc.sysadap.spring.security.service.CompetenciaService;
import pe.edu.upc.sysadap.spring.security.web.dto.CompentenciaDto;

public class CompetenciaDTOControllerTest {
	
	@InjectMocks
	@Spy
	private CompetenciaDtoController controller;
	
	@Mock
	private CompetenciaService competenciaService;
	
	Model model = mock(Model.class);
	
	BindingResult result = mock(BindingResult.class);
	
	CompentenciaDto competenciaDTO = new CompentenciaDto();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(competenciaService.findById(Mockito.anyLong())).thenReturn(new Competencia());
	}
	
	@Test
	public void testShowRegistrationForm() {
		assertEquals("registration", controller.showRegistrationForm(model));
	}

	@Test
	public void testRegisterProfesor() {
		when(result.hasErrors()).thenReturn(true);
		assertEquals("admin/mantComp :: competencia-form", controller.registerProfesor(model, competenciaDTO, result));
	}
	
	@Test
	public void testRegisterProfesor2() {
		when(result.hasErrors()).thenReturn(false);
		competenciaDTO.setId(1L);
		competenciaDTO.setNombre("Comp1");;
		assertEquals("redirect:/mantComp?success", controller.registerProfesor(model, competenciaDTO, result));
	}

}
