package pe.edu.upc.sysadap.spring.security.web.dto;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ClaseDto {

    private Long id;
	
    @NotEmpty
    private String nombre;
    
    @Column
	@NotNull
	private Integer edad;
    
    private List<Long> idAlumnos;
    
    private String competencias;
    
    private Long idProfesor;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public List<Long> getIdAlumnos() {
		return idAlumnos;
	}

	public void setIdAlumnos(List<Long> idAlumnos) {
		this.idAlumnos = idAlumnos;
	}

	public String getCompetencias() {
		return competencias;
	}

	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}

	public Long getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}
	
	
}
