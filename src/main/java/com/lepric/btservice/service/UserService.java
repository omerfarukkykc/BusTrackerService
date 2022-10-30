package com.lepric.btservice.service;

import com.lepric.btservice.model.Role;
import com.lepric.btservice.model.User;
import com.lepric.btservice.payload.response.AmountResponse;
import com.lepric.btservice.payload.response.UpdatePasswordModelHelper;

import java.util.List;

public interface UserService {
    User AddUser(User User);
    User UpdateUser(User User,long userID);
    User GetUser(long userID);
    List<User> GetUsers();
    boolean DeleteUser(long userID);
    boolean ChangePassword(long userID,UpdatePasswordModelHelper upmh);
    User GetUser(String email);
    
    
    AmountResponse getPayment(String cardID,long busID);
    double loadBalance(String cardID,double amount);
    double loadBalance(long userID,double amount);
    double getBalance(String cardID);
    double getBalance(long userID);
    Double getRefund(String cardID,long stationID);
    Double getRefund(long userID,long stationID);


    List<Role> GetUserRols(long userID);

}
