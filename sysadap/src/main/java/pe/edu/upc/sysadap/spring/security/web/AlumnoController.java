package pe.edu.upc.sysadap.spring.security.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.sysadap.spring.security.model.Alumno;
import pe.edu.upc.sysadap.spring.security.model.Clase;
import pe.edu.upc.sysadap.spring.security.model.ClaseAlumno;
import pe.edu.upc.sysadap.spring.security.model.ClaseAlumnoActividades;
import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.service.AlumnoService;
import pe.edu.upc.sysadap.spring.security.service.ClaseAlumnoActividadesService;
import pe.edu.upc.sysadap.spring.security.service.ClaseAlumnoService;
import pe.edu.upc.sysadap.spring.security.service.PersonaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/alumno")
@Api(value="Controlador para Alumnos", description="Operaciones de carga de datos")
public class AlumnoController {

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
    	
    	List<Clase> lista = new ArrayList<Clase>();
    	List<Alumno> listaAlumnos = new ArrayList<Alumno>();
    	System.out.println("listAlumnos");
    	try {
    		listaAlumnos = alumnoService.findByAll();
    		for (Alumno alumno : listaAlumnos) {
    			alumno.setPersonas(null);
    			alumno.setClases(null);
    			alumno.setApoderados(null);
    			alumno.setPersona(personaService.findByIdAlumno(alumno.getId()));
    			
//    			alumno.getPersona().setName("ABC");
//    			personaService.guardarPersona(alumno.getPersona());
			} 
//    		listaAlumnos = lista.get(0).getAlumnos();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
        return listaAlumnos;
    }
    
    
    @GetMapping(value = "/listalumnos/{fecha}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ver los alumnos por fecha de clase")
    public List<Alumno> listalumnosxFecna( @ApiParam(value = "Fecha de clase", required = true) @PathVariable String fecha) {

    	List<Alumno> listaAlumnos = new ArrayList<Alumno>();
    	List<ClaseAlumno> listaClaseAlumno = new ArrayList<ClaseAlumno>();
    	
    	try {
    		listaAlumnos = alumnoService.findByAll();
    		for (Alumno alumno : listaAlumnos) {
    			alumno.setPersonas(null);
    			alumno.setClases(null);
    			alumno.setApoderados(null);
    			alumno.setPersona(personaService.findByIdAlumno(alumno.getId()));
    			alumno.getPersona().setAlumno(null);
    			System.out.println("fecha: "+fecha);
    			
    			//Buscar si se encuentra calificado
    			listaClaseAlumno = new ArrayList<ClaseAlumno>();
    			listaClaseAlumno = claseAlumnoService.findByFechaIdAlumno(fecha,alumno.getId());
    			if(listaClaseAlumno!=null && listaClaseAlumno.size()>0)
    				alumno.setCalificado("SI");
    			else
    				alumno.setCalificado("NO");
			} 
//    		listaAlumnos = lista.get(0).getAlumnos();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaAlumnos;
    }
    
    @GetMapping(value = "/getalumno/{fecha}/{clase}/{alumno}", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ver los alumnos por fecha de clase")
    public Map<String, Object> alumnoxFecna( @ApiParam(value = "Fecha de clase", required = true) @PathVariable String fecha,
    										 @ApiParam(value = "Codigo de clase", required = true) @PathVariable Long clase,
    										 @ApiParam(value = "Codigo de alumno", required = true) @PathVariable Long alumno) {

    	List<Alumno> listaAlumnos = new ArrayList<Alumno>();
    	List<ClaseAlumno> listaClaseAlumno = new ArrayList<ClaseAlumno>();
    	List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	try {
    		
    		listaClaseAlumno = new ArrayList<ClaseAlumno>();
			listaClaseAlumno = claseAlumnoService.findByFechaIdAlumno(fecha,alumno);
			
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
			// TODO: handle exception
		}
		return map;
    }
    
    @GetMapping(value = "/getDataClaseAlumnos", produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Datos de Clase de alumnos")
    public List<Map<String, Object>> getClaseAlumno() {

    	List<ClaseAlumno> listaClaseAlumno = new ArrayList<ClaseAlumno>();
    	List<ClaseAlumnoActividades> lstClaseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	List<Map<String, Object>> lstDataClase = new ArrayList<Map<String,Object>>();
    	
    	try {
    		
    		listaClaseAlumno = new ArrayList<ClaseAlumno>();
			listaClaseAlumno = claseAlumnoService.findByAll();
			
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
			Long estilo = new Long(0);
			
			
			for (ClaseAlumno claseAlumno : listaClaseAlumno) {
				map = new HashMap<String, Object>();
				lstClaseAlumnoActividades = claseAlumnoActividadesService.findByIdClasealumno(claseAlumno.getId());
				estilo = claseAlumno.getId_estilo() !=null?claseAlumno.getId_estilo():new Long(0);
				for (ClaseAlumnoActividades claseAlumnoActividades : lstClaseAlumnoActividades) {
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
				
				map.put("recurso1", rec1);
				map.put("recurso2", rec2);
				map.put("recurso3", rec3);
				map.put("recurso4", rec4);
				map.put("recurso5", rec5);
				map.put("recurso6", rec6);
				map.put("recurso7", rec7);
				map.put("recurso8", rec8);
				map.put("recurso9", rec9);
				map.put("recurso10", rec10);
				map.put("recurso11", rec11);
				map.put("recurso12", rec12);
				map.put("estilo", estilo);
				lstDataClase.add(map);
				
				rec1 = new Double("0");
				rec2 = new Double("0");
				rec3 = new Double("0");
				rec4 = new Double("0");
				rec5 = new Double("0");
				rec6 = new Double("0");
				rec7 = new Double("0");
				rec8 = new Double("0");
				rec9 = new Double("0");
				rec10 = new Double("0");
				rec11 = new Double("0");
				rec12 = new Double("0");
				estilo = new Long(0);
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lstDataClase;
    }
    
    @ApiOperation(value = "Graba datos de clase")
    @PostMapping(value="/grabarClase", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String create(@RequestBody Map<String, String> body){
    	
    	ClaseAlumno claseAlumno = new ClaseAlumno();
    	claseAlumno.setFecha(body.get("fecha"));
    	claseAlumno.setId_alumno(new Long(body.get("idAlumno")));
    	claseAlumno.setId_clase(new Long("1"));
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
        	claseAlumno.setId_clase(new Long("1"));
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
    	
    	List<Map<String, Object>> listReturn = new ArrayList<Map<String,Object>>(); 
    	Map<String, Object> promedio = new HashMap<String, Object>();
    	List<ClaseAlumnoActividades> claseAlumnoActividades = new ArrayList<ClaseAlumnoActividades>();
    	try {
    		claseAlumnoActividades = claseAlumnoActividadesService.findAll();
    		Collections.sort(claseAlumnoActividades, ClaseAlumnoActividades.claseAlumnoActividadesComparator);
    		
    		Long id_recurso = 0L;
    		Double sum = new Double(0);
    		int cant = 0;
    		for (ClaseAlumnoActividades actividad : claseAlumnoActividades) {
    			
    			if(id_recurso.equals(0L)) {
    				id_recurso = actividad.getId_recurso();
    			}
    			
    			if(id_recurso.compareTo(actividad.getId_recurso()) == 0) {
    				cant++;
    				sum += actividad.getValor()!=null?actividad.getValor():new Double(0);
    			}else {
    				Double prom = !sum.equals(new Double(0)) && cant != 0? sum/cant:0;
    				promedio = new HashMap<String, Object>();
    				promedio.put("index", id_recurso);
    				promedio.put("value", prom);
    				listReturn.add(promedio);
    				id_recurso = actividad.getId_recurso();
    				cant=1;
    				sum = actividad.getValor()!=null?actividad.getValor():new Double(0);
    			}
			} 
    		
    		if(cant==1) {
    			Double prom = !sum.equals(new Double(0)) && cant != 0? sum/cant:0;
				promedio.put("index", id_recurso);
				promedio.put("value", prom);
				listReturn.add(promedio);
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
        return listReturn;
    }
    
	
}
