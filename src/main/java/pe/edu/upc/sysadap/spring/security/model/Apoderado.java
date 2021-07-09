package pe.edu.upc.sysadap.spring.security.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Classe que representa o usuario do sistema
 * 
 * @author hlandim
 *
 */
@Entity
public class Apoderado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy="apoderado")
    private List<Persona> personas;
	
	
//	@ManyToMany(cascade = {
//	        CascadeType.PERSIST,
//	        CascadeType.MERGE
//	    })
//	@JoinTable(name = "alumno_apoderado",
//	    joinColumns = @JoinColumn(name = "id_apoderado"),
//	    inverseJoinColumns = @JoinColumn(name = "id_alumno")
//	)
//	private List<Alumno> alumnos = new ArrayList<Alumno>();
	@OneToMany(mappedBy="apoderado")
	private Set<Alumno> alumnos = new HashSet<>();

	public Apoderado() {
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

	public Set<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	
	
}
