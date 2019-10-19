package com.trains.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stations")
public class Station {
    @Id
    @Column (name = "id_station")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_station")
    private String nameStation;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Timetable> timetables;

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }

    public int getId() {
        return id;
    }

    public void setId(int id_station) {
        this.id = id_station;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }


}
