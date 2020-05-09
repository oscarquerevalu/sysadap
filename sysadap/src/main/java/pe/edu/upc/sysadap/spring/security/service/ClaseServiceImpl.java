package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.repository.ClaseRepository;

@Service
public class ClaseServiceImpl implements ClaseService {

    @Autowired
    private ClaseRepository claseRepository;


	@Override
	public List<Clase> findByAll() {
		// TODO Auto-generated method stub
		return claseRepository.findAll();
	}


	@Override
	public Clase findById(Long id) {
		// TODO Auto-generated method stub
		return claseRepository.findOne(id);
	}


	@Override
	public Clase guardarClase(Clase clase) {
		// TODO Auto-generated method stub
		return claseRepository.saveAndFlush(clase);
	}
}
