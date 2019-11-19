package com.trains.model.dto;


import com.trains.model.entity.Ticket;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

public class PassengerDTO {

    private int id;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid name. Name have to consist only from letters")
    private String name;
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid surname. Name have to consist only from letters")
    private String surname;
    private Date birthday;
    @Size(min = 5,max = 32,message = "Invalid login. Login should have min 5 and max 32 symbols")
    private String login;
    @Size(min = 5,max = 32, message = "Invalid login. Login should have min 5 and max 32 symbols")
    private String password;
    @Email(message = "Invalid email")
    private String email;
    private List<Ticket> tickets;
    private String user;


    public String getUser() { return user; }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Passenger {" +
                "id=" + id +
                ", name is " + name +
                ", surname is "+surname+
                ", birthday is " + birthday +
                " }";
    }
}
