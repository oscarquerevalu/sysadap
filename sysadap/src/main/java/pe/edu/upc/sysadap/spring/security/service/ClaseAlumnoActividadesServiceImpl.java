package pe.edu.upc.sysadap.spring.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.sysadap.spring.security.model.ClaseAlumnoActividades;
import pe.edu.upc.sysadap.spring.security.repository.ClaseAlumnoActividadesRepository;

@Service
public class ClaseAlumnoActividadesServiceImpl implements ClaseAlumnoActividadesService {

    @Autowired
    private ClaseAlumnoActividadesRepository claseAlumnoActividadesRepository;

	@Override
	public void guardarActividad(ClaseAlumnoActividades c) {
		// TODO Auto-generated method stub
		claseAlumnoActividadesRepository.saveAndFlush(c);
	}

	@Override
	public List<ClaseAlumnoActividades> findAll() {
		// TODO Auto-generated method stub
		return claseAlumnoActividadesRepository.findAll();
	}
	
	@Override
	public List<ClaseAlumnoActividades> findByIdClasealumno(Long idClasealumno) {
		// TODO Auto-generated method stub
		return claseAlumnoActividadesRepository.findByIdClasealumno(idClasealumno);
	}

}
