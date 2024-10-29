package com.app.pw.prueba.service.Impl;

import com.app.pw.prueba.model.RoleEntity;
import com.app.pw.prueba.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    public void saveRole(RoleEntity role) {
        roleRepository.save(role);
    }
}
