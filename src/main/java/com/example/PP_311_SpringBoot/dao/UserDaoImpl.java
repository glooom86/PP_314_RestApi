package com.example.PP_311_SpringBoot.dao;

import com.example.PP_311_SpringBoot.model.User;
import com.example.PP_311_SpringBoot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDaoImpl(){}


    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        em.persist(user);
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query = em
                .createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public User getById(Long id) {
        TypedQuery<User> query = em
                .createQuery("select u from User u WHERE u.id =:id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(User updatedUser) {
        if (updatedUser.getPassword().length() == 0) {
            updatedUser.setPassword(getById(updatedUser.getId()).getPassword());
        } else {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        em.merge(updatedUser);
    }

    @Override
    public void deleteById(Long id) {
        User user = em.find(User.class, id);
        if(user != null) {
            em.remove(user);
        }
    }

    @Override
    public User getByName(String name) {
        TypedQuery<User> query = em
                .createQuery("select u from User u WHERE u.name =:name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
