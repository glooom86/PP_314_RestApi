package com.example.PP_311_SpringBoot.dao;

import com.example.PP_311_SpringBoot.model.Role;

import java.util.List;


public interface RoleDao {
    List<Role> getAll();
    Role getById(Long id);
    List<Role> getByName(List<String> name);
    void save(Role role);
    void deleteById(Long id);
}
