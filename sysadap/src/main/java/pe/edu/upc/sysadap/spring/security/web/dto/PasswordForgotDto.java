package pe.edu.upc.sysadap.spring.security.web.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class PasswordForgotDto {

    @Email(message = "Debe ingresar un Email válido")
    @NotEmpty(message = "Debe ingresar un Email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
