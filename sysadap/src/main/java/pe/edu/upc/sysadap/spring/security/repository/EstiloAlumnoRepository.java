package pe.edu.upc.sysadap.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;
import pe.edu.upc.sysadap.spring.security.model.EstiloAlumno;

@Repository
public interface EstiloAlumnoRepository extends JpaRepository<EstiloAlumno, Long> {
	
//	@Query("select c from EstiloAlumno c where c.id_alumno = :idAlumno and c.fecha BETWEEN :fechaIni and :fechaFin")
	@Query("select c from EstiloAlumno c where c.id_alumno = :idAlumno and STR_TO_DATE(c.fecha, '%d%m%Y') BETWEEN STR_TO_DATE(:fechaIni, '%d%m%Y') and STR_TO_DATE(:fechaFin, '%d%m%Y')")
	List<EstiloAlumno> findByFechasIdAlumno(@Param("fechaIni") String fechaIni, @Param("fechaFin") String fechaFin, @Param("idAlumno") Long idAlumno);

}

