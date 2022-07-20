package com.lepric.btservice.service;

import com.lepric.btservice.model.Rol;
import com.lepric.btservice.model.User;
import com.lepric.btservice.payload.response.UpdatePasswordModelHelper;

import java.util.List;

public interface UserService {
    User AddUser(User User);
    User UpdateUser(User User,long userID);
    User GetUser(long userID);
    List<User> GetUsers();
    boolean DeleteUser(long userID);
    boolean ChangePassword(long userID,UpdatePasswordModelHelper upmh);


    List<Rol> GetUserRols(long userID);

}
