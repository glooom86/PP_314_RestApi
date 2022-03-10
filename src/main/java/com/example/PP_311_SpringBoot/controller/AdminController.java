package com.example.PP_311_SpringBoot.controller;

import com.example.PP_311_SpringBoot.converter.UserConverter;
import com.example.PP_311_SpringBoot.dto.UserData;
import com.example.PP_311_SpringBoot.model.User;
import com.example.PP_311_SpringBoot.service.RoleService;
import com.example.PP_311_SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserConverter userConverter;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserConverter userConverter) {
        this.userService = userService;
        this.roleService = roleService;
        this.userConverter = userConverter;
    }

    @GetMapping()
    public String index(Principal principal, Model model) {
        User userBd = userService.getByName(principal.getName());
        model.addAttribute("currentUser", userBd);
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userService.getAll());
        model.addAttribute("roleList", roleService.getAll());
        return "admin";
    }

    @GetMapping("users/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }

    @PostMapping("users")
    public String create(@ModelAttribute("user") UserData userData) {
        User user = userConverter.convert(userData);
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("users/{id}")
    public String update(@ModelAttribute("user") UserData userData,
                         @PathVariable("id") Long id) {
        User user = userConverter.convert(userData);
        user.setId(id);
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("users/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
