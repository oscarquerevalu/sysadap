package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumnoActividades;

public interface ClaseAlumnoActividadesService {

	void guardarActividad(ClaseAlumnoActividades c);
	List<ClaseAlumnoActividades> findAll();
	List<ClaseAlumnoActividades> findByIdClasealumno(Long idClasealumno);
}
