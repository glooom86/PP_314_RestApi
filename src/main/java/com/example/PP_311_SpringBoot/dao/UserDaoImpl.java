package com.example.PP_311_SpringBoot.dao;

import com.example.PP_311_SpringBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoleDao roleDao;

    public UserDaoImpl(){}


    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void save(User user, String[] roles) {
        if (roles.length == 2) {
            user.setRoles(new HashSet<>(roleDao.getAll()));
        } else {
            user.setRoles(Set.of(roleDao.getByName(roles[0])));
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
