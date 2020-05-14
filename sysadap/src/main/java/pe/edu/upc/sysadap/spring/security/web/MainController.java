package pe.edu.upc.sysadap.spring.security.web;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.context.Context;

import pe.edu.upc.sysadap.spring.security.model.Alumno;
import pe.edu.upc.sysadap.spring.security.model.Apoderado;
import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.Competencia;
import pe.edu.upc.sysadap.spring.security.model.Mail;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.model.Profesor;
import pe.edu.upc.sysadap.spring.security.repository.ClaseRepository;
import pe.edu.upc.sysadap.spring.security.repository.CompetenciaRepository;
import pe.edu.upc.sysadap.spring.security.repository.ProfesorRepository;
import pe.edu.upc.sysadap.spring.security.repository.UserRepository;
import pe.edu.upc.sysadap.spring.security.repository.EstiloAlumnoRepository.PromId;
import pe.edu.upc.sysadap.spring.security.service.ApoderadoService;
import pe.edu.upc.sysadap.spring.security.service.ApoderadoServiceImpl;
import pe.edu.upc.sysadap.spring.security.service.PersonaService;
import pe.edu.upc.sysadap.spring.security.service.ProfesorService;
import pe.edu.upc.sysadap.spring.security.service.UserService;
import pe.edu.upc.sysadap.spring.security.web.dto.AlumnoDto;
import pe.edu.upc.sysadap.spring.security.web.dto.ClaseDto;
import pe.edu.upc.sysadap.spring.security.web.dto.ProfesorDto;

@Controller
public class MainController {
	
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private ClaseRepository claseRepository;
	@Autowired
    private CompetenciaRepository competenciasRepository;
	@Autowired
    private ProfesorService profesorService;
	@Autowired
    private PersonaService personaService;
	@Autowired
    private ApoderadoService apoderadoService;
	
//	private final static String urlCntx = "http://34.195.249.193";
//	private final static String urlCntx = "http://ec2-52-86-169-48.compute-1.amazonaws.com";
	private final static String urlCntx = "http://localhost:8080";

    @GetMapping("/")
    public String root(Model model, HttpSession session) {
    	System.out.println("--------------------INDEX");
    	setDatosSession(session);
    	Persona persona = (Persona) session.getAttribute("persona");
    	model.addAttribute("aulas", persona.getProfesor().getClase());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @GetMapping("/accessdenied")
    public String accessdenied(Model model) {
        return "accessdenied";
    }
    
    @GetMapping("/viewAlumnos")
    public String viewAlumnos(Model model, HttpSession session) {
    	setDatosSession(session);
		Persona apoderadoPer =  (Persona) session.getAttribute("persona");
		Apoderado apoderado = apoderadoService.findById(apoderadoPer.getApoderado().getId());
		Set<Alumno> alumnos = apoderado.getAlumnos();
		for (Alumno alumno : alumnos) {
			Persona persona = personaService.findByIdAlumno(alumno.getId());
			alumno.setPersona(persona);
		}
		model.addAttribute("alumnos", alumnos);
        return "indexApoderado";
    }
    
    @GetMapping("/viewAdmin")
    public String viewAdmin(Model model, HttpSession session) {
    	setDatosSession(session);
        return "admin/indexAdmin";
    }
    
    @GetMapping("/mantProf")
    public String viewProf(Model model, HttpSession session) {
    	setDatosSession(session);
    	model.addAttribute("profesor", new ProfesorDto());
        return "admin/mantProfesor";
    }
    
    @GetMapping("/mantAlumno")
    public String mantAlumno(Model model, HttpSession session) {
    	setDatosSession(session);
    	List<Clase> clases = claseRepository.findAll();
    	for (Clase clase : clases) {
			clase.setAlumnos(null);
			clase.setCompetencias(null);
			clase.setProfesor(null);
			clase.setRecursos(null);
		}
    	model.addAttribute("alumno", new AlumnoDto());
    	model.addAttribute("clases", clases);
        return "admin/mantAlumno";
    }
    
    @GetMapping("/mantAulSec")
    public String mantAulSec(Model model, HttpSession session) {
    	setDatosSession(session);
    	model.addAttribute("clase", new ClaseDto());
    	List<Competencia> competencias = competenciasRepository.findAll();
    	model.addAttribute("competencias", competencias);
    	
    	List<Profesor> listaProfesores = profesorService.findByAll();
		
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
		model.addAttribute("profesores", listaProfesores);
        return "admin/mantAulSec";
    }
    
    @GetMapping("/mantComp")
    public String mantComp(Model model, HttpSession session) {
    	setDatosSession(session);
    	model.addAttribute("competencia", new Competencia());
        return "admin/mantComp";
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
    	session.setAttribute("urlCntx", urlCntx);
    }
}
