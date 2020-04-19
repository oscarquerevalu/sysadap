package pe.edu.upc.sysadap.spring.security.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class AlumnoControllerTest extends SpringBootHelloWorldTests{
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetClaseAlumnos() throws Exception {
//		HashMap<String, String> obj = new HashMap<>();
//		obj.put("nombre", "Angelo");
//		obj.put("edad", "22");
//		
//		String nombre = "abc";
//		
//		if(obj.containsKey("edad") && "abc".contains(nombre)) {
//			System.out.println(obj.get("edad"));
//			
//		}
//		
//		mockMvc.perform(get("/alumno/getDataClaseAlumnos")).andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"));

	}
	
	@Test
	public void testListAlumnos() throws Exception {
//		mockMvc.perform(get("/alumno/listAlumnos")).andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"));

	}

}
