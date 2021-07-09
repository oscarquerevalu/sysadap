package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumnoActividades;
import pe.edu.upc.sysadap.spring.security.repository.ClaseAlumnoActividadesRepository;
import pe.edu.upc.sysadap.spring.security.repository.ClaseAlumnoActividadesRepository.Actidad;

@Service
public class ClaseAlumnoActividadesServiceImpl implements ClaseAlumnoActividadesService {

    @Autowired
    private ClaseAlumnoActividadesRepository claseAlumnoActividadesRepository;

	@Override
	public void guardarActividad(ClaseAlumnoActividades c) {
		
		claseAlumnoActividadesRepository.saveAndFlush(c);
	}

	@Override
	public List<ClaseAlumnoActividades> findAll() {
		
		return claseAlumnoActividadesRepository.findAll();
	}
	
	@Override
	public List<ClaseAlumnoActividades> findByIdClasealumno(Long idClasealumno) {
		
		return claseAlumnoActividadesRepository.findByIdClasealumno(idClasealumno);
	}

	@Override
	public List<Actidad> findByIdClasealumnoActividad(Long idClasealumno) {
		
		return claseAlumnoActividadesRepository.findByIdClasealumnoActividad(idClasealumno);
	}

}
