package com.trains;


import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

        Timestamp time = new Timestamp(System.currentTimeMillis());
        System.out.println(time);
    }
}
