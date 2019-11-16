package com.trains.service;

import com.trains.dao.SearchStationDAO;
import com.trains.dao.TrainDAO;
import com.trains.model.dto.PassengerDTO;
import com.trains.model.dto.SearchStationDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.dto.TrainFromStationAToB;
import com.trains.model.entity.SearchStations;
import com.trains.model.entity.Train;
import com.trains.util.mapperForDTO.SearchStationMapper;
import com.trains.util.mapperForDTO.TrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class SearchStationService {
    private SearchStationDAO searchStationDAO;
    private SearchStationMapper searchStationMapper;
    private TrainDAO trainDAO;

    @Autowired
    public void setTrainDAO(TrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

    @Autowired
    public void setSearchStationDAO(SearchStationDAO searchStationDAO) {
        this.searchStationDAO = searchStationDAO;
    }

    @Autowired
    public void setSearchStationMapper(SearchStationMapper searchStationMapper) {
        this.searchStationMapper = searchStationMapper;
    }

    public void add(SearchStationDTO searchStationDTO) {
        SearchStations searchStations = searchStationMapper.mapDtoToEntity(searchStationDTO);
        searchStationDAO.add(searchStations);
    }

    public void delete(SearchStationDTO searchStationDTO) {
        SearchStations searchStations = searchStationMapper.mapDtoToEntity(searchStationDTO);
        searchStationDAO.delete(searchStations);
    }

    public void edit(SearchStationDTO searchStationDTO) {
        SearchStations searchStations = searchStationMapper.mapDtoToEntity(searchStationDTO);
        searchStationDAO.edit(searchStations);
    }

    public SearchStationDTO getById(int id) {
        SearchStations searchStations = searchStationDAO.getById(id);
        SearchStationDTO searchStationDTO = searchStationMapper.mapEntityToDto(searchStations);
        return searchStationDTO;
    }

    public List<SearchStationDTO> getAllStations() {
        List<SearchStations> searchStations = searchStationDAO.allTrains();
        List<SearchStationDTO> searchStationDTOS = new ArrayList<>();
        for (SearchStations searchStation: searchStations) {
            searchStationDTOS.add(searchStationMapper.mapEntityToDto(searchStation));
        }
        return searchStationDTOS;
    }

    public void delByID (int id) {searchStationDAO.delByID(id);}

    public List<TrainFromStationAToB> getTrainsFromStations (String stationNameA, String stationNameB, LocalTime startTime, LocalTime endTime, LocalDate departureDate) {
        return searchStationDAO.getTrainsFromStations(stationNameA,stationNameB,startTime,endTime, departureDate);
    }


    public void addInformationAboutSearch (String stationA, String stationB, LocalDate departureDate, String startTime, String endTime) {

        int idCurrentPassenger = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        SearchStations searchStation = new SearchStations();
        searchStation.setId(idCurrentPassenger);
        searchStation.setDepartureDate(departureDate);
        searchStation.setStartTime(LocalTime.parse(startTime));
        searchStation.setDepartureStation(stationA);
        searchStation.setArrivalStation(stationB);
        searchStation.setEndTime(LocalTime.parse(endTime));

        searchStationDAO.add(searchStation);
    }


    public void addTrainBySchedule (LocalDate departureDate) {

        List<Train> trainList = new ArrayList<>();
        boolean everydayFlag = true;
        boolean oddFlag = true;
        boolean evenFlag = true;

        Set<Train> trains =new HashSet<>(trainDAO.allTrain());

        for (Train train : trains) {
            // поезд с ежедневным расписанием
            if (train.getSchedule().equals("everyday")&&everydayFlag) {
                everydayFlag = false;
                Train newTrain = new Train();
                newTrain.setDepartureDate(departureDate);
                newTrain.setSchedule(train.getSchedule());
                newTrain.setCountSits(train.getCountSits());
                newTrain.setTrainNumber(train.getTrainNumber());
                newTrain.setTrainWay(train.getTrainWay());
                trainList.add(newTrain);
                continue;
            }
            // поезд двигающийся по четным дням
            if (departureDate.getDayOfMonth() % 2 == 0 && train.getSchedule().equals("even") && evenFlag) {
                evenFlag = false;
                Train newTrain = new Train();
                newTrain.setDepartureDate(departureDate);
                newTrain.setSchedule(train.getSchedule());
                newTrain.setCountSits(train.getCountSits());
                newTrain.setTrainNumber(train.getTrainNumber());
                newTrain.setTrainWay(train.getTrainWay());
               trainList.add(newTrain);
                continue;
            }

            // поезд двигающийся по нечетным дням
            if (departureDate.getDayOfMonth() % 2 != 0 && train.getSchedule().equals("odd")&&oddFlag) {
                oddFlag = false;
                Train newTrain = new Train();
                newTrain.setDepartureDate(departureDate);
                newTrain.setSchedule(train.getSchedule());
                newTrain.setCountSits(train.getCountSits());
                newTrain.setTrainNumber(train.getTrainNumber());
                newTrain.setTrainWay(train.getTrainWay());
                trainList.add(newTrain);
                continue;
            }
        }

        List<Train> trainIndate = trainDAO.getTrainsByDepartureDate(departureDate);

        if (trainIndate.size()==trainList.size()) {
            return;
        }else {
            for (Train train: trainList) {
                if (!trainIndate.contains(train)) {
                    trainDAO.add(train);
                }
            }
        }
    }
}
