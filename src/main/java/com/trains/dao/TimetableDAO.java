package com.trains.dao;

import com.trains.model.Timetable;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimetableDAO extends CrudDAO {
    public List<Timetable> allTimetable() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Timetable ").list();
    }

    public Timetable getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Timetable.class,id);
    }
}
