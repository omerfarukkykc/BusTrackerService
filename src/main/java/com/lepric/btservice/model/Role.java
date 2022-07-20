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


import lombok.Data;

@Data
@Entity
@Table(name = "Rols")
public class Role {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleID")
    private long roleID;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<User> users;

    @Column(name = "rolName", nullable = false,length = 40)
    private String roleName;

    @Column(name = "editRotations", nullable = false)
    private boolean editRotations;
    
    @Column(name = "editStations", nullable = false)
    private boolean editStations;

    @Column(name = "editBuses", nullable = false)
    private boolean editBuses;

    @Column(name = "editUsers", nullable = false)
    private boolean editUsers;

    @Column(name = "loadBalance", nullable = false)
    private boolean loadBalance;

    @Column(name = "loadBalanceHimSelf", nullable = false)
    private boolean loadBalanceHimSelf;

}
