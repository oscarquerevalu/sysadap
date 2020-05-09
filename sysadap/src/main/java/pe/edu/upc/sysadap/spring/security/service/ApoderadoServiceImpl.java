package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.sysadap.spring.security.model.Apoderado;
import pe.edu.upc.sysadap.spring.security.repository.ApoderadoRepository;

@Service
public class ApoderadoServiceImpl implements ApoderadoService {

    @Autowired
    private ApoderadoRepository apoderadoRepository;


	@Override
	public Apoderado save(Apoderado apoderado) {
		// TODO Auto-generated method stub
		return apoderadoRepository.save(apoderado);
	}


	@Override
	public Apoderado findById(Long id) {
		// TODO Auto-generated method stub
		return apoderadoRepository.findOne(id);
	}


	@Override
	public List<Apoderado> findAll() {
		// TODO Auto-generated method stub
		return apoderadoRepository.findAll();
	}
}
