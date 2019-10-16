package com.trains.dto;

public class PassFromTrainDTO {
    private String name;
    private String surname;
    private int trainId;

    public int getTrainId() { return trainId; }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Override
    public String toString() {
        return "TrainEntity{" +
                "id train is "+trainId+
                ", name is " + name +
                ", surname is " + surname + " }";
    }
}
