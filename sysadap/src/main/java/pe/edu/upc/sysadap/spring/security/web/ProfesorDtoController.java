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

import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.service.PersonaService;
import pe.edu.upc.sysadap.spring.security.service.ProfesorService;
import pe.edu.upc.sysadap.spring.security.web.dto.ProfesorDto;

@Controller
@RequestMapping("/profesorDTO")
public class ProfesorDtoController {
    
    @Autowired
    private PersonaService personaService;
    
    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping(value="guardar")
    public String registerProfesor(Model model,@ModelAttribute("profesor") @Valid ProfesorDto profesorDTO,
                                      BindingResult result){

        if (result.hasErrors()){
            return "admin/mantProfesor :: profesor-form";
        }
        
        Persona persona = personaService.findById(profesorDTO.getId());
        persona.setTelefono(profesorDTO.getTelefono());
        persona.setDireccion(profesorDTO.getDireccion());
        persona.setName(profesorDTO.getName());
        persona.setDocumento(profesorDTO.getDocumento());
        personaService.guardarPersona(persona);

        return "redirect:/mantProf?success";
    }

}
