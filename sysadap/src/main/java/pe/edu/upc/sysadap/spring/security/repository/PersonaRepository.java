package pe.edu.upc.sysadap.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.sysadap.spring.security.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

//    Persona findByEmail(String email);
//
    @Query("Select u from Persona u where u.alumno.id = :id")
    Persona findByIdAlumno(@Param("id") Long id);
}
