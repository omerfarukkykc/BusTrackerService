package com.lepric.btservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ApiUserRols")
public class ApiUserRols implements Serializable{
    
    @Id
    @Column(name = "ApiUserRoleID")
    private long ApiUserRoleID;
    
    @Id
    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "userID")
    User user;

    @Id
    @ManyToOne
    @MapsId("apiRole")
    @JoinColumn(name = "apiRolID")
    ApiRol apiRole;

    @Column(name = "apiAccessToken",length = 40,unique = true,nullable = true)
    String apiAccessToken;
}
