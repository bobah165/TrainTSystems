package com.trains.service;

import com.trains.dao.RoleDAO;
import com.trains.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {
    private RoleDAO roleDAO;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public void add(Role role) {
        roleDAO.add(role);
    }

    public void delete(Role role) {
      roleDAO.delete(role);
    }

    public void edit(Role role) {
        roleDAO.edit(role);
    }

    public Role getById(int id) {
        return roleDAO.getById(id);
    }
}
