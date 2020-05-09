package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.sysadap.spring.security.model.Competencia;
import pe.edu.upc.sysadap.spring.security.repository.CompetenciaRepository;

@Service
public class CompetenciaServiceImpl implements CompetenciaService {

    @Autowired
    private CompetenciaRepository competenciaRepository;


	@Override
	public List<Competencia> findByAll() {
		// TODO Auto-generated method stub
		return competenciaRepository.findAll();
	}


	@Override
	public Competencia findById(Long id) {
		// TODO Auto-generated method stub
		return competenciaRepository.findOne(id);
	}


	@Override
	public Competencia guardarCompetencia(Competencia Competencia) {
		// TODO Auto-generated method stub
		return competenciaRepository.saveAndFlush(Competencia);
	}
}
