package pe.edu.upc.sysadap.spring.security.model;

import java.io.Serializable;

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
public class ClaseAlumno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NumberFormat
	private Long id_alumno;
	
	@Column
	@NumberFormat
	private Long id_clase;
	
	@Column
	private String fecha;
	
	@Column
	private Long id_estilo;
	
	public ClaseAlumno() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(Long id_alumno) {
		this.id_alumno = id_alumno;
	}

	public Long getId_clase() {
		return id_clase;
	}

	public void setId_clase(Long id_clase) {
		this.id_clase = id_clase;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Long getId_estilo() {
		return id_estilo;
	}

	public void setId_estilo(Long id_estilo) {
		this.id_estilo = id_estilo;
	}
	
}
