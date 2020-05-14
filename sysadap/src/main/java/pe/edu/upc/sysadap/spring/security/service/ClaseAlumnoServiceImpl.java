package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;
import pe.edu.upc.sysadap.spring.security.repository.ClaseAlumnoRepository;
import pe.edu.upc.sysadap.spring.security.repository.ClaseRepository;

@Service
public class ClaseAlumnoServiceImpl implements ClaseAlumnoService {

    @Autowired
    private ClaseAlumnoRepository claseAlumnoRepository;


	@Override
	public List<ClaseAlumno> findByAll() {
		// TODO Auto-generated method stub
		return claseAlumnoRepository.findAll();
	}
	
	@Override
	public List<ClaseAlumno> findByFechaIdAlumno(String fecha, Long idAlumno) {
		// TODO Auto-generated method stub
		return claseAlumnoRepository.findByFechaIdAlumno(fecha,idAlumno);
	}

	@Override
	public ClaseAlumno guardar(ClaseAlumno claseAlumno) {
		// TODO Auto-generated method stub
		return claseAlumnoRepository.saveAndFlush(claseAlumno);
	}

	@Override
	public List<ClaseAlumno> findByPeriodoIdAlumno(int mes, int anio, Long idAlumno) {
		// TODO Auto-generated method stub
		return claseAlumnoRepository.findByPeriodoIdAlumno(mes, anio, idAlumno);
	}
}
