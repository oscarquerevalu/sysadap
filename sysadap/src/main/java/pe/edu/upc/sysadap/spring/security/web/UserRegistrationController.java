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
import pe.edu.upc.sysadap.spring.security.service.UserService;
import pe.edu.upc.sysadap.spring.security.web.dto.PersonaDTO;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public PersonaDTO userRegistrationDto() {
        return new PersonaDTO();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid PersonaDTO userDto,
                                      BindingResult result){

        Persona existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "Ya existe una cuenta registrada con este correo: "+userDto.getEmail());
        }else if (!userDto.getPassword().equals(userDto.getConfirmPassword())){
            result.rejectValue("password", null, "Las contraseñas no coinciden");
        }else if (!userDto.getEmail().equals(userDto.getConfirmEmail())){
            result.rejectValue("email", null, "Los correos no coinciden");
        }

        if (result.hasErrors()){
            return "registration";
        }
        
        existing = new Persona();
        existing.setUsername(userDto.getEmail());
        existing.setName(userDto.getName());
        existing.setPassword(userDto.getPassword());
        existing.setConfirmPassword(userDto.getConfirmPassword());
        existing.setEmail(userDto.getEmail());
        existing.setConfirmEmail(userDto.getConfirmEmail());
        existing.setTelefono(userDto.getTelefono());
        existing.setDocumento(userDto.getDocumento());
        existing.setDireccion(userDto.getDireccion());
        existing.setTerms(userDto.getTerms());
        existing.setRole(userDto.getRole());
        userService.save(existing);
        return "redirect:/registration?success";
    }

}
