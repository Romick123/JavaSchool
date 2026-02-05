package com.example.cursovik;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordGenerator {

    @Bean
    CommandLineRunner generatePassword() {
        return args -> {
            PasswordEncoder encoder = new BCryptPasswordEncoder();

            String rawPassword = "1234";   // любой пароль
            String encoded = encoder.encode(rawPassword);

            System.out.println("RAW: " + rawPassword);
            System.out.println("ENCODED: " + encoded);
        };
    }
}
