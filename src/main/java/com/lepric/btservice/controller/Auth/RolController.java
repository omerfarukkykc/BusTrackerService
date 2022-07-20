package com.lepric.btservice.controller.Auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lepric.btservice.model.Role;

@Controller
@RequestMapping("/rols")
public class RolController {
    
    //Get User Rol
    @GetMapping("/user/{userID}")
    public ResponseEntity<Role> GetUserRol(@PathVariable("userID") long userID){
        return new ResponseEntity<Role>(HttpStatus.OK);
    }
}
