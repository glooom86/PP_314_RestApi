package com.example.PP_311_SpringBoot.dao;

import com.example.PP_311_SpringBoot.model.User;
import com.example.PP_311_SpringBoot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoleService roleService;

    public UserDaoImpl(){}


    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void save(User user, String[] roles) {
        if (roles.length == 2) {
            user.setRoles(roleService.getAll());
        } else {
            user.setRoles(List.of(roleService.getByName(roles[0])));
        }
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
        em.merge(updatedUser);
    }

    @Override
    public void deleteById(Long id) {
        User user = em.find(User.class, id);
        if(user != null) {
            em.remove(user);
        }
    }
}
