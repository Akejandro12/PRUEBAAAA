package com.app.pw.prueba.service.Impl;

import com.app.pw.prueba.model.RoleEntity;
import com.app.pw.prueba.model.UserEntity;
import com.app.pw.prueba.repository.RoleRepository;
import com.app.pw.prueba.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UserEntity findUserByUserName(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void saveUser(UserEntity user, List<Integer> roleIds) {
        // Encriptar contraseña si es un usuario nuevo o si se cambió la contraseña
        if (user.getUserId() == null || !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        user.setEnabled(true);
        // Asignar roles
        Set<RoleEntity> roles = new HashSet<>(roleRepository.findAllById(roleIds));
        user.setRoles(roles);

        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
