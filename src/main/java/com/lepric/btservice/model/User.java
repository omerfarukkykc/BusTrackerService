package com.lepric.btservice.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private long userID;

    @Column(name = "firstname", nullable = false,length = 40)
    private String firstname;

    @Column(name = "lastname", nullable = false,length = 20)
    private String lastname;
    
    @Column(name = "email", nullable = false,length = 50,unique = true)
    private String email;

    @Column(name = "password", nullable = false,length = 60)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = false;

    @CreationTimestamp()
    @Column(name = "createdAt", updatable = false, nullable = false)
    private LocalDateTime createdAt; 

    @UpdateTimestamp()
    @Column(name = "updatedAt", updatable = true, nullable = false)
    private LocalDateTime updatedAt;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "locationID",nullable = false,unique = true,updatable = false)
    private Location location;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "alarmID")
    private List<Alarms> alarms;

    @JoinColumn(name = "roleID")
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "apiRole")
    List<ApiUserRols> ApiUserRols;
}
