package pe.edu.upc.sysadap.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
//public class Run {
public class Run extends SpringBootServletInitializer {

//    public static void main(String[] args) {
//        SpringApplication.run(Run.class, args);
//    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Run.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Run.class, args);
    }

}
