package com.example.PP_311_SpringBoot.controller;

import com.example.PP_311_SpringBoot.model.User;
import com.example.PP_311_SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        return "index";
    }

    @GetMapping("users/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "show";
    }

    @GetMapping("users/new")
    public String addUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("users")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("users/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PatchMapping("users/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("users/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
