package ru.gordanov.task_bootstrap_312.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gordanov.task_bootstrap_312.entity.User;
import ru.gordanov.task_bootstrap_312.service.RoleService;
import ru.gordanov.task_bootstrap_312.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/user")
    public String showUser(Principal principal, Model model) {
        model.addAttribute("userPrincipal", userService.getUserByUsername(principal.getName()));
        return "showUser";
    }

    @GetMapping("/admin")
    public String adminPage(Principal principal, Model model, @ModelAttribute("newUser") User user) {
        model.addAttribute("userPrincipal", userService.getUserByUsername(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "adminPage";
    }


    @PostMapping("/admin/save")
    public String save(@ModelAttribute("user") User user,
                           @RequestParam(value = "rolesSelected") String[] roles) {
        userService.saveWithRole(user, roles);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/update")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "rolesSelected") String[] roles) {
        userService.saveWithRole(user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}