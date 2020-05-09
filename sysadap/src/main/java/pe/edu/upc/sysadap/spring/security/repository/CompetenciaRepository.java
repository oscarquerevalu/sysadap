package pe.edu.upc.sysadap.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.sysadap.spring.security.model.Competencia;

@Repository
public interface CompetenciaRepository extends JpaRepository<Competencia, Long> {

//    Persona findByEmail(String email);
//
//    @Query("update Persona u set u.password = :password where u.id = :id")
//    void updatePassword(@Param("password") String password, @Param("id") Long id);
}
