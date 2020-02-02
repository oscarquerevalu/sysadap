package pe.edu.upc.sysadap.spring.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
    	System.out.println("--------------------INDEX");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @GetMapping("/viewAlumnos")
    public String viewAlumnos(Model model) {
        return "indexApoderado";
    }

    @GetMapping("/user")
    public String userIndex() {
    	System.out.println("--------------------USER");
        return "user/index";
    }
}
