package com.trains.controller;

import com.trains.service.FreeSeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FreeSeatsController {
    private FreeSeatsService freeSeatsService;

    @Autowired
    public void setFreeSeatsService(FreeSeatsService freeSeatsService) {
        this.freeSeatsService = freeSeatsService;
    }



}
