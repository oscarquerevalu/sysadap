package pe.edu.upc.sysadap.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.sysadap.spring.security.model.EstiloAlumno;

@Repository
public interface EstiloAlumnoRepository extends JpaRepository<EstiloAlumno, Long> {

	//	@Query("select c from EstiloAlumno c where c.id_alumno = :idAlumno and c.fecha BETWEEN :fechaIni and :fechaFin")
	@Query("select c from EstiloAlumno c where c.id_alumno = :idAlumno and STR_TO_DATE(c.fecha, '%d%m%Y') BETWEEN STR_TO_DATE(:fechaIni, '%d%m%Y') and STR_TO_DATE(:fechaFin, '%d%m%Y')")
	List<EstiloAlumno> findByFechasIdAlumno(@Param("fechaIni") String fechaIni, @Param("fechaFin") String fechaFin, @Param("idAlumno") Long idAlumno);

	@Query("select avg(c.valor1) as val1, avg(c.valor2) as val2, avg(c.valor3) as val3, avg(c.valor4) as val4, avg(c.valor5) as val5, avg(c.valor6) as val6, avg(c.valor7) as val7, avg(c.valor8) as val8 from EstiloAlumno c where c.id_alumno = :idAlumno and YEAR(STR_TO_DATE(c.fecha, '%d%m%Y'))= :anio and MONTH(STR_TO_DATE(c.fecha, '%d%m%Y')) = :mes")
	List<PromId> findByFechasIdByMonth(@Param("anio") int anio, @Param("mes") int mes, @Param("idAlumno") Long idAlumno);

	interface PromId {
		Double getVal1();
		Double getVal2();
		Double getVal3();
		Double getVal4();
		Double getVal5();
		Double getVal6();
		Double getVal7();
		Double getVal8();
	}
}

