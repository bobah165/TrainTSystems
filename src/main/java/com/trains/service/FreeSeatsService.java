package com.trains.service;

import com.trains.dao.FreeSeatsDAO;
import com.trains.dao.TrainWayDAO;
import com.trains.model.dto.FreeSeatsDTO;
import com.trains.model.entity.FreeSeats;
import com.trains.util.mapperForDTO.FreeSeatsMapper;
import com.trains.util.mapperForDTO.TrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Controller
@Transactional
public class FreeSeatsService {
    private FreeSeatsDAO freeSeatsDAO;
    private FreeSeatsMapper freeSeatsMapper;
    private TrainWayDAO trainWayDAO;
    private TrainMapper trainMapper;

    @Autowired
    public void setTrainMapper(TrainMapper trainMapper) {
        this.trainMapper = trainMapper;
    }

    @Autowired
    public void setTrainWayDAO(TrainWayDAO trainWayDAO) {
        this.trainWayDAO = trainWayDAO;
    }


    @Autowired
    public void setFreeSeatsDAO(FreeSeatsDAO freeSeatsDAO) {
        this.freeSeatsDAO = freeSeatsDAO;
    }

    @Autowired
    public void setFreeSeatsMapper(FreeSeatsMapper freeSeatsMapper) {
        this.freeSeatsMapper = freeSeatsMapper;
    }

    public List<FreeSeatsDTO> getAllSeats() {
        List<FreeSeats> freeSeats = freeSeatsDAO.getAllSeats();
        List<FreeSeatsDTO> freeSeatsDTOS = new ArrayList<>();
        for (FreeSeats freeSeat: freeSeats) {
            freeSeatsDTOS.add(freeSeatsMapper.mapEntityToDto(freeSeat));
        }
        return freeSeatsDTOS;
    }

    public void add(FreeSeatsDTO freeSeatsDTO) {
        FreeSeats freeSeats = freeSeatsMapper.mapDtoToEntity(freeSeatsDTO);
        freeSeatsDAO.add(freeSeats);
    }

    public void delete(FreeSeatsDTO freeSeatsDTO) {
        FreeSeats freeSeats = freeSeatsMapper.mapDtoToEntity(freeSeatsDTO);
        freeSeatsDAO.delete(freeSeats); }

    public void edit(FreeSeatsDTO freeSeatsDTO) {
        FreeSeats freeSeats = freeSeatsMapper.mapDtoToEntity(freeSeatsDTO);
        freeSeatsDAO.edit(freeSeats); }


    public FreeSeatsDTO getById(int id) {
        FreeSeats freeSeats = freeSeatsDAO.getById(id);
        FreeSeatsDTO freeSeatsDTO = freeSeatsMapper.mapEntityToDto(freeSeats);
        return freeSeatsDTO;
    }

    public FreeSeatsDTO getByStationAndTrainID (String stationName, int trainID) {
        FreeSeats freeSeats = freeSeatsDAO.getByStationAndTrainID(stationName,trainID);
        FreeSeatsDTO freeSeatsDTO = freeSeatsMapper.mapEntityToDto(freeSeats);
        return freeSeatsDTO;
    }

}
