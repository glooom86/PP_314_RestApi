package com.example.PP_311_SpringBoot.controller;

import com.example.PP_311_SpringBoot.converter.UserConverter;
import com.example.PP_311_SpringBoot.dto.UserData;
import com.example.PP_311_SpringBoot.model.User;
import com.example.PP_311_SpringBoot.service.RoleService;
import com.example.PP_311_SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
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
        model.addAttribute("roleList", roleService.getAll());
        return "admin";
    }
}
