package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.sysadap.spring.security.model.Profesor;
import pe.edu.upc.sysadap.spring.security.repository.ProfesorRepository;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;


	@Override
	public Profesor save(Profesor profesor) {
		// TODO Auto-generated method stub
		return profesorRepository.save(profesor);
	}
	
	@Override
	public List<Profesor> findByAll() {
		// TODO Auto-generated method stub
		return profesorRepository.findAll();
	}

	@Override
	public Profesor findByID(Long id) {
		// TODO Auto-generated method stub
		return profesorRepository.findOne(id);
	}
}
