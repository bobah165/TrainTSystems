package com.trains.service;

import com.trains.dao.StationDAO;
import com.trains.dao.TrainDAO;
import com.trains.dao.TrainWayDAO;
import com.trains.model.dto.StationDTO;
import com.trains.model.dto.TrainFromStationDTO;
import com.trains.model.entity.Station;
import com.trains.model.entity.Train;
import com.trains.model.entity.TrainWay;
import com.trains.util.mapperForDTO.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StationService {
    private StationMapper stationMapper;
    private StationDAO stationDAO;
    private TrainWayDAO trainWayDAO;
    private TrainDAO trainDAO;

    @Autowired
    public void setTrainDAO(TrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

    @Autowired
    public void setTrainWayDAO(TrainWayDAO trainWayDAO) {
        this.trainWayDAO = trainWayDAO;
    }

    @Autowired
    public void setStationDAO(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    @Autowired
    public void setStationMapper(StationMapper stationMapper) {
        this.stationMapper = stationMapper;
    }



    public List<StationDTO> getAllStations() {
        List<Station> stations = stationDAO.getAllStations();
        List<StationDTO> stationDTOS = new ArrayList<>();
        for (Station station: stations) {
            stationDTOS.add(stationMapper.mapEntityToDto(station));
        }
    return stationDTOS;
    }

    public List<StationDTO> getStationsForPagination(int page) {
        List<Station> stations = stationDAO.getStationsForPagination(page);
        List<StationDTO> stationDTOS = new ArrayList<>();
        for (Station station: stations) {
            stationDTOS.add(stationMapper.mapEntityToDto(station));
        }
        return stationDTOS;
    }

    public int getCountStationsForPagination() {
        return stationDAO.getCountStationsForPagination();
    }

    public void add(StationDTO stationDTO) {
        Station station = stationMapper.mapDtoToEntity(stationDTO);
        stationDAO.add(station); }

    public void delete(StationDTO stationDTO) {
        Station station = stationMapper.mapDtoToEntity(stationDTO);
        stationDAO.delete(station); }

    public void edit(StationDTO stationDTO) {
        Station station = stationMapper.mapDtoToEntity(stationDTO);
        stationDAO.edit(station); }

    public StationDTO getById(int id) {
        Station station = stationDAO.getById(id);
        return stationMapper.mapEntityToDto(station);
    }



    public List<TrainFromStationDTO> getTrainFromStation(int idStation, LocalDate departureDate, LocalTime startTime, LocalTime endTime) {

        List<TrainFromStationDTO> trainFromStationDTOS = new ArrayList<>();
        List<TrainWay> trainWays = trainWayDAO.getWaysByStationId(idStation);
        Station station = stationDAO.getById(idStation);

        //поиск поездов ходящих по маршруту в котором есть искомая станция
        for (TrainWay trainWay : trainWays) {
            trainFromStationDTOS = getTrainsFromStationInDate(departureDate, station, trainFromStationDTOS, trainWay);
        }

        // из полученного листа поездов по дате ищем поезда удовлетворяющие заданному промежутку времени
        List<TrainFromStationDTO> finalTrainList = new ArrayList<>();
        for (TrainFromStationDTO trainFromStationDTO : trainFromStationDTOS) {
            LocalTime trainTime = trainFromStationDTO.getDepartureTime();
            if (trainTime.isAfter(startTime) && trainTime.isBefore(endTime)) {
                finalTrainList.add(trainFromStationDTO);
            }
        }
        return finalTrainList;
    }



    public List<TrainFromStationDTO> getTrainsFromStationInDate (LocalDate departureDate, Station station,
                                            List<TrainFromStationDTO> trainFromStationDTOS,TrainWay trainWay) {
        int daysInWay = 0;
        TrainWay trainWayDTOS = trainWayDAO.getTrainWayByStationAndWay(station.getNameStation(), trainWay.getNumberWay());
        daysInWay = trainWayDTOS.getDaysInWay() - 1;
        LocalDate currentDepartureDate = departureDate.minusDays(daysInWay);

        List<Train> trains = trainDAO.getTrainsByDepartureDateAndNumberWay(currentDepartureDate, trainWay.getNumberWay());
        if (trains.isEmpty()) {
            return trainFromStationDTOS;
        }
            TrainFromStationDTO trainFromStationDTO = new TrainFromStationDTO();
            trainFromStationDTO.setIdTrain(trains.get(0).getId());
            trainFromStationDTO.setNameStation(station.getNameStation());
            trainFromStationDTO.setDepartureTime(trainWay.getDepartureTime().toLocalTime());
            trainFromStationDTO.setArrivalTime(trainWay.getArrivalTime().toLocalTime());

            trainFromStationDTOS.add(trainFromStationDTO);

        return trainFromStationDTOS;
    }


    public void delByID (int id) { stationDAO.delByID(id); }


    public StationDTO getByName (String name) {
        Station station = stationDAO.getByName(name);
        return stationMapper.mapEntityToDto(station);
    }

}
