package com.trains.service;

import com.trains.dao.CrudDAO;
import com.trains.dto.TrainFromStationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrainFromStationDTOService {
    private CrudDAO crudDAO;

    @Autowired
    public void setCrudDAO(CrudDAO crudDAO) {
        this.crudDAO = crudDAO;
    }

    public List<TrainFromStationDTO> getTrainFromStation(int idStation) {
        return crudDAO.getTrainFromStation(idStation);
    }
}
