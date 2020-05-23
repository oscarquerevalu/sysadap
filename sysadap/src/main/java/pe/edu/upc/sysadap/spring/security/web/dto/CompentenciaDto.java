package pe.edu.upc.sysadap.spring.security.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class CompentenciaDto {

    private Long id;
	
    @NotEmpty
    private String nombre;

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
}
