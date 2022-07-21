/*
package com.lepric.btservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ApiRol")
public class ApiRole {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apiRoleID")
    private long ApiRoleID;

    @OneToMany(mappedBy = "user")
    private List<ApiUserRols> ApiUserRols;

    
    
    @Column(name = "rolName", nullable = false,length = 40)
    private String rolName;

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
 */