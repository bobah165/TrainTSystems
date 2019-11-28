package com.trains.controller;

import com.trains.model.dto.PassengerDTO;
import com.trains.service.PassengerService;
import com.trains.util.validator.PassengerDTOValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class LoginController {
    private PassengerService passengerService;
    private PassengerDTOValidator passengerDTOValidator;
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    public void setPassengerDTOValidator(PassengerDTOValidator passengerDTOValidator) {
        this.passengerDTOValidator = passengerDTOValidator;
    }


    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    @GetMapping(value = "/registration")
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/auth-view/registration");
        logger.info("Read view /auth-view/registration.jsp");
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView registration (@Valid @ModelAttribute("passengerDTO") PassengerDTO passengerDTO, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        passengerDTOValidator.validate(passengerDTO,result);
        passengerDTO.setUser("passenger");
        passengerService.add(passengerDTO);
        logger.info("Add object Passenger from frontend");
        modelAndView.setViewName("redirect:/login");
        return modelAndView;

    }

    @GetMapping(value = "/login")
    public ModelAndView getLoginPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth-view/login");
        logger.info("Read view /auth-view/login.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/pass")
    public ModelAndView getPassengerPage() {
        ModelAndView modelAndView = new ModelAndView();
        String passengerName = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
        modelAndView.addObject("passengerName",passengerName);
        String text = passengerService.getTextForDidYouKnow();
        modelAndView.addObject("text",text);
        modelAndView.setViewName("passenger-view/main-passenger-page");
        logger.info("Read view /main/passenger.jsp");
        return modelAndView;
    }

    @GetMapping(value = "/empl")
    public ModelAndView getEmployeePage() {
        ModelAndView modelAndView = new ModelAndView();
        String employeeName = ((PassengerDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();
        modelAndView.addObject("employeeName",employeeName);
        String text = passengerService.getTextForDidYouKnow();
        modelAndView.addObject("text",text);
        modelAndView.setViewName("passenger-view/main-employee-page");
        logger.info("Read view /main/employee.jsp");
        return modelAndView;
    }

}
