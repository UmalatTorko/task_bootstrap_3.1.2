package ru.gordanov.task_bootstrap_312.service;

import ru.gordanov.task_bootstrap_312.entity.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String name);

    List<Role> getAllRoles();

    void saveRole(Role role);
}
