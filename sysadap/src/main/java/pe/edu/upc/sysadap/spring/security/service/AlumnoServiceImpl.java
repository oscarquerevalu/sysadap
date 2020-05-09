package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.sysadap.spring.security.model.Alumno;
import pe.edu.upc.sysadap.spring.security.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;


	@Override
	public List<Alumno> findByAll() {
		// TODO Auto-generated method stub
		return alumnoRepository.findAll();
	}


	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		return alumnoRepository.saveAndFlush(alumno);
	}


	@Override
	public List<Alumno> findByIdClase(Long id) {
		// TODO Auto-generated method stub
		return alumnoRepository.findByIdClase(id);
	}
	
}
