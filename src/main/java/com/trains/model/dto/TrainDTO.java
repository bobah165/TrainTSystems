package com.trains.model.dto;

import com.trains.model.entity.Ticket;
import com.trains.model.entity.Timetable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class TrainDTO {

    private int id;
    private int countSits;
    private List<Ticket> tickets;
    private List<Timetable> timetables;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountSits() {
        return countSits;
    }

    public void setCountSits(int countSits) {
        this.countSits = countSits;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }
}
