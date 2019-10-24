package com.trains.model.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "time_table")
public class Timetable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;

    @NaturalId (mutable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_train")
    private Train train;

    @NaturalId (mutable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_station")
    private Station station;

    @Column(name = "arrival_time")
    private Time arrivalTime;
    @Column(name = "departure_time")
    private Time departureTime;
    @Column(name = "count_free_sits")
    private int countFreeSits;


    public Train getTrain() {
        return train;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public int getCountFreeSits() {
        return countFreeSits;
    }

    public void setCountFreeSits(int countFreeSits) {
        this.countFreeSits = countFreeSits;
    }
}
