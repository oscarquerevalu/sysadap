package pe.edu.upc.sysadap.spring.security.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 * Classe que representa o usuario do sistema
 * 
 * @author hlandim
 *
 */
@Entity
public class Alumno implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="alumno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Persona> personas;
	
//	@ManyToMany(mappedBy="alumnos", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//	@ManyToOne
//	@JoinColumn(name="id", nullable=false)
	@ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="id_apoderado", referencedColumnName="id")
	private Apoderado apoderado;
//    private List<Apoderado> apoderados = new ArrayList<Apoderado>();
	
//	@ManyToMany(mappedBy="alumnos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Clase> clases = new ArrayList<Clase>();
	
	@ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="id_clase", referencedColumnName="id")
	private Clase clase;
	
	@Transient
	private Persona persona;
	
	@Transient
	private String calificado;
	
	public Alumno() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	@Transient
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Transient
	public String getCalificado() {
		return calificado;
	}

	public void setCalificado(String calificado) {
		this.calificado = calificado;
	}

	public Apoderado getApoderado() {
		return apoderado;
	}

	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
	}
	
}
