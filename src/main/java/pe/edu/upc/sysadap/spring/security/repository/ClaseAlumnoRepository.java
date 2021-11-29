package pe.edu.upc.sysadap.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;

@Repository
public interface ClaseAlumnoRepository extends JpaRepository<ClaseAlumno, Long> {
	
	@Query("select c from ClaseAlumno c where c.fecha = :fecha and c.id_alumno = :idAlumno and c.id_clase = :idClase")
	List<ClaseAlumno> findByFechaIdAlumno(@Param("fecha") String fecha, @Param("idAlumno") Long idAlumno, @Param("idClase") Long idClase);
	
	@Query("select c from ClaseAlumno c where c.id_alumno = :idAlumno and YEAR(STR_TO_DATE(c.fecha, '%d%m%Y'))= :anio and MONTH(STR_TO_DATE(c.fecha, '%d%m%Y')) = :mes")
	List<ClaseAlumno> findByPeriodoIdAlumno(@Param("mes") int mes,@Param("anio") int anio, @Param("idAlumno") Long idAlumno);
	
	@Query("select c from ClaseAlumno c where c.id_clase = :idClase and YEAR(STR_TO_DATE(c.fecha, '%d%m%Y'))= :anio and MONTH(STR_TO_DATE(c.fecha, '%d%m%Y')) = :mes")
	List<ClaseAlumno> findByPeriodo(@Param("mes") int mes,@Param("anio") int anio,@Param("idClase")  Long idClase);
	
	@Query(nativeQuery = true, value = "select * from clase_alumno c LIMIT 100")
	List<ClaseAlumno> findAllMax100();

}

