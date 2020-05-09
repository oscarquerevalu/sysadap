package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import pe.edu.upc.sysadap.spring.security.model.EstiloAlumno;
import pe.edu.upc.sysadap.spring.security.repository.EstiloAlumnoRepository.PromId;

public interface EstiloAlumnoService {

    List<EstiloAlumno> findByAll();
    EstiloAlumno guardar(EstiloAlumno estiloAlumno);
    List<EstiloAlumno> findByFechaIdAlumno(String fechaIni, String fechaFin, Long idAlumno);
    List<PromId> findByFechasIdByMonth(int anio, int mes, @Param("idAlumno") Long idAlumno);

}
