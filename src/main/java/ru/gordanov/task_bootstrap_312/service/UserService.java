package ru.gordanov.task_bootstrap_312.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gordanov.task_bootstrap_312.entity.User;
import ru.gordanov.task_bootstrap_312.repository.UserRepository;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByEmail(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveOrUpdate(User user) {
        User userDB = getUserById(user.getId());
        if (userDB != null && !user.getPassword().equals(userDB.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        if (userDB == null){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public void saveWithRole(User user, String[] roles) {
        user.setRoles(Arrays.stream(roles)
                .map(roleService::getRoleByName)
                .collect(Collectors.toSet()));
        saveOrUpdate(user);
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
