package com.app.pw.prueba.service.Impl;

import com.app.pw.prueba.model.RoleEntity;
import com.app.pw.prueba.model.UserEntity;
import com.app.pw.prueba.repository.RoleRepository;
import com.app.pw.prueba.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserSetupService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        createRoles();
        createUser();
    }

    private void createRoles() {
        createRoleIfNotExists("ADMIN");
        createRoleIfNotExists("USER");
        createRoleIfNotExists("EDITOR");
        createRoleIfNotExists("CREATOR");
    }

    private void createRoleIfNotExists(String roleName) {
        if(!roleRepository.existsByRoleName(roleName)) {
            RoleEntity role = new RoleEntity();
            role.setRoleName(roleName);
            roleRepository.save(role);
        }
    }

    private void createUser() {
        if (!userRepository.existsByUsername("ADMIN")) {
            UserEntity adminUser = new UserEntity();
            adminUser.setUsername("ADMIN");
            adminUser.setPassword(passwordEncoder.encode("1234"));
            adminUser.setEnabled(true);

            // Buscar el rol ADMIN
            RoleEntity adminRole = roleRepository.findByRoleName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("ADMIN role not found"));

            // Añadir rol ADMIN al usuario
            adminUser.getRoles().add(adminRole);

            // Guardar usuario en el repositorio
            userRepository.save(adminUser);
        }
    }
}