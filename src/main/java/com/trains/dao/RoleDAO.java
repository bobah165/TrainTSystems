package com.trains.dao;

import com.trains.model.entity.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAO extends CrudDAO {
    @Override
    public Role getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class,id);
    }
}
