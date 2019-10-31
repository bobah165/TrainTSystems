package com.trains.service;

import com.trains.dao.UserRoleDAO;
import com.trains.model.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserRoleService {
    private UserRoleDAO userRoleDAO;

    @Autowired
    public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    public void add(UserRole userRole) {
        userRoleDAO.add(userRole);
    }

    public void delete(UserRole userRole) {
        userRoleDAO.delete(userRole);
    }

    public void edit(UserRole userRole) {
        userRoleDAO.edit(userRole);
    }

    public UserRole getById(int id) {
        return userRoleDAO.getById(id);
    }
}
