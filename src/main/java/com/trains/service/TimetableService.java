package com.trains.service;

import com.trains.dao.TimetableDAO;
import com.trains.model.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TimetableService {
    private TimetableDAO timetableDAO;

    @Autowired
    public void setTimetableDAO(TimetableDAO timetableDAO) {
        this.timetableDAO = timetableDAO;
    }

    public List<Timetable> allTimetable() {
        return timetableDAO.allTimetable();
    }

    public void add(Timetable timetable) { timetableDAO.add(timetable); }

    public void delete(Timetable timetable) {timetableDAO.delete(timetable); }

    public void edit(Timetable timetable) {timetableDAO.edit(timetable); }

    public Timetable getById(int id) { return timetableDAO.getById(id); }

}
