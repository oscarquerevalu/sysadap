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
public class EstiloAlumno implements Serializable {

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
	
	@Column
	private Double valor1;
	
	@Column
	private Double valor2;
	
	@Column
	private Double valor3;
	
	@Column
	private Double valor4;
	
	@Column
	private Double valor5;
	
	@Column
	private Double valor6;
	
	@Column
	private Double valor7;
	
	@Column
	private Double valor8;
	
	public EstiloAlumno() {
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

	public Double getValor1() {
		return valor1;
	}

	public void setValor1(Double valor1) {
		this.valor1 = valor1;
	}

	public Double getValor2() {
		return valor2;
	}

	public void setValor2(Double valor2) {
		this.valor2 = valor2;
	}

	public Double getValor3() {
		return valor3;
	}

	public void setValor3(Double valor3) {
		this.valor3 = valor3;
	}

	public Double getValor4() {
		return valor4;
	}

	public void setValor4(Double valor4) {
		this.valor4 = valor4;
	}

	public Double getValor5() {
		return valor5;
	}

	public void setValor5(Double valor5) {
		this.valor5 = valor5;
	}

	public Double getValor6() {
		return valor6;
	}

	public void setValor6(Double valor6) {
		this.valor6 = valor6;
	}

	public Double getValor7() {
		return valor7;
	}

	public void setValor7(Double valor7) {
		this.valor7 = valor7;
	}

	public Double getValor8() {
		return valor8;
	}

	public void setValor8(Double valor8) {
		this.valor8 = valor8;
	}
}
