package pe.edu.upc.sysadap.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.sysadap.spring.security.model.Apoderado;
import pe.edu.upc.sysadap.spring.security.model.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}

