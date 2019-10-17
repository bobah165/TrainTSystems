package com.trains.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trains")
public class Train {
    @Id
    @Column (name = "id_train")
    private int id;
    @Column(name = "count_sits")
    private int countSits;

    @OneToMany(mappedBy = "train",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Timetable> timetables;

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getCountSits() {
        return countSits;
    }

    public void setCountSits(int countSits) {
        this.countSits = countSits;
    }


    public int getId() {
        return id;
    }

    public void setId(int idTrain) {
        this.id = idTrain;
    }


    @Override
    public String toString() {
        return "TrainEntity{" +
                "id=" + id +
                ", countFreeSits= " + countSits + " }";
    }

}
