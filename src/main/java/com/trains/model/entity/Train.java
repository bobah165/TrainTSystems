package com.trains.model.entity;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "trains")
public class Train {
    @Id
    @Column (name = "id_train")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId(mutable = true)
    @Column (name = "train_number")
    private int trainNumber;

    @Column(name = "count_sits")
    @DecimalMin(value = "0")
    private int countSits;

    @OneToMany(mappedBy = "train",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Ticket> tickets;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_way")
    private TrainWay trainWay;

    @NaturalId(mutable = true)
    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "schedule")
    private String schedule;

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departure_date) {
        this.departureDate = departure_date;
    }

    public TrainWay getTrainWay() {
        return trainWay;
    }

    public void setTrainWay(TrainWay trainWay) {
        this.trainWay = trainWay;
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

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
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
                ", count Sits = " + countSits +
                ", way id is = "+trainWay.getNumberWay()+
                ", departure date is "+departureDate+
                " }";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Train train = (Train) object;
        return getTrainNumber() == train.getTrainNumber() &&
                (getTrainWay().getNumberWay() == train.getTrainWay().getNumberWay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrainNumber(), getTrainWay());
    }
}
