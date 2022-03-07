package com.example.PP_311_SpringBoot.dao;

import com.example.PP_311_SpringBoot.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    User getById(Long id);
    User getByName(String name);
    void save(User user);
    void update(User updatedUser);
    void deleteById(Long id);
}
