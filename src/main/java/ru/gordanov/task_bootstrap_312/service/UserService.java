package ru.gordanov.task_bootstrap_312.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.gordanov.task_bootstrap_312.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);

    List<User> getAllUsers();

    void saveOrUpdate(User user);

    void saveWithRole(User user, String[] roles);

    User getUserById(long id);

    void delete(long id);
}
