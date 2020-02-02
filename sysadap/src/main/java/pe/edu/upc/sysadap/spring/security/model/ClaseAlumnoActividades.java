package pe.edu.upc.sysadap.spring.security.model;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.NumberFormat;

/**
 * Classe que representa o usuario do sistema
 * 
 * @author hlandim
 *
 */
@Entity
public class ClaseAlumnoActividades implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NumberFormat
	private Long id_clasealumno;
	
	@Column
	@NumberFormat
	private Long id_actividad;
	
	@Column
	@NumberFormat
	private Long id_recurso;
	
	@Column
	private Double valor;
	
	public ClaseAlumnoActividades() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_clasealumno() {
		return id_clasealumno;
	}

	public void setId_clasealumno(Long id_clasealumno) {
		this.id_clasealumno = id_clasealumno;
	}

	public Long getId_actividad() {
		return id_actividad;
	}

	public void setId_actividad(Long id_actividad) {
		this.id_actividad = id_actividad;
	}

	public Long getId_recurso() {
		return id_recurso;
	}

	public void setId_recurso(Long id_recurso) {
		this.id_recurso = id_recurso;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
    public static Comparator<ClaseAlumnoActividades> claseAlumnoActividadesComparator = new Comparator<ClaseAlumnoActividades>() {

	public int compare(ClaseAlumnoActividades s1, ClaseAlumnoActividades s2) {
		Long StudentName1 = s1.id_recurso;
		Long StudentName2 = s2.id_recurso;

	   //ascending order
	   return StudentName1.compareTo(StudentName2);
    }};
}
