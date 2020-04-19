package pe.edu.upc.sysadap.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.sysadap.spring.security.model.Apoderado;

@Repository
public interface ApoderadoRepository extends JpaRepository<Apoderado, Long> {
}

