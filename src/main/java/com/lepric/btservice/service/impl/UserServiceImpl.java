package com.lepric.btservice.service.impl;

import java.util.ArrayList;
import java.util.List;


import static org.geolatte.geom.builder.DSL.*;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lepric.btservice.exception.ResourceNotFoundException;
import com.lepric.btservice.model.Role;
import com.lepric.btservice.model.User;
import com.lepric.btservice.payload.response.UpdatePasswordModelHelper;
import com.lepric.btservice.model.Location;
import com.lepric.btservice.model.Privilege;
import com.lepric.btservice.repository.UserRepository;
import com.lepric.btservice.service.UserService;


@Service
public class UserServiceImpl implements UserService ,UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    


    //Add New User
    @Override
    public User AddUser(User user) {
        user.setLocation(new Location());
        user.getLocation().setLocation(new Point<G2D>(g(0,0),WGS84));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
            () -> new ResourceNotFoundException("User", "ID", id)
        );
        userRepository.deleteById(id);
        return true;
    }


    

    //Get User Rols
    @Override
    public List<Role> GetUserRols(long userID) {
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

    


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(
            () -> new ResourceNotFoundException("User", "email", username)
        );
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }


    @Override
    public User GetUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(
            () -> new ResourceNotFoundException("User", "email", email)
        );
        
    }


    


    
    
}
