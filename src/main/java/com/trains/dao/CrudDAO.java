package com.trains.dao;

import com.trains.dto.PassFromTrainDTO;
import com.trains.model.Passenger;
import com.trains.model.Train;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class CrudDAO {
    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public <T> void add(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(object);
    }

    public <T> void delete(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

    public <T> void edit(T object) {
        Session session = sessionFactory.getCurrentSession();
        session.update(object);
    }

    public List<PassFromTrainDTO> getPassFromTrain(int idTrain) {
        Session session = sessionFactory.getCurrentSession();
        List<PassFromTrainDTO> passFromTrainDTOS = new ArrayList<>();
        List<Passenger> passengers = session.createQuery("from Passenger").list();
        Train train = session.get(Train.class,idTrain);

        for (Passenger pass:passengers ) {
            if (idTrain==pass.getTrain().getId()) {
                PassFromTrainDTO passFromTrain = new PassFromTrainDTO();
                passFromTrain.setName(pass.getName());
                passFromTrain.setSurname(pass.getSurname());
                passFromTrain.setTrainId(train.getId());
                passFromTrainDTOS.add(passFromTrain);
            }
        }

        return passFromTrainDTOS;
    }

}
