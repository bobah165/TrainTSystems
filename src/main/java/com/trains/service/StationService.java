package com.trains.service;

import com.trains.dao.StationDAO;
import com.trains.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StationService {
    private StationDAO stationDAO;

    @Autowired
    public void setStationDAO(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public List<Station> allStations() {
        return stationDAO.allStations();
    }

    public void add(Station station) { stationDAO.add(station); }

    public void delete(Station station) {stationDAO.delete(station); }

    public void edit(Station station) {stationDAO.edit(station); }

    public Station getById(int idStation) {
        return stationDAO.getById(idStation);
    }

}
