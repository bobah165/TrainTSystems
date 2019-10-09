package com.trains.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class MainDAO {
    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void add(Object object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
    }

    public void delete(Object object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

    public void edit(Object object) {
        Session session = sessionFactory.getCurrentSession();
        session.update(object);
    }

}
