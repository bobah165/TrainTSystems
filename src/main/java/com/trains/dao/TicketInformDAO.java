package com.trains.dao;

import com.trains.model.dto.*;
import com.trains.model.entity.SearchStations;
import com.trains.model.entity.TicketInform;
import com.trains.model.entity.Train;
import com.trains.model.entity.TrainWay;
import org.hibernate.Session;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Repository
public class TicketInformDAO extends CrudDAO {

    public List<TicketInform> allTickets() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from TicketInform ").list();
    }

    @Override
    public TicketInform getById(int idTicket) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TicketInform.class,idTicket);
    }

    public void delByID (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(TicketInform.class,id));
    }

    public TicketInform fullInformation(SearchStations searchStation, List<TrainWay> trainWays, Train train) {

        Time departureTime = Time.valueOf("00:00:00");
        Time arrivalTime = Time.valueOf("00:00:00");

        LocalDate arravalDate = LocalDate.of(1990,01,01);
        int idCurrentPassenger = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        for (TrainWay trainWay:trainWays){
            int currentWay = train.getTrainWay().getNumberWay();
            if (trainWay.getNumberWay()== currentWay){
                if (trainWay.getStation().getNameStation().equals(searchStation.getDepartureStation())){
                    departureTime=Time.valueOf(trainWay.getDepartureTime().toLocalTime());
                }
                if(trainWay.getStation().getNameStation().equals(searchStation.getArrivalStation())){
                    arrivalTime = Time.valueOf(trainWay.getArrivalTime().toLocalTime());
                }

                arravalDate = train.getDepartureDate().plusDays(trainWay.getDaysInWay()-1);

            }
        }

        TicketInform ticketInform = new TicketInform();
        ticketInform.setId(idCurrentPassenger); // по ID пользователя

        ticketInform.setIdTrain(train.getId());
        ticketInform.setDepartureStation(searchStation.getDepartureStation());
        ticketInform.setArrivalStation(searchStation.getArrivalStation());

        ticketInform.setArrivalDate(arravalDate);
        ticketInform.setDepartureDate(searchStation.getDepartureDate());
        ticketInform.setDepartureTime(departureTime);
        ticketInform.setArrivalTime(arrivalTime);

        ticketInform.setBirthday(LocalDate.of(1990,01,01));
        ticketInform.setName("none");
        ticketInform.setSurname("none");

        return ticketInform;
    }
}
