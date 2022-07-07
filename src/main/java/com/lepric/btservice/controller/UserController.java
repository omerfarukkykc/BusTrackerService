package com.lepric.btservice.controller;

import java.util.List;

import com.lepric.btservice.ModelHelper.LocationHelper;
import com.lepric.btservice.model.Rol;
import com.lepric.btservice.model.User;
import com.lepric.btservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    //Create a new user
    @PostMapping()
    public ResponseEntity<User> AddUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.AddUser(user), HttpStatus.OK);
    }

    // Get User by userID
    @GetMapping("{userID}")
    public ResponseEntity<User> GetUser(@PathVariable("userID") long userID) {
        return new ResponseEntity<User>(userService.GetUser(userID), HttpStatus.OK);
    }

    //Get All Users 
    @GetMapping()
    public ResponseEntity<List<User>> getEmployees() {
        return new ResponseEntity<>(userService.GetUsers(), HttpStatus.OK);
    }
    
    //Delete User by userID
    @DeleteMapping("{userID}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("userID") long userID) {
        userService.DeleteUser(userID);
        return new ResponseEntity<String>("User successfully deleted.",HttpStatus.OK);
    }

    //Update User PUT
    @PutMapping("{userID}")
    public ResponseEntity<User> updateEmployee(@PathVariable("userID") long userID, @RequestBody User employee) {
        return new ResponseEntity<User>(userService.UpdateUser(employee, userID), HttpStatus.OK);
    }

    //Update User Location by userID
    @PutMapping("/location/{userID}")
    public ResponseEntity<LocationHelper> UpdateUserLocation(@PathVariable("userID") long userID, @RequestBody LocationHelper location) {
        return new ResponseEntity<LocationHelper>(userService.UpdateUserLocation(location,userID),HttpStatus.OK) ;
    }

    //Get User location by userID
    @GetMapping("/location/{userID}")
    public ResponseEntity<LocationHelper> GetUserLocation(@PathVariable("userID") long userID) {
        return new ResponseEntity<LocationHelper>(userService.GetUserLocation(userID),HttpStatus.OK) ;
    }

    //Get User Rol
    @GetMapping("/rols/{userID}")
    public ResponseEntity<Rol> GetUserRol(@PathVariable("userID") long userID){
        return new ResponseEntity<Rol>(HttpStatus.OK);
    }
}
