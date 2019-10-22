package com.trains.dao;

import com.trains.model.entity.Station;
import com.trains.model.entity.Timetable;
import com.trains.model.entity.Train;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Timetable.class,id));
    }

    public Timetable getTimetableByTrainAndStation (Train train, Station station) {
        Session session = sessionFactory.getCurrentSession();
        List<Timetable> timetables = session.createQuery("from Timetable ").list();
        Timetable timetable = new Timetable();
        for (Timetable timetableFromList: timetables){
            if (timetableFromList.getTrain().equals(train)&&
            timetableFromList.getStation().equals(station)) {
                timetable = timetableFromList;
                break;
            }
        }
        return timetable;
    }

}
