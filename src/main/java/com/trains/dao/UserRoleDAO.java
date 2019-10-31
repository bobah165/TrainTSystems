package com.trains.dao;

import com.trains.model.entity.UserRole;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDAO extends CrudDAO {
    @Override
    public UserRole getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(UserRole.class,id);
    }
}
