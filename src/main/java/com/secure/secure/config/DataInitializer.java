package com.secure.secure.config;

import com.secure.secure.enums.AppRole;
import com.secure.secure.model.Role;
import com.secure.secure.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        if (roleRepository.findByRoleName(AppRole.ROLE_USER).isEmpty()) {
            roleRepository.save(new Role(null, AppRole.ROLE_USER));
        }

        if (roleRepository.findByRoleName(AppRole.ROLE_ADMIN).isEmpty()) {
            roleRepository.save(new Role(null, AppRole.ROLE_ADMIN));
        }
    }
}
