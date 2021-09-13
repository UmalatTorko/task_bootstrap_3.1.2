package ru.gordanov.task_bootstrap_312.service;

import org.springframework.stereotype.Service;
import ru.gordanov.task_bootstrap_312.entity.Role;
import ru.gordanov.task_bootstrap_312.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String name) {
       return roleRepository.findRoleByName(name);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
