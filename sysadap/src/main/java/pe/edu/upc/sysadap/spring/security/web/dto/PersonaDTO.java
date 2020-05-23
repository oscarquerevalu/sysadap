package pe.edu.upc.sysadap.spring.security.web.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import pe.edu.upc.sysadap.spring.security.model.Persona.Role;

/**
 * Classe que representa o usuario do sistema
 * 
 * @author hlandim
 *
 */
public class PersonaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@Size(max = 50)
	private String username;

	@NotEmpty
	private String name;

	@NotEmpty
	private String password;
	
	private String confirmPassword;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthdate;
	
	@NotEmpty
	@Email
	private String email;
	
	@Email
	private String confirmEmail;
	
	@Column
	@Digits(integer=9,fraction=0,message = "Debe ingresar un Número Celular Válido")
	private String telefono;
	
	@Column
	@Digits(integer=8,fraction=0)
	@Length(min=8, max=8,message = "Cantidad de dígitos no válida")
	private String documento;
	
	private String direccion;
	
	@AssertTrue(message = "Tiene que aceptar los términos y condiciones")
    private Boolean terms;

	@NotNull(message = "Seleccione un rol")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public PersonaDTO() {
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public PersonaDTO(Long id, String username, String name, String password, LocalDate birthdate, String email,
			String telefono, String documento, String direccion, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.birthdate = birthdate;
		this.email = email;
		this.telefono = telefono;
		this.documento = documento;
		this.direccion = direccion;
		this.role = role;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public Boolean getTerms() {
		return terms;
	}

	public void setTerms(Boolean terms) {
		this.terms = terms;
	}
	
}
