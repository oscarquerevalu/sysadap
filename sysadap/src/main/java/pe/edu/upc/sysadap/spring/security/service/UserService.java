package pe.edu.upc.sysadap.spring.security.service;

import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Persona findByEmail(String email);

    Persona save(Persona registration);

    void updatePassword(String password, Long userId);
}
