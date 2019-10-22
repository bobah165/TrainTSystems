package com.trains.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class CrudDAO {
    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public <T> void add(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
    }

    public <T> void delete(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

    public <T> void edit(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.update(object);
    }

    public abstract <T> T getById(int id);
}
