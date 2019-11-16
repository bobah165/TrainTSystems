package com.trains.util.validator;

import com.trains.model.dto.TrainDTO;
import com.trains.model.dto.TrainWayDTO;
import com.trains.service.TrainService;
import com.trains.service.TrainWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.List;

@Component
public class TrainDTOValidator implements Validator {
    private TrainService trainService;
    private TrainWayService trainWayService;

    @Autowired
    public void setTrainWayService(TrainWayService trainWayService) {
        this.trainWayService = trainWayService;
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TrainDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TrainDTO trainDTO = (TrainDTO) o;
        List<TrainDTO> trainDTOS = trainService.allTrains();
        for (TrainDTO train: trainDTOS) {
            if (train.getTrainNumber()==trainDTO.getTrainNumber()&&
            train.getDepartureDate().equals(trainDTO.getDepartureDate())) {
                errors.rejectValue(
                        "trainNumber", "", "There is this train"
                );
            }
        }

        LocalDate date = trainDTO.getDepartureDate().toLocalDate();
        LocalDate date1 = LocalDate.now();
        boolean b = date1.isAfter(date);
        if (b) {
            errors.rejectValue(
                    "departureDate", "", "Wrong departure date"
            );
        }

        List<TrainWayDTO> trainWayDTOList = trainWayService.getAllWays();
        boolean isTrereIsWay = false;
        for (TrainWayDTO wayDTO: trainWayDTOList) {
            if (wayDTO.getId()==trainDTO.getTrainWay().getId()) {
                isTrereIsWay = true;
                break;
            }
        }
        if (!isTrereIsWay) {
            errors.rejectValue(
                    "trainWay", "", "Wrong train way"
            );
        }


    }
}
