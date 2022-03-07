package com.example.PP_311_SpringBoot.converter;

import com.example.PP_311_SpringBoot.dto.UserData;
import com.example.PP_311_SpringBoot.model.User;
import com.example.PP_311_SpringBoot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private RoleService roleService;

    public User convert (UserData userData) {
        User user = new User();

        user.setName(userData.getName());
        user.setLastName(userData.getLastName());
        user.setEmail(userData.getEmail());
        user.setRoles(roleService.getByName(userData.getRoles()));
        return user;
    }
}
