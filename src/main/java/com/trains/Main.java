package com.trains;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
//        String url = "jdbc:postgresql://localhost:5432/postgres";
//        String username = "postgres";
//        String password = "bobah";
//        System.out.println("Connecting...");
//
//        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//            System.out.println("Connection successful!");
//        } catch (SQLException e) {
//            System.out.println("Connection failed!");
//            e.printStackTrace();
//        }

        LocalDate date = LocalDate.of(2012,12,12);
        System.out.println(date);
    }
}
