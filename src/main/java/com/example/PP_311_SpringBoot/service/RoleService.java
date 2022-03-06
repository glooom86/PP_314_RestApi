package com.example.PP_311_SpringBoot.service;

import com.example.PP_311_SpringBoot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getById(Long id);
    void save(Role role);
    void deleteById(Long id);
}
