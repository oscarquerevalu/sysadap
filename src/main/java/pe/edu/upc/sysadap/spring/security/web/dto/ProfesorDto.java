package pe.edu.upc.sysadap.spring.security.web.dto;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ProfesorDto {

    private Long id;
	
    @NotEmpty
    private String name;
    
    @Column
	@NotEmpty
	@Digits(integer=9,fraction=0,message = "Debe ingresar un Número Celular Válido")
	private String telefono;
	
	@Column
	@NotEmpty
	@Digits(integer=8,fraction=0)
	@Length(min=8, max=8,message = "Cantidad de dígitos no válida")
	private String documento;
	
	@Column
	private String direccion;
	
	@Transient
	private boolean edit = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
