package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;
import pe.edu.upc.sysadap.spring.security.repository.ClaseAlumnoRepository;

@Service
public class ClaseAlumnoServiceImpl implements ClaseAlumnoService {

    @Autowired
    private ClaseAlumnoRepository claseAlumnoRepository;


	@Override
	public List<ClaseAlumno> findByAll() {
		
		return claseAlumnoRepository.findAllMax100();
	}
	
	@Override
	public List<ClaseAlumno> findByFechaIdAlumno(String fecha, Long idAlumno, Long idClase) {
		
		return claseAlumnoRepository.findByFechaIdAlumno(fecha,idAlumno,idClase);
	}

	@Override
	public ClaseAlumno guardar(ClaseAlumno claseAlumno) {
		
		return claseAlumnoRepository.saveAndFlush(claseAlumno);
	}

	@Override
	public List<ClaseAlumno> findByPeriodoIdAlumno(int mes, int anio, Long idAlumno) {
		
		return claseAlumnoRepository.findByPeriodoIdAlumno(mes, anio, idAlumno);
	}

	@Override
	public List<ClaseAlumno> findByPeriodo(int mes, int anio, Long idClase) {
		
		return claseAlumnoRepository.findByPeriodo(mes, anio, idClase);
	}
}
