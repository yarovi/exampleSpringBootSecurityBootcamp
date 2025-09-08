package org.yasmani.io.examplesecuritysprinbbootv1.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.yasmani.io.examplesecuritysprinbbootv1.entity.UserEntity;
import org.yasmani.io.examplesecuritysprinbbootv1.repository.UserRepository;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public DataInitializer(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        if (repo.findByUsername("ana").isEmpty()) {
            repo.save(new UserEntity("ana", encoder.encode("123"), Arrays.asList("ADMIN"), true));
        }
        if (repo.findByUsername("user").isEmpty()) {
            repo.save(new UserEntity("user", encoder.encode("password"), Arrays.asList("USER"), true));
        }
        if (repo.findByUsername("pepe").isEmpty()) {
            repo.save(new UserEntity("pepe", encoder.encode("123"), Arrays.asList("SELLER"), true));
        }
    }
}
