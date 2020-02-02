package pe.edu.upc.sysadap.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;

@Repository
public interface ClaseAlumnoRepository extends JpaRepository<ClaseAlumno, Long> {
	
	@Query("select c from ClaseAlumno c where c.fecha = :fecha and c.id_alumno = :idAlumno")
	List<ClaseAlumno> findByFechaIdAlumno(@Param("fecha") String fecha, @Param("idAlumno") Long idAlumno);

}

