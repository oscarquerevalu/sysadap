package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import pe.edu.upc.sysadap.spring.security.model.Competencia;

public interface CompetenciaService {

    List<Competencia> findByAll();
    
    Competencia findById(Long id);
    
    Competencia guardarCompetencia(Competencia Competencia);

}
