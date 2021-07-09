package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;


	@Override
	public List<Persona> findByAll() {
		
		return personaRepository.findAll();
	}
	
	public List<Persona> findByIdApoderado(String documento){
		return personaRepository.findByIdApoderado(documento);
	}


	@Override
	public Persona findById(Long id) {
		
		return personaRepository.getOne(id);
	}
	
	@Override
	public Persona findByIdAlumno(Long id) {
		
		return personaRepository.findByIdAlumno(id);
	}
	
	@Override
	public Persona findByIdProfesor(Long id) {
		
		return personaRepository.findByIdProfesor(id);
	}

	@Override
	public Persona guardarPersona(Persona p) {
		
		return personaRepository.saveAndFlush(p);
	}

	@Override
	public Persona findByIdApoderado(Long id) {
		
		return personaRepository.findByIdApoderado(id);
	}
}
