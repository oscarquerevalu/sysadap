package pe.edu.upc.sysadap.spring.security.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import pe.edu.upc.sysadap.spring.security.model.Alumno;
import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;
import pe.edu.upc.sysadap.spring.security.model.ClaseAlumnoActividades;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.service.AlumnoService;
import pe.edu.upc.sysadap.spring.security.service.ClaseAlumnoActividadesService;
import pe.edu.upc.sysadap.spring.security.service.ClaseAlumnoService;
import pe.edu.upc.sysadap.spring.security.service.PersonaService;

@RestController
@RequestMapping("/alumno")
@Api(value="Controlador para Alumnos", description="Operaciones de carga de datos")
@CrossOrigin(origins = "*")
public class AlumnoController {
	
	Logger logger = LoggerFactory.getLogger(AlumnoController.class);

    @Autowired
    private AlumnoService alumnoService;
    
    @Autowired
    private ClaseAlumnoService claseAlumnoService;
    
    @Autowired
    private ClaseAlumnoActividadesService claseAlumnoActividadesService;
    
    @Autowired
    private PersonaService personaService;

    @GetMapping(value="/listAlumnos", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ver lista los alumnos ")
    public List<Alumno> listaClase(Model model) {
    	
    	List<Alumno> listaAlumnos = new ArrayList<>();
    	logger.debug("message {}","listAlumnos");
    	try {
    		listaAlumnos = alumnoService.findByAll();
    		for (Alumno alumno : listaAlumnos) {
    			alumno.setPersonas(null);
    			alumno.setClase(null);
    			alumno.setApoderado(null);
    			alumno.setPersona(personaService.findByIdAlumno(alumno.getId()));
    			alumno.setPersonas(null);
    			alumno.getPersona().setAlumno(null);
    			if(alumno.getPersona()!=null) {
    				alumno.getPersona().setAlumno(null);
    				alumno.getPersona().setProfesor(null);
    			}
			} 
    		listaAlumnos = listaAlumnos.stream().filter(profesor -> profesor.getPersona()!=null).collect(Collectors.toList());
		} catch (Exception e) {
			
			logger.error("AlumnoController listaClase", e);
		}
    	
        return listaAlumnos;
    }
    
    
    @GetMapping(value = "/listalumnos/{clase}/{fecha}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ver los alumnos por fecha de clase")
    public List<Alumno> listalumnosxFecna( @ApiParam(value = "Fecha de clase", required = true) @PathVariable String fecha,
    									   @ApiParam(value = "Id Clase", required = true) @PathVariable Long clase) {

    	List<Alumno> listaAlumnos = new ArrayList<>();
    	List<ClaseAlumno> listaClaseAlumno = new ArrayList<>();
    	
    	try {
    		listaAlumnos = alumnoService.findByIdClase(clase);
    		for (Alumno alumno : listaAlumnos) {
    			alumno.setPersonas(null);
    			alumno.setClase(null);
    			alumno.setApoderado(null);
    			alumno.setPersona(personaService.findByIdAlumno(alumno.getId()));
    			alumno.getPersona().setAlumno(null);
    			logger.debug("fecha: {}",fecha);
    			
    			//Buscar si se encuentra calificado
    			listaClaseAlumno = new ArrayList<>();
    			listaClaseAlumno = claseAlumnoService.findByFechaIdAlumno(fecha,alumno.getId(),clase);
    			if(listaClaseAlumno!=null && listaClaseAlumno.size()>0)
    				alumno.setCalificado("SI");
    			else
    				alumno.setCalificado("NO");
			} 
		} catch (Exception e) {
			
		}
		return listaAlumnos;
    }
    
    @GetMapping(value = "/getAlumno/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtener Alumno")
    public Map<String, Object> getProfesor(@ApiParam(value = "Id persona", required = true) @PathVariable Long id) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	Persona persona = personaService.findById(id);
    	map.put("id", id);
    	map.put("nombre", persona.getName());
    	map.put("telefono", persona.getTelefono());
    	map.put("documento", persona.getDocumento());
    	map.put("direccion", persona.getDireccion());
    	
    	if(persona.getAlumno()!=null && persona.getAlumno().getApoderado()!=null) {
    		map.put("codApoderado", persona.getAlumno().getApoderado().getId());
        	map.put("nombreApoderado", persona.getAlumno().getApoderado().getPersonas().get(0).getName());
        	map.put("documentoApoderado", persona.getAlumno().getApoderado().getPersonas().get(0).getDocumento());
    	}
    	
    	if(persona.getAlumno()!=null && persona.getAlumno().getClase()!=null) {
    		map.put("idClase", persona.getAlumno().getClase().getId());
    	}
    	
    	return map;
    }
    
