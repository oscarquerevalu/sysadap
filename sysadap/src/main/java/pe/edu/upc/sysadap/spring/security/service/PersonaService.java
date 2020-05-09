package pe.edu.upc.sysadap.spring.security.service;

import pe.edu.upc.sysadap.spring.security.model.Alumno;
import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.web.dto.UserRegistrationDto;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface PersonaService {

    List<Persona> findByAll();
    
    Persona findById(Long id);
    
    Persona findByIdAlumno(Long id);
    
    Persona findByIdProfesor(Long id);
    
    List<Persona> findByIdApoderado(String documento);
    
    Persona guardarPersona(Persona p);

}
