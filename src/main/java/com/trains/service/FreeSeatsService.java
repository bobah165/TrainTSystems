package com.trains.service;

import com.trains.dao.FreeSeatsDAO;
import com.trains.model.dto.FreeSeatsDTO;
import com.trains.model.dto.TicketInformDTO;
import com.trains.model.dto.TrainDTO;
import com.trains.model.dto.TrainWayDTO;
import com.trains.model.entity.FreeSeats;
import com.trains.util.mapperForDTO.FreeSeatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@Transactional
public class FreeSeatsService {
    private FreeSeatsDAO freeSeatsDAO;
    private FreeSeatsMapper freeSeatsMapper;
    private TrainWayService trainWayService;

    @Autowired
    public void setTrainWayService(TrainWayService trainWayService) {
        this.trainWayService = trainWayService;
    }

    @Autowired
    public void setFreeSeatsDAO(FreeSeatsDAO freeSeatsDAO) {
        this.freeSeatsDAO = freeSeatsDAO;
    }

    @Autowired
    public void setFreeSeatsMapper(FreeSeatsMapper freeSeatsMapper) {
        this.freeSeatsMapper = freeSeatsMapper;
    }

    public List<FreeSeatsDTO> allSeats() {
        List<FreeSeats> freeSeats = freeSeatsDAO.allSeats();
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


    public boolean checkFreeSeats(TrainDTO train, TicketInformDTO ticketInformDTO) {
        boolean isAddTicket = true;
        //находим все станции по маршруту поезда
        List<TrainWayDTO> trainOneWAy = new ArrayList<>();
        List<TrainWayDTO> trainWayDTOS = trainWayService.allWays();

        for (TrainWayDTO trainWayDTO: trainWayDTOS){
            if (trainWayDTO.getNumberWay()==train.getTrainWay().getNumberWay())
                trainOneWAy.add(trainWayDTO);
        }

        //сортировка полученного расписания для одной станции по времени отправления
        List<TrainWayDTO> sortedTrainWay= trainOneWAy.stream()
                .sorted(Comparator.comparing(TrainWayDTO::getDaysInWay)
                                    .thenComparing(TrainWayDTO::getShedule))
                .collect(Collectors.toList());

        //добавляем данные о поезде в таблицу free_sets
        List<FreeSeats> freeSeats = freeSeatsDAO.allSeats();
        List<FreeSeats> freeSeatInWay = new ArrayList<>();
        for (FreeSeats freeSeat: freeSeats){
            for(TrainWayDTO trainWayDTO: sortedTrainWay) {
                if (freeSeat.getIdTrain() == train.getId()
                        && freeSeat.getStationName().equals(trainWayDTO.getStation().getNameStation())) {
                    freeSeatInWay.add(freeSeat);
                }
            }
        }

        // если в таблице нет информации о свободных местах то заполняем ее
        if (freeSeatInWay.isEmpty()) {
            for (TrainWayDTO trainWayDTO: sortedTrainWay) {
                FreeSeats seatsDTO = new FreeSeats();
                seatsDTO.setStationName(trainWayDTO.getStation().getNameStation());
                seatsDTO.setIdTrain(train.getId());
                seatsDTO.setFreeSeats(train.getCountSits());
                freeSeatsDAO.add(seatsDTO);
            }
        }

        List<FreeSeats> sortedFreeSeatInWay = freeSeatInWay.stream()
                .sorted(Comparator.comparing(FreeSeats::getId))
                .collect(Collectors.toList());
        // поиск станций на которые покупается билет
        for (FreeSeats freeSeats1: sortedFreeSeatInWay) {

            // поиск станции отправления
            if(freeSeats1.getStationName().equals(ticketInformDTO.getDepartureStation())) {
                if(freeSeats1.getFreeSeats()>0) {
                    freeSeats1.setFreeSeats(freeSeats1.getFreeSeats() - 1);
                    freeSeatsDAO.edit(freeSeats1);
                } else {
                    isAddTicket = false;
                    return isAddTicket;
                }
            }
        }

        // добавление билетов (уменьшение числа свободных мест)
        // получаем записи из таблицы Free Seats о станции отправления и прибытия
        FreeSeats freeSeatsDeparture  = freeSeatsDAO.getByStationAndTrainID(ticketInformDTO.getDepartureStation(),ticketInformDTO.getIdTrain());
        FreeSeats freeSeatsArrival = freeSeatsDAO.getByStationAndTrainID(ticketInformDTO.getArrivalStation(),ticketInformDTO.getIdTrain());

        if(((freeSeatsDeparture.getId()+1)!=freeSeatsArrival.getId())) {

            int depNumber =  sortedFreeSeatInWay.indexOf(freeSeatsDeparture);
            int arrNumber = sortedFreeSeatInWay.indexOf(freeSeatsArrival);

                for (int j = depNumber+1; j<arrNumber;j++ ) {
                    if(sortedFreeSeatInWay.get(j).getFreeSeats()>0) {
                        sortedFreeSeatInWay.get(j).setFreeSeats(sortedFreeSeatInWay.get(j).getFreeSeats()-1);
                        freeSeatsDAO.edit(sortedFreeSeatInWay.get(j));
                    } else {
                        isAddTicket = false;
                        return isAddTicket;
                    }
                }
        }
        return isAddTicket;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        FreeSeatsService that = (FreeSeatsService) object;
        return Objects.equals(freeSeatsDAO, that.freeSeatsDAO) &&
                Objects.equals(freeSeatsMapper, that.freeSeatsMapper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(freeSeatsDAO, freeSeatsMapper);
    }
}