    @GetMapping(value = "/getApoderado/{documento}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtener Apoderado")
    public Map<String, Object> getApoderado(@ApiParam(value = "Documento apoderado", required = true) @PathVariable String documento) {
    	Map<String, Object> map = new HashMap<>();
    	List<Persona> personas = personaService.findByIdApoderado(documento);
    	
    	if(personas.isEmpty()) {
    		map.put("error", true);
    		return map;
    	}
    	
    	for (Persona apoderado : personas) {
    		map.put("id", apoderado.getApoderado().getId());
        	map.put("nombre", apoderado.getName());
        	map.put("documento", apoderado.getDocumento());
		}
    	return map;
    }
    
    @GetMapping(value = "/getalumno/{fecha}/{clase}/{alumno}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ver los alumnos por fecha de clase")
    public Map<String, Object> alumnoxFecna( @ApiParam(value = "Fecha de clase", required = true) @PathVariable String fecha,
    										 @ApiParam(value = "Codigo de clase", required = true) @PathVariable Long clase,
    										 @ApiParam(value = "Codigo de alumno", required = true) @PathVariable Long alumno) {

    	List<ClaseAlumno> listaClaseAlumno = new ArrayList<>();
    	List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<>();
    	Map<String, Object> map = new HashMap<>();
    	
    	try {
    		
    		listaClaseAlumno = new ArrayList<>();
			listaClaseAlumno = claseAlumnoService.findByFechaIdAlumno(fecha,alumno,clase);
			
			Double rec1 = new Double("0");
			Double rec2 = new Double("0");
			Double rec3 = new Double("0");
			Double rec4 = new Double("0");
			Double rec5 = new Double("0");
			Double rec6 = new Double("0");
			Double rec7 = new Double("0");
			Double rec8 = new Double("0");
			Double rec9 = new Double("0");
			Double rec10 = new Double("0");
			Double rec11 = new Double("0");
			Double rec12 = new Double("0");
			
			for (ClaseAlumno claseAlumno : listaClaseAlumno) {
				lstClaseAlumnoActividades = claseAlumnoActividadesService.findByIdClasealumno(claseAlumno.getId());
				
				for (ClaseAlumnoActividades claseAlumnoActividades : lstClaseAlumnoActividades) {
					map.put("competencia", claseAlumnoActividades.getId_actividad());
					if(rec1.equals(new Double("0")) && (new Long(1)).equals(claseAlumnoActividades.getId_recurso())) {
						rec1 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(rec2.equals(new Double("0")) && (new Long(2)).equals(claseAlumnoActividades.getId_recurso())) {
						rec2 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(rec3.equals(new Double("0")) && (new Long(3)).equals(claseAlumnoActividades.getId_recurso())) {
						rec3 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(rec4.equals(new Double("0")) && (new Long(4)).equals(claseAlumnoActividades.getId_recurso())) {
						rec4 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(rec5.equals(new Double("0")) && (new Long(5)).equals(claseAlumnoActividades.getId_recurso())) {
						rec5 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(rec6.equals(new Double("0")) && (new Long(6)).equals(claseAlumnoActividades.getId_recurso())) {
						rec6 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(rec7.equals(new Double("0"))  && (new Long(7)).equals(claseAlumnoActividades.getId_recurso())) {
						rec7 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(rec8.equals(new Double("0")) && (new Long(8)).equals(claseAlumnoActividades.getId_recurso())) {
						rec8 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(rec9.equals(new Double("0")) && (new Long(9)).equals(claseAlumnoActividades.getId_recurso())) {
						rec9 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(rec10.equals(new Double("0")) && (new Long(10)).equals(claseAlumnoActividades.getId_recurso())) {
						rec10 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(rec11.equals(new Double("0")) && (new Long(11)).equals(claseAlumnoActividades.getId_recurso())) {
						rec11 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(rec12.equals(new Double("0")) && (new Long(12)).equals(claseAlumnoActividades.getId_recurso())) {
						rec12 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}
				}
			}
			
			map.put("val1", rec1);
			map.put("val2", rec2);
			map.put("val3", rec3);
			map.put("val4", rec4);
			map.put("val5", rec5);
			map.put("val6", rec6);
			map.put("val7", rec7);
			map.put("val8", rec8);
			map.put("val9", rec9);
			map.put("val10", rec10);
			map.put("val11", rec11);
			map.put("val12", rec12);
			
		} catch (Exception e) {
			
		}
		return map;
    }
    
    @GetMapping(value = "/getDataClaseAlumnos", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Datos de Clase de alumnos")
    public List<Map<String, Object>> getClaseAlumno() {

    	List<ClaseAlumno> listaClaseAlumno = new ArrayList<ClaseAlumno>();
    	List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<Map<String, Object>> lstDataClase = new ArrayList<>();
    	
    	try {
    		
    		listaClaseAlumno = new ArrayList<ClaseAlumno>();
			listaClaseAlumno = claseAlumnoService.findByAll();
			
			Double recCA1 = new Double("0");
			Double recCA2 = new Double("0");
			Double recCA3 = new Double("0");
			Double recCA4 = new Double("0");
			Double recCA5 = new Double("0");
			Double recCA6 = new Double("0");
			Double recCA7 = new Double("0");
			Double recCA8 = new Double("0");
			Double recCA9 = new Double("0");
			Double recCA10 = new Double("0");
			Double recCA11 = new Double("0");
			Double recCA12 = new Double("0");
			Long estilo = new Long(0);
			
			
			for (ClaseAlumno claseAlumno : listaClaseAlumno) {
				map = new HashMap<String, Object>();
				lstClaseAlumnoActividades = claseAlumnoActividadesService.findByIdClasealumno(claseAlumno.getId());
				estilo = claseAlumno.getId_estilo() !=null?claseAlumno.getId_estilo():new Long(0);
				for (ClaseAlumnoActividades claseAlumnoActividades : lstClaseAlumnoActividades) {
					if(recCA1.equals(new Double("0")) && (new Long(1)).equals(claseAlumnoActividades.getId_recurso())) {
						recCA1 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(recCA2.equals(new Double("0")) && (new Long(2)).equals(claseAlumnoActividades.getId_recurso())) {
						recCA2 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(recCA3.equals(new Double("0")) && (new Long(3)).equals(claseAlumnoActividades.getId_recurso())) {
						recCA3 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(recCA4.equals(new Double("0")) && (new Long(4)).equals(claseAlumnoActividades.getId_recurso())) {
						recCA4 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(recCA5.equals(new Double("0")) && (new Long(5)).equals(claseAlumnoActividades.getId_recurso())) {
						recCA5 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(recCA6.equals(new Double("0")) && (new Long(6)).equals(claseAlumnoActividades.getId_recurso())) {
						recCA6 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(recCA7.equals(new Double("0"))  && (new Long(7)).equals(claseAlumnoActividades.getId_recurso())) {
						recCA7 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(recCA8.equals(new Double("0")) && (new Long(8)).equals(claseAlumnoActividades.getId_recurso())) {
						recCA8 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(recCA9.equals(new Double("0")) && (new Long(9)).equals(claseAlumnoActividades.getId_recurso())) {
						recCA9 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(recCA10.equals(new Double("0")) && (new Long(10)).equals(claseAlumnoActividades.getId_recurso())) {
						recCA10 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(recCA11.equals(new Double("0")) && (new Long(11)).equals(claseAlumnoActividades.getId_recurso())) {
						recCA11 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}else if(recCA12.equals(new Double("0")) && (new Long(12)).equals(claseAlumnoActividades.getId_recurso())) {
						recCA12 = claseAlumnoActividades.getValor() != null && !claseAlumnoActividades.getValor().equals(new Double("0"))? claseAlumnoActividades.getValor():new Double("0");
					}
				}
				
				map.put("recurso1", recCA1);
				map.put("recurso2", recCA2);
				map.put("recurso3", recCA3);
				map.put("recurso4", recCA4);
				map.put("recurso5", recCA5);
				map.put("recurso6", recCA6);
				map.put("recurso7", recCA7);
				map.put("recurso8", recCA8);
				map.put("recurso9", recCA9);
				map.put("recurso10", recCA10);
				map.put("recurso11", recCA11);
				map.put("recurso12", recCA12);
				map.put("estilo", estilo);
				lstDataClase.add(map);
				
				recCA1 = new Double("0");
				recCA2 = new Double("0");
				recCA3 = new Double("0");
				recCA4 = new Double("0");
				recCA5 = new Double("0");
				recCA6 = new Double("0");
				recCA7 = new Double("0");
				recCA8 = new Double("0");
				recCA9 = new Double("0");
				recCA10 = new Double("0");
				recCA11 = new Double("0");
				recCA12 = new Double("0");
				estilo = new Long(0);
			}
			
			
			
		} catch (Exception e) {
			
		}
		return lstDataClase;
    }
    
    @ApiOperation(value = "Graba datos de clase")
    @PostMapping(value="/grabarClase", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String create(@RequestBody Map<String, String> body){
    	
    	ClaseAlumno claseAlumno = new ClaseAlumno();
    	claseAlumno.setFecha(body.get("fecha"));
    	claseAlumno.setId_alumno(new Long(body.get("idAlumno")));
    	claseAlumno.setId_clase(new Long(body.get("idClase")));
    	ClaseAlumno claseAlumnoResp= claseAlumnoService.guardar(claseAlumno);
    	
    	String[] recursos = body.get("recursos").split(",");
    	String[] valores = body.get("valores").split(",");
    	
    	for (int i = 0; i < recursos.length; i++) {
    		String rec = recursos[i];
    		ClaseAlumnoActividades claseAlumnoActividades = new ClaseAlumnoActividades();
    		claseAlumnoActividades.setId_actividad(new Long(body.get("actividad")));
    		claseAlumnoActividades.setId_clasealumno(claseAlumnoResp.getId());
    		claseAlumnoActividades.setId_recurso(new Long(rec));
    		claseAlumnoActividades.setValor((new Double(valores[i]))/100);
    		claseAlumnoActividadesService.guardarActividad(claseAlumnoActividades);
		}
    	return claseAlumnoResp.getId().toString();
    }
    
    @ApiOperation(value = "Grabar Estilo de aprendizaje")
    @PostMapping(value="/grabarClaseEstilo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String createEstilo(@RequestBody Map<String, String> body){
    	
    	String[] recursos = body.get("recursos").split(",");
    	String[] valores = body.get("valores").split(",");
    	
    	for (int j = 0; j < 20; j++) {
    		ClaseAlumno claseAlumno = new ClaseAlumno();
        	claseAlumno.setFecha("01019999");
        	claseAlumno.setId_alumno(new Long("-1"));
        	claseAlumno.setId_clase(new Long("-1"));
        	claseAlumno.setId_estilo(new Long(body.get("estilo")));
    		ClaseAlumno claseAlumnoResp= claseAlumnoService.guardar(claseAlumno);
    		for (int i = 0; i < recursos.length; i++) {
        		String rec = recursos[i];
        		ClaseAlumnoActividades claseAlumnoActividades = new ClaseAlumnoActividades();
        		claseAlumnoActividades.setId_clasealumno(claseAlumnoResp.getId());
        		claseAlumnoActividades.setId_recurso(new Long(rec));
        		claseAlumnoActividades.setValor((new Double(valores[i]))/100);
        		claseAlumnoActividadesService.guardarActividad(claseAlumnoActividades);
    		}
		}
    	
    	return "{\"success\":1}";
    }
    
    @GetMapping(value="/promRecursos", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ver promedio de recursos ")
    public List<Map<String, Object>>  promRecursos(Model model) {
    	
    	List<Map<String, Object>> listReturn = new ArrayList<>(); 
    	Map<String, Object> promedio = new HashMap<>();
    	List<ClaseAlumnoActividades> claseAlumnoActividades = new ArrayList<>();
    	try {
    		claseAlumnoActividades = claseAlumnoActividadesService.findAll();
    		Collections.sort(claseAlumnoActividades, ClaseAlumnoActividades.claseAlumnoActividadesComparator);
    		
    		Long id_recurso = 0L;
    		Double sum = new Double(0);
    		int cant = 0;
    		boolean reinicio = false;
    		for (ClaseAlumnoActividades actividad : claseAlumnoActividades) {
    			
    			if(id_recurso.equals(0L)) {
    				id_recurso = actividad.getId_recurso();
    			}
    			
    			if(id_recurso.compareTo(actividad.getId_recurso()) == 0) {
    				cant++;
    				sum += actividad.getValor()!=null?actividad.getValor():new Double(0);
    				reinicio = true;
    			}else {
    				Double prom = !sum.equals(new Double(0)) && cant != 0? sum/cant:0;
    				promedio = new HashMap<String, Object>();
    				promedio.put("index", id_recurso);
    				promedio.put("value", prom);
    				listReturn.add(promedio);
    				id_recurso = actividad.getId_recurso();
    				cant=1;
    				reinicio = false;
    				sum = actividad.getValor()!=null?actividad.getValor():new Double(0);
    			}
			} 
    		
    		if(reinicio || cant==1) {
    			Double prom = !sum.equals(new Double(0)) && cant != 0? sum/cant:0;
    			promedio = new HashMap<>();
				promedio.put("index", id_recurso);
				promedio.put("value", prom);
				listReturn.add(promedio);
    		}
		} catch (Exception e) {
			logger.error("Error",e);
		}
    	
        return listReturn;
    }
    
}
