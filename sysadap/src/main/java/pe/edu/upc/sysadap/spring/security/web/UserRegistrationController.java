package pe.edu.upc.sysadap.spring.security.web;

import pe.edu.upc.sysadap.spring.security.constraint.FieldMatch;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.service.UserService;
import pe.edu.upc.sysadap.spring.security.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public Persona userRegistrationDto() {
        return new Persona();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid Persona userDto,
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
        userDto.setUsername(userDto.getEmail());

        userService.save(userDto);
        return "redirect:/registration?success";
    }

}
