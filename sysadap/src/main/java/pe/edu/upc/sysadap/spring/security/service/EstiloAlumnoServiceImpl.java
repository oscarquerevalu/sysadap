package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;
import pe.edu.upc.sysadap.spring.security.model.EstiloAlumno;
import pe.edu.upc.sysadap.spring.security.repository.EstiloAlumnoRepository;

@Service
public class EstiloAlumnoServiceImpl implements EstiloAlumnoService {

    @Autowired
    private EstiloAlumnoRepository estiloAlumnoRepository;


	@Override
	public List<EstiloAlumno> findByAll() {
		// TODO Auto-generated method stub
		return estiloAlumnoRepository.findAll();
	}
	
	@Override
	public List<EstiloAlumno> findByFechaIdAlumno(String fechaIni, String fechaFin, Long idAlumno) {
		// TODO Auto-generated method stub
		return estiloAlumnoRepository.findByFechasIdAlumno(fechaIni, fechaFin, idAlumno);
	}

	@Override
	public EstiloAlumno guardar(EstiloAlumno estiloAlumno) {
		// TODO Auto-generated method stub
		return estiloAlumnoRepository.saveAndFlush(estiloAlumno);
	}
}
