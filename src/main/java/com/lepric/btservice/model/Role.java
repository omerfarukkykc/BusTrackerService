package com.lepric.btservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Role")
public class Role {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleID")
    private long roleID;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<User> users;

    @Column(name = "rolName", nullable = false,length = 40)
    private String roleName;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Privilege> privileges;

    public Role() {
    }

    public Role(String roleName, List<Privilege> privileges) {
        this.roleName = roleName;
        this.privileges = privileges;
    }

    /*
    @Column(name = "editRotations", nullable = false)
    private boolean editRotations = false;
    
    @Column(name = "editStations", nullable = false)
    private boolean editStations = false;

    @Column(name = "editBuses", nullable = false)
    private boolean editBuses = false;

    @Column(name = "editUsers", nullable = false)
    private boolean editUsers = false;

    @Column(name = "loadBalance", nullable = false)
    private boolean loadBalance = false;

    @Column(name = "loadBalanceHimSelf", nullable = false)
    private boolean loadBalanceHimSelf = false;
    */

}
