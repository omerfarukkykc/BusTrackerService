package com.lepric.btservice.controller.Auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lepric.btservice.model.Rol;

@Controller
@RequestMapping("/rols")
public class RolController {
    /*
    @GetMapping("/user/{userID}")
    public ResponseEntity<Rols> GetUserRol(@PathVariable("userID") long userID){
        return new ResponseEntity<Rols>(HttpStatus.OK);
    }
    */
    //Get User Rol
    @GetMapping("/user/{userID}")
    public ResponseEntity<Rol> GetUserRol(@PathVariable("userID") long userID){
        return new ResponseEntity<Rol>(HttpStatus.OK);
    }
}
