package com.trains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/main")
public class MainPageController {

    @GetMapping(value = "/pass")
    public ModelAndView getPassengerPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main/passenger");
        return modelAndView;
    }

    @GetMapping(value = "/empl")
    public ModelAndView getEmployeePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main/employee");
        return modelAndView;
    }
}
