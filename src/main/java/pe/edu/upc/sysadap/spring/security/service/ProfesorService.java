package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import pe.edu.upc.sysadap.spring.security.model.Profesor;

public interface ProfesorService {

    Profesor save(Profesor profesor);
    Profesor findByID(Long id);
    List<Profesor> findByAll();
}
