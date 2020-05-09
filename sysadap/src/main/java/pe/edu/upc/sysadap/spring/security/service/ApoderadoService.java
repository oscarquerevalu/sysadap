package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import pe.edu.upc.sysadap.spring.security.model.Apoderado;

public interface ApoderadoService {

    Apoderado save(Apoderado apoderado);
    
    Apoderado findById(Long id);
    
    List<Apoderado> findAll();
}
