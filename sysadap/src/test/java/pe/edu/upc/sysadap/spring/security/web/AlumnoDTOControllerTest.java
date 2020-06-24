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

import pe.edu.upc.sysadap.spring.security.model.Alumno;
import pe.edu.upc.sysadap.spring.security.model.Apoderado;
import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.service.AlumnoService;
import pe.edu.upc.sysadap.spring.security.service.ApoderadoService;
import pe.edu.upc.sysadap.spring.security.service.ClaseService;
import pe.edu.upc.sysadap.spring.security.service.PersonaService;
import pe.edu.upc.sysadap.spring.security.web.dto.AlumnoDto;

public class AlumnoDTOControllerTest {
	
	@InjectMocks
	@Spy
	private AlumnoDtoController controller;
	
	@Mock
	private PersonaService personaService;
	
	@Mock
	private AlumnoService alumnoService;
	
	@Mock
	private ApoderadoService apoderadoService;
	
	@Mock
	private ClaseService claseService;
	
	Model model = mock(Model.class);
	
	BindingResult result = mock(BindingResult.class);
	
	AlumnoDto alumnoDTO = new AlumnoDto();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		Persona persona = new Persona();
		persona.setId(1L);
		persona.setName("Oscar");
		persona.setAlumno(new Alumno());
		when(personaService.findById(Mockito.anyLong())).thenReturn(persona);
		when(personaService.guardarPersona(Mockito.any())).thenReturn(persona);
		when(alumnoService.save(Mockito.any())).thenReturn(new Alumno());
		when(apoderadoService.findById(Mockito.anyLong())).thenReturn(new Apoderado());
		when(claseService.findById(Mockito.anyLong())).thenReturn(new Clase());
		
	}

	@Test
	public void testShowRegistrationForm() {
		assertEquals("registration", controller.showRegistrationForm(model));
	}

	@Test
	public void testRegisterProfesor() {
		when(result.hasErrors()).thenReturn(true);
		assertEquals("admin/mantAlumno :: alumno-form", controller.registerProfesor(model, alumnoDTO, result));
	}
	
	@Test
	public void testRegisterProfesor2() {
		when(result.hasErrors()).thenReturn(false);
		alumnoDTO.setId(1L);
		alumnoDTO.setDocApoderado(1L);
		alumnoDTO.setIdClase(1L);
		assertEquals("redirect:/mantAlumno?success", controller.registerProfesor(model, alumnoDTO, result));
	}

}
