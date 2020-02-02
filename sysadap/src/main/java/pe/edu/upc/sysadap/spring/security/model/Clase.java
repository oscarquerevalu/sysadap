package pe.edu.upc.sysadap.spring.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe que representa o usuario do sistema
 * 
 * @author hlandim
 *
 */
@Entity
public class Clase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "alumno_clase",
	    joinColumns = @JoinColumn(name = "id_clase"),
	    inverseJoinColumns = @JoinColumn(name = "id_alumno")
	)
	private List<Alumno> alumnos = new ArrayList<Alumno>();
	
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	@JoinTable(name = "recurso_clase",
	    joinColumns = @JoinColumn(name = "id_clase"),
	    inverseJoinColumns = @JoinColumn(name = "id_recurso")
	)
	private List<RecursoDidactico> recursos = new ArrayList<RecursoDidactico>();
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column
	private Date fechaClase;
	
	@ManyToOne(optional=true)
    @JoinColumn(name="id_profesor", referencedColumnName="id")
	private Profesor profesor;

	public Clase() {
	}

	public Clase(Long id) {
		super();
		this.id = id;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public List<RecursoDidactico> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<RecursoDidactico> recursos) {
		this.recursos = recursos;
	}

	public Date getFechaClase() {
		return fechaClase;
	}

	public void setFechaClase(Date fechaClase) {
		this.fechaClase = fechaClase;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
}
