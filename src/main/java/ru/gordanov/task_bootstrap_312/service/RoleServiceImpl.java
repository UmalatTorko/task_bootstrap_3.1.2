package ru.gordanov.task_bootstrap_312.service;

import org.springframework.stereotype.Service;
import ru.gordanov.task_bootstrap_312.entity.Role;
import ru.gordanov.task_bootstrap_312.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String name) {
       return roleRepository.findRoleByName(name);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
