package com.trains.util.validator;

import com.trains.model.dto.PassengerDTO;
import com.trains.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Component
public class PassengerDTOValidator implements Validator {

    private PassengerService passengerService;

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return PassengerDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PassengerDTO passengerDTO = (PassengerDTO) o;
        if (!passengerService.getPassengerBylogin(passengerDTO.getLogin()).getName().equals("none")) {
            errors.rejectValue(
                    "login", "", "This login is already in use"
            );
        }
        if(passengerService.getPassengerId(passengerDTO.getName(),passengerDTO.getSurname(),passengerDTO.getBirthday())>0){
            errors.rejectValue(
                    "id", "", "This passenger is already exist"
            );
        }
        LocalDate date = passengerDTO.getBirthday().toLocalDate();
        LocalDate date1 = LocalDate.now();
        boolean b = date.isAfter(date1);
        if (b){
            errors.rejectValue(
                    "birthday", "", "Wrong birthday"
            );
        }
    }
}
