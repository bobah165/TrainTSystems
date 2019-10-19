package com.trains.model.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.sql.Date;

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
    private Date arrivalTime;
    @Column(name = "departure_time")
    private Date departureTime;
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

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public int getCountFreeSits() {
        return countFreeSits;
    }

    public void setCountFreeSits(int countFreeSits) {
        this.countFreeSits = countFreeSits;
    }
}
