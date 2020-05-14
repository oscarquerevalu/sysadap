package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import pe.edu.upc.sysadap.spring.security.model.Persona;

public interface PersonaService {

    List<Persona> findByAll();
    
    Persona findById(Long id);
    
    Persona findByIdAlumno(Long id);
    
    Persona findByIdProfesor(Long id);
    
    Persona findByIdApoderado(Long id);
    
    List<Persona> findByIdApoderado(String documento);
    
    Persona guardarPersona(Persona p);

}
