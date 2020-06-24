package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import pe.edu.upc.sysadap.spring.security.model.Clase;

public interface ClaseService {

    List<Clase> findByAll();
    
    Clase findById(Long id);
    
    Clase guardarClase(Clase clase);

}
