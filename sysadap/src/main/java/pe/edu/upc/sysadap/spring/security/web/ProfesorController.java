package pe.edu.upc.sysadap.spring.security.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.model.Profesor;
import pe.edu.upc.sysadap.spring.security.service.PersonaService;
import pe.edu.upc.sysadap.spring.security.service.ProfesorService;
import pe.edu.upc.sysadap.spring.security.web.dto.ProfesorDto;

@RestController
@RequestMapping("/profesor")
@Api(value="Controlador para Profesores", description="Operaciones de carga de datos")
public class ProfesorController {

	Logger logger = LoggerFactory.getLogger(ProfesorController.class);

    @Autowired
    private ProfesorService profesorService;
    
    @Autowired
    private PersonaService personaService;
    
    @ModelAttribute("profesor")
    public ProfesorDto profesorDto() {
        return new ProfesorDto();
    }

    @GetMapping(value="/listProfesores", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ver lista los profesores ")
    public List<Profesor> listaProfesores(Model model) {
    	
    	List<Profesor> listaProfesores = new ArrayList<Profesor>();
    	System.out.println("listaProfesores");
    	try {
    		listaProfesores = profesorService.findByAll();
    		
    		for (Profesor profesor : listaProfesores) {
    			profesor.setPersonas(null);
    			profesor.setClase(null);
    			profesor.setPersona(personaService.findByIdProfesor(profesor.getId()));
    			profesor.setPersonas(null);
    			if(profesor.getPersona()!=null) {
    				profesor.getPersona().setAlumno(null);
        			profesor.getPersona().setProfesor(null);
    			}
			} 
    		
    		listaProfesores = listaProfesores.stream().filter(profesor -> profesor.getPersona()!=null).collect(Collectors.toList());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("ProfesorController listProfesores", e);
			
		}
        return listaProfesores;
    }
    
    @GetMapping(value = "/getProfesor/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ver los alumnos por fecha de clase")
    public Map<String, Object> getProfesor(@ApiParam(value = "Id persona", required = true) @PathVariable Long id) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	Persona persona = personaService.findById(id);
    	map.put("id", id);
    	map.put("nombre", persona.getName());
    	map.put("telefono", persona.getTelefono());
    	map.put("documento", persona.getDocumento());
    	map.put("direccion", persona.getDireccion());
    	return map;
    }
    
    @PostMapping(value = "/guardar")
    public String guardarProfesor(@ModelAttribute("profesor") @Valid ProfesorDto profesor,
                                      BindingResult result,
                                      Model model){

        if (result.hasErrors()){
        	model.addAttribute("hasError", true);
            return "admin/mantProfesor";
        } 

//        profesorService.save(profesor);
        return "redirect:/mantProfesor?success";
    }
}
