package com.example.PP_311_SpringBoot.service;

import com.example.PP_311_SpringBoot.dao.RoleDao;
import com.example.PP_311_SpringBoot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    @Transactional
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        roleDao.deleteById(id);
    }
}
