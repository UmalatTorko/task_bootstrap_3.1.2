package ru.gordanov.task_bootstrap_312.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gordanov.task_bootstrap_312.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
    Role findRoleById(long id);
}
