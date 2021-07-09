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
		
		return competenciaRepository.findAll();
	}


	@Override
	public Competencia findById(Long id) {
		
		return competenciaRepository.findOne(id);
	}


	@Override
	public Competencia guardarCompetencia(Competencia Competencia) {
		
		return competenciaRepository.saveAndFlush(Competencia);
	}
}
