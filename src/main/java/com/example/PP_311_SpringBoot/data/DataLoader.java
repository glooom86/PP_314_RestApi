package com.example.PP_311_SpringBoot.data;

import com.example.PP_311_SpringBoot.model.Role;
import com.example.PP_311_SpringBoot.model.User;
import com.example.PP_311_SpringBoot.service.RoleService;
import com.example.PP_311_SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleService roleService;
    private UserService userService;

    @Autowired
    public DataLoader(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    private void saveRole(){
        Role roleAdmin = new Role("ROLE_ADMIN", "ADMIN");
        Role roleUser = new Role( "ROLE_USER", "USER");
        roleService.save(roleAdmin);
        roleService.save(roleUser);
    }

    private void saveUser(){
        User user1 = new User("admin","Ivanov","admin","admin@mail.ru", List.of(roleService.getById(1L)));
        User user2 = new User("user", "Ara", "user", "user@mail.ru", List.of(roleService.getById(2L)));
        userService.save(user1);
        userService.save(user2);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        saveRole();
        saveUser();
    }
}
