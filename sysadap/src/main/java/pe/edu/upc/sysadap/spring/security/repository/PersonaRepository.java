package pe.edu.upc.sysadap.spring.security.repository;

import java.util.List;

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
    
    @Query("Select u from Persona u where u.profesor.id = :id")
    Persona findByIdProfesor(@Param("id") Long id);
    
    @Query("Select u from Persona u where u.apoderado.id = :id")
    Persona findByIdApoderado(@Param("id") Long id);
    
    @Query("Select u from Persona u where u.apoderado.id is not null and u.documento = :documento")
    List<Persona> findByIdApoderado(@Param("documento") String documento);
}
