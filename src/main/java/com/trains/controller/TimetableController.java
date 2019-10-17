package com.trains.controller;

import com.trains.model.Timetable;
import com.trains.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/timetable")
public class TimetableController {
    TimetableService timetableService;

    @Autowired
    public void setTimetableService(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping(value = "/")
    public ModelAndView allPassengers() {
        List<Timetable> timetables = timetableService.allTimetable();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("timetable-view/timetables");
        modelAndView.addObject("timetablesList",timetables);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView update (@PathVariable("id") int id) {
        Timetable timetable = timetableService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("timetable-view/edit-timetable");
        modelAndView.addObject("timetable",timetable);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public ModelAndView editPassenger(@ModelAttribute("timetable") Timetable timetable) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/timetable/");
        timetableService.edit(timetable);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getPassPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("timetable-view/add-timetable");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView create(@ModelAttribute("timetable") Timetable timetable) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/timetable/");
        timetableService.add(timetable);
        return modelAndView;
    }

    @GetMapping (value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/timetable/");
        Timetable timetable = timetableService.getById(id);
        timetableService.delete(timetable);
        return modelAndView;
    }

}
