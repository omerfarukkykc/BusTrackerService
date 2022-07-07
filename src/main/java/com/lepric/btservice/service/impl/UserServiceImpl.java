package com.lepric.btservice.service.impl;

import java.util.List;


import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.stereotype.Service;

import com.lepric.btservice.ModelHelper.LocationModelHelper;
import com.lepric.btservice.ModelHelper.UpdatePasswordModelHelper;
import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.Rol;
import com.lepric.btservice.model.User;
import com.lepric.btservice.model.Location;
import com.lepric.btservice.repository.UserRepository;
import com.lepric.btservice.service.UserService;


@Service
public class UserServiceImpl implements UserService{
    
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }


    //Add New User
    @Override
    public User AddUser(User user) {
        user.setLocation(new Location());
        user.getLocation().setLocation(new Point<G2D>(g(0,0),WGS84));
        return userRepository.save(user);
    }

    @Override
    public User UpdateUser(User user, long id) {
        User dbuser =  userRepository.findById(id).orElseThrow( 
            () -> new ResourceNotFoundException("User", "ID", id));
        if(user.getFirstname() != null)    
        dbuser.setFirstname(user.getFirstname());

        if(user.getLastname() != null)    
        dbuser.setLastname(user.getLastname());

        if(user.getEmail() != null)    
        dbuser.setEmail(user.getEmail());

        if(user.getPassword() != null)    
        dbuser.setPassword(user.getPassword());
        
        return userRepository.save(dbuser);

    }

    @Override
    public User GetUser(long id) {
        User user =  userRepository.findById(id).orElseThrow( 
            () -> new ResourceNotFoundException("User", "ID", id));
        return user;
    }

    @Override
    public List<User> GetUsers() {
       
        return userRepository.findAll();
    }

    @Override
    public boolean DeleteUser(long id) {
        userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Employee", "ID", id)
        );
        userRepository.deleteById(id);
        return true;
    }


    //Update user location
    @Override
    public LocationModelHelper UpdateUserLocation(LocationModelHelper location, Long userID) {
        User dbUser =  userRepository.findById(userID).orElseThrow(
            () -> new ResourceNotFoundException("User", "ID", userID)
        );
        dbUser.getLocation().setLocation(new Point<G2D>(g(location.getLatitude(),location.getLongitude()),WGS84));
        userRepository.save(dbUser);
        return new LocationModelHelper(dbUser.getLocation().getLocation(),dbUser.getLocation().getUpdatedAt());
    }

    //Get User Location
    @Override
    public LocationModelHelper GetUserLocation(Long userID) {
        User dbUser =  userRepository.findById(userID).orElseThrow(
            () -> new ResourceNotFoundException("User", "ID", userID)
        );
        return new LocationModelHelper(dbUser.getLocation().getLocation(),dbUser.getLocation().getUpdatedAt());
    }

    //Get User Rols
    @Override
    public List<Rol> GetUserRols(long userID) {
        User dbUser =  userRepository.findById(userID).orElseThrow(
            () -> new ResourceNotFoundException("User", "ID", userID)
        );
        return dbUser.getRols();
    }


    @Override
    public boolean ChangePassword(long userID,UpdatePasswordModelHelper upmh) {
        User dbUser =  userRepository.findById(userID).orElseThrow(
            () -> new ResourceNotFoundException("User", "ID", userID)
        );
        if(!upmh.getOldPassword().equals(dbUser.getPassword())){
            return false;
        }
        if(!upmh.getNewPassword().equals(upmh.getNewPasswordAgain())){
            return false;
        }
        dbUser.setPassword(upmh.getNewPassword());
        userRepository.save(dbUser);
        return true;
    }


    
    
}
