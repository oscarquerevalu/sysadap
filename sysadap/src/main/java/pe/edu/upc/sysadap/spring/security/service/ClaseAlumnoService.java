package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;

public interface ClaseAlumnoService {

    List<ClaseAlumno> findByAll();
    List<ClaseAlumno> findByFechaIdAlumno(String fecha, Long idAlumno, Long idClase);
    ClaseAlumno guardar(ClaseAlumno claseAlumno);
    List<ClaseAlumno> findByPeriodoIdAlumno(int mes, int anio, Long idAlumno);
    List<ClaseAlumno> findByPeriodo(int mes, int anio, Long idClase);

}
