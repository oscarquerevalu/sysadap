package pe.edu.upc.sysadap.spring.security.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.Competencia;
import pe.edu.upc.sysadap.spring.security.service.ClaseService;
import pe.edu.upc.sysadap.spring.security.service.CompetenciaService;

@RestController
@RequestMapping("/clase")
public class ClaseController {
	Logger logger = LoggerFactory.getLogger(ClaseController.class);

    @Autowired
    private ClaseService claseService;
    
    @Autowired
    private CompetenciaService competenciaService;

    @GetMapping(value="/listClases", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Clase> listClases(Model model) {
    	
    	List<Clase> lista = new ArrayList<Clase>();
    	try {
    		lista = claseService.findByAll();
    		for (Clase clase : lista) {
				clase.setAlumnos(null);
				clase.setProfesor(null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("ClaseController listClases", e);
		}
    	
        return lista;
    }
    
    @GetMapping(value = "/getClase/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtener Clase por id")
    public Map<String, Object> getProfesor(@ApiParam(value = "Id clase", required = true) @PathVariable Long id) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<Competencia> lstCompetencia = new ArrayList<Competencia>();
    	Clase clase = claseService.findById(id);
    	map.put("id", id);
    	map.put("nombre", clase.getNombre());
    	map.put("edad", clase.getEdad());
    	map.put("idProfesor", clase.getProfesor().getId());
    	
    	String[] listaCompetencias = clase.getCompetencias() != null? clase.getCompetencias().split(",") : new String[0];
    	for (String idComp : listaCompetencias) {
    		if(!"".equals(idComp.trim())) {
    			Competencia comp = competenciaService.findById(new Long(idComp));
    			if(comp !=null)
    				lstCompetencia.add(comp);
    		}
		}
    	
    	map.put("competencias", lstCompetencia);
    	
    	return map;
    }
    
	
}
