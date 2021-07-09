package pe.edu.upc.sysadap.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;
import pe.edu.upc.sysadap.spring.security.model.ClaseAlumnoActividades;

@Repository
public interface ClaseAlumnoActividadesRepository extends JpaRepository<ClaseAlumnoActividades, Long> {
	@Query("select c from ClaseAlumnoActividades c where c.id_clasealumno = :idClasealumno")
	List<ClaseAlumnoActividades> findByIdClasealumno(@Param("idClasealumno") Long idClasealumno);
	
	@Query("select distinct c.id_actividad as actividad from ClaseAlumnoActividades c where c.id_clasealumno = :idClasealumno")
	List<Actidad> findByIdClasealumnoActividad(@Param("idClasealumno") Long idClasealumno);
	
	interface Actidad {
		Long getActividad();
	}
	
}

