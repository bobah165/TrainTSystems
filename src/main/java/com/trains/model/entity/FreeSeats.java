package com.trains.model.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "free_seats")
public class FreeSeats {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;

    @NaturalId(mutable = true)
    @Column(name = "id_train")
    private int idTrain;
    @NaturalId(mutable = true)
    @Column(name = "station_name")
    private String stationName;
    @Column(name = "free_seats")
    private int freeSeats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    @Override
    public String toString() {
        return "Free seats {" +
                "id=" + id +
                ", train id " + idTrain +
                ", station is "+stationName+
                ", free seats is " + freeSeats +
                " }";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FreeSeats freeSeats1 = (FreeSeats) object;
        return getId() == freeSeats1.getId() &&
                getIdTrain() == freeSeats1.getIdTrain() &&
                getFreeSeats() == freeSeats1.getFreeSeats();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdTrain(), getFreeSeats());
    }
}
