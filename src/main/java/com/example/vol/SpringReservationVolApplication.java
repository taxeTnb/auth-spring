package com.example.vol;

import com.example.vol.models.Utilisateur;
import com.example.vol.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class SpringReservationVolApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringReservationVolApplication.class, args);
    }

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //private RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
        Utilisateur user = appUserRepository.findByUsername("admin").orElse(null);
        if (user == null){
            appUserRepository.save(
                    new Utilisateur("admin", passwordEncoder.encode("admin"),"adm")
            );
        }
    }
    
}
