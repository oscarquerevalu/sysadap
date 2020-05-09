package pe.edu.upc.sysadap.spring.security.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.service.ClaseService;
import pe.edu.upc.sysadap.spring.security.service.PersonaService;
import pe.edu.upc.sysadap.spring.security.service.ProfesorService;
import pe.edu.upc.sysadap.spring.security.web.dto.ClaseDto;
import pe.edu.upc.sysadap.spring.security.web.dto.ProfesorDto;

@Controller
@RequestMapping("/claseDTO")
public class ClaseDtoController {
    
    @Autowired
    private ClaseService claseService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping(value="guardar")
    public String registerProfesor(Model model,@ModelAttribute("clase") @Valid ClaseDto claseDto,
    								@RequestParam(value = "checkCompetencia" , required = false) Long[] competencias ,
                                      BindingResult result){

        if (result.hasErrors()){
            return "admin/mantAulSec :: clase-form";
        }
        
        Clase clase = claseService.findById(claseDto.getId());
        clase.setNombre(claseDto.getNombre());
        clase.setEdad(claseDto.getEdad());
        String strCompetencias = "";
        
        if(competencias != null) {
        	for (Long long1 : competencias) {
            	if(strCompetencias == "") {
            		strCompetencias = long1.toString();	
            	}else {
            		strCompetencias = strCompetencias.concat(","+long1.toString());
            	}
    		}
        }
        
        clase.setCompetencias(strCompetencias);
        claseService.guardarClase(clase);

        return "redirect:/mantAulSec?success";
    }

}
