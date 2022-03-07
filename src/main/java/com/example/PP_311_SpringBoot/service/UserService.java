package com.example.PP_311_SpringBoot.service;



import com.example.PP_311_SpringBoot.model.Role;
import com.example.PP_311_SpringBoot.model.User;
import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long id);
    void save(User user);
    void update(User updatedUser);
    void deleteById(Long id);
}
