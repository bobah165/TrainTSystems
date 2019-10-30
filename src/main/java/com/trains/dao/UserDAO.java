package com.trains.dao;

import com.trains.model.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO extends CrudDAO {


    @Override
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class,id);
    }

    public User getByName (String name) {
        Session session = sessionFactory.getCurrentSession();
        List<User> users =session.createQuery("from User").list();
        User user = new User();
        for (User user1: users) {
            if (user1.getUsername().equals(name)) {
                user = user1;
            }
        }
        return user;
    }
}
