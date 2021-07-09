package pe.edu.upc.sysadap.spring.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import pe.edu.upc.sysadap.spring.security.model.Persona;

public interface UserService extends UserDetailsService {

    Persona findByEmail(String email);

    Persona save(Persona registration);

    void updatePassword(String password, Long userId);
}
