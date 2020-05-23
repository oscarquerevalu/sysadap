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
import pe.edu.upc.sysadap.spring.security.model.Competencia;
import pe.edu.upc.sysadap.spring.security.service.CompetenciaService;

@RestController
@RequestMapping("/competencia")
public class CompetenciaController {

	Logger logger = LoggerFactory.getLogger(CompetenciaController.class);
	
    @Autowired
    private CompetenciaService CompetenciaService;

    @GetMapping(value="/listCompetencias", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Competencia> listCompetencias(Model model) {
    	
    	List<Competencia> lista = new ArrayList<Competencia>();
    	try {
    		lista = CompetenciaService.findByAll();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("CompetenciaController listCompetencias", e);
		}
    	
        return lista;
    }
    
    @GetMapping(value = "/getCompetencia/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtener Competencia por id")
    public Map<String, Object> getProfesor(@ApiParam(value = "Id Competencia", required = true) @PathVariable Long id) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	Competencia Competencia = CompetenciaService.findById(id);
    	map.put("id", id);
    	map.put("nombre", Competencia.getNombre());
    	return map;
    }
    
	
}
