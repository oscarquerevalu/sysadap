package pe.edu.upc.sysadap.spring.security.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.UserRepository;
import pe.edu.upc.sysadap.spring.security.service.UserService;

@Controller
public class MainController {
	
	@Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String root(HttpSession session) {
    	System.out.println("--------------------INDEX");
    	setDatosSession(session);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @GetMapping("/viewAlumnos")
    public String viewAlumnos(Model model, HttpSession session) {
    	setDatosSession(session);
        return "indexApoderado";
    }
    
    @GetMapping("/viewEA")
    public String viewEA(Model model, HttpSession session) {
    	setDatosSession(session);
        return "indexEstiloAprendisaje";
    }

    @GetMapping("/user")
    public String userIndex() {
    	System.out.println("--------------------USER");
        return "user/index";
    }
    
    private void setDatosSession(HttpSession session) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
    	System.out.println("currentPrincipalName: "+currentPrincipalName);
    	Persona persona = userRepository.findByEmail(currentPrincipalName);
    	session.setAttribute("persona", persona);
    }
}
