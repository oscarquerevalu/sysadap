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
import pe.edu.upc.sysadap.spring.security.service.AlumnoService;
import pe.edu.upc.sysadap.spring.security.service.PersonaService;
import pe.edu.upc.sysadap.spring.security.web.dto.AlumnoDto;
import pe.edu.upc.sysadap.spring.security.web.dto.ProfesorDto;

@Controller
@RequestMapping("/alumnoDTO")
public class AlumnoDtoController {
    
    @Autowired
    private PersonaService personaService;
    
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping(value="guardar")
    public String registerProfesor(Model model,@ModelAttribute("alumno") @Valid AlumnoDto alumnoDTO,
                                      BindingResult result){

        if (result.hasErrors()){
            return "admin/mantAlumno :: alumno-form";
        }
        
        Persona persona = personaService.findById(alumnoDTO.getId());
        persona.setTelefono(alumnoDTO.getTelefono());
        persona.setDireccion(alumnoDTO.getDireccion());
        persona.setName(alumnoDTO.getName());
        persona.setDocumento(alumnoDTO.getDocumento());
        personaService.guardarPersona(persona);

        return "redirect:/mantAlumno?success";
    }

}
