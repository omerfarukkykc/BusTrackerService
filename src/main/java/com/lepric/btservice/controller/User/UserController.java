package com.lepric.btservice.controller.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lepric.btservice.model.User;
import com.lepric.btservice.payload.response.AmountResponse;
import com.lepric.btservice.payload.response.UpdatePasswordModelHelper;
import com.lepric.btservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PostMapping("/register")
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getUsers(HttpServletRequest request) {        
        return new ResponseEntity<>(userService.GetUsers(), HttpStatus.OK);
    }
    
    //Delete User by userID
    @DeleteMapping("{userID}")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasAuthority('EDIT_ROTATIONS')")
    public ResponseEntity<String> deleteUser(@PathVariable("userID") long userID) {
        userService.DeleteUser(userID);
        return new ResponseEntity<String>("User successfully deleted.",HttpStatus.OK);
    }
    //Update User PUT
    @PutMapping("{userID}")
    @PreAuthorize("hasAuthority('EDIT_USERS')")
    public ResponseEntity<User> updateUser(@PathVariable("userID") long userID, @RequestBody User user) {
        return new ResponseEntity<User>(userService.UpdateUser(user, userID), HttpStatus.OK);
    }
    @PutMapping("/updatePassword/{userID}")
    public ResponseEntity<Boolean> updateUserPassword(@PathVariable("userID") long userID, @RequestBody UpdatePasswordModelHelper updatePassword) {
        return new ResponseEntity<Boolean>(userService.ChangePassword(userID,updatePassword), HttpStatus.OK);
    }

   
    @GetMapping("{cardOrUserID}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable("cardOrUserID") String cardOrUserID) {
        try {
            long userID = Long.parseLong(cardOrUserID);
            return new ResponseEntity<Double>(userService.getBalance(userID), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Double>(userService.getBalance(cardOrUserID), HttpStatus.OK);
        }
    }
    @GetMapping("{cardOrUserID}/balance/{amount}")
    public ResponseEntity<Double> loadBalance(@PathVariable("cardOrUserID") String cardOrUserID,@PathVariable("amount") double amount) {
        try {
            long userID = Long.parseLong(cardOrUserID);
            return new ResponseEntity<Double>(userService.loadBalance(userID,amount), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Double>(userService.loadBalance(cardOrUserID,amount), HttpStatus.OK);
        }
    }
    @GetMapping("/payment/{cardID}/bus/{busID}")
    public ResponseEntity<AmountResponse> getPayment(@PathVariable("cardID") String cardID,@PathVariable("busID") long busID) {
        return new ResponseEntity<AmountResponse>(userService.getPayment(cardID, busID), HttpStatus.OK);
    }

    @GetMapping("/payment/{cardID}/station/{stationID}")
    public ResponseEntity<Double> getRefund(@PathVariable("cardID") String cardID,@PathVariable("stationID") long stationID) {
        return new ResponseEntity<Double>(userService.getRefund(cardID,stationID), HttpStatus.OK);
    }
}
