package pe.edu.upc.sysadap.spring.security.web.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Length.List;
import org.hibernate.validator.constraints.NotEmpty;

import pe.edu.upc.sysadap.spring.security.constraint.FieldMatch;

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class PasswordResetDto {

    @NotEmpty
    @List({
        @Length(min = 8, message = "The field must be at least 8 characters"),
        @Length(max = 15, message = "The field must be less than 15 characters")
    })
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
