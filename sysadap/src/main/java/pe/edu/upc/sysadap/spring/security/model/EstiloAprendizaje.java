package pe.edu.upc.sysadap.spring.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Classe que representa o usuario do sistema
 * 
 * @author hlandim
 *
 */
@Entity
public class EstiloAprendizaje implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToMany(mappedBy="recursos")
    private List<Clase> clases = new ArrayList<Clase>();
	
	@OneToMany(mappedBy="estiloAprendizaje")
    private List<RecursoDidactico> recursoDidacticos;
	
	private String nombre;
	
	private String peso;
	
	public EstiloAprendizaje() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public List<RecursoDidactico> getRecursoDidacticos() {
		return recursoDidacticos;
	}

	public void setRecursoDidacticos(List<RecursoDidactico> recursoDidacticos) {
		this.recursoDidacticos = recursoDidacticos;
	}
	
	
	
}
