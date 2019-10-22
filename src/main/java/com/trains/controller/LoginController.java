package com.trains.controller;

import com.trains.model.dto.PassengerDTO;
import com.trains.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {
    private PassengerService passengerService;

    @Autowired
    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping(value = "/")
    public ModelAndView getStartPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/auth-view/index");
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/auth-view/login");
        return modelAndView;
    }

    @PostMapping(value = "/login")
    public ModelAndView autentification (@RequestParam("login") String login,
                                         @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        List<PassengerDTO> passengers = passengerService.allPassengers();
        for (PassengerDTO passengerDTO: passengers) {
            if(login.equals(passengerDTO.getLogin())&&password.equals(passengerDTO.getPassword())) {
                modelAndView.setViewName("redirect:/train/");
                break;
            }
            else modelAndView.setViewName("redirect:/passenger/add");
        }
        return modelAndView;
    }
}
