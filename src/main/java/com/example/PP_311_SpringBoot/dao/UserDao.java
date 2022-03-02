package com.example.PP_311_SpringBoot.dao;


import com.example.PP_311_SpringBoot.model.User;

import java.util.List;

public interface UserDao {
    List<User> index();
    User show(Long id);
    void save(User user);
    void update(User updatedUser);
    void deleteById(Long id);
}
