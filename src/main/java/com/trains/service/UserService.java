package com.trains.service;

import com.trains.dao.UserDAO;
import com.trains.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void add(User user) {
        userDAO.add(user);
    }

    public void delete(User user) {
        userDAO.delete(user);

    }

    public void edit(User user) {
        userDAO.edit(user);
    }

    public  User getById(int id) {
        return userDAO.getById(id);
    }

    public User getByName(String name) {return userDAO.getByName(name);}
}
