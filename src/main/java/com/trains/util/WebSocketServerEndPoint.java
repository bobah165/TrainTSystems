package com.trains.util;


import com.google.gson.Gson;
import com.trains.model.dto.TrainFromStationDTO;
import com.trains.service.TrainFromStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.sql.Date;
import java.sql.Time;

@Component
@ServerEndpoint(value = "/server")
public class WebSocketServerEndPoint {
    private TrainFromStationService trainFromStationService;

    @Autowired
    public void setTrainFromStationService(TrainFromStationService trainFromStationService) {
        this.trainFromStationService = trainFromStationService;
    }

    @OnOpen
    public void onOpen(Session session) {
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println(session.getId() +"  "+ closeReason.getReasonPhrase());
    }

    @OnMessage
    public String onMassege(String message, Session session) {
        TrainFromStationDTO trainFromStationDTO = new TrainFromStationDTO();
        trainFromStationDTO.setId(5);
        trainFromStationDTO.setIdTrain(5);
        trainFromStationDTO.setArrivalTime(Time.valueOf("14:30:00"));
        trainFromStationDTO.setDepartureTime(Time.valueOf("20:30:00"));
        trainFromStationDTO.setNameStation("piter");
        trainFromStationDTO.setDate(Date.valueOf("2019-12-12"));

        Gson gson = new Gson();
        String json = gson.toJson(trainFromStationDTO);
        message = json;

        return message;
    }

}
