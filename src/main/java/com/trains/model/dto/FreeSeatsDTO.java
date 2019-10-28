package com.trains.model.dto;

import java.util.Objects;

public class FreeSeatsDTO {

    private int id;
    private int idTrain;
    private String stationName;
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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FreeSeatsDTO that = (FreeSeatsDTO) object;
        return getId() == that.getId() &&
                getIdTrain() == that.getIdTrain() &&
                getFreeSeats() == that.getFreeSeats() &&
                Objects.equals(getStationName(), that.getStationName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIdTrain(), getStationName(), getFreeSeats());
    }
}
