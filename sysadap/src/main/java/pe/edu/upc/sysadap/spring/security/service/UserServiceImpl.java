package pe.edu.upc.sysadap.spring.security.service;

import pe.edu.upc.sysadap.spring.security.model.Persona;
import pe.edu.upc.sysadap.spring.security.repository.UserRepository;
import pe.edu.upc.sysadap.spring.security.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.management.relation.Role;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Persona findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Persona save(Persona registration){
        Persona persona = new Persona();
        persona.setEmail(registration.getEmail());
        persona.setUsername(registration.getEmail());
        persona.setRole(Persona.Role.ROLE_USER);
        persona.setName(registration.getName());
        persona.setTelefono(registration.getTelefono());
        persona.setDocumento(registration.getDocumento());
        persona.setPassword(passwordEncoder.encode(registration.getPassword()));
        return userRepository.save(persona);
    }

    @Override
    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Persona persona = userRepository.findByEmail(email);
        if (persona == null){
            throw new UsernameNotFoundException("Usuario o contraseña invalido.");
        }
        return new org.springframework.security.core.userdetails.User(persona.getEmail(),
        		persona.getPassword(),
                mapRolesToAuthorities(Arrays.asList(persona.getRole())));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<pe.edu.upc.sysadap.spring.security.model.Persona.Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
    }
}
