package com.trains.model.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @Column(name = "id_user_role")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId(mutable = true)
    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "id_role")
    private Role role;


    @NaturalId(mutable = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //    public int getIdRole() {
//        return idRole;
//    }
//
//    public void setIdRole(int idRole) {
//        this.idRole = idRole;
//    }
//
//    public int getIduser() {
//        return iduser;
//    }
//
//    public void setIduser(int iduser) {
//        this.iduser = iduser;
//    }
}
