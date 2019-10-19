package com.trains.model.dto;

import com.trains.model.entity.Timetable;
import java.util.List;

public class StationDTO {

    private int id;
    private String nameStation;
    private List<Timetable> timetables;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }
}
