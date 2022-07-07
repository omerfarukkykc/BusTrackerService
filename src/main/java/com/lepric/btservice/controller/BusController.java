package com.lepric.btservice.controller;

import java.util.List;

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

import com.lepric.btservice.model.Busses;
import com.lepric.btservice.service.BusService;



@RestController
@RequestMapping("/bus")
public class BusController {
    
    @Autowired
    BusService busService;
     //Create a new bus
     @PostMapping()
     public ResponseEntity<Busses> Addbus(@RequestBody bus bus) {
         return new ResponseEntity<Busses>(busService.Addbus(bus), HttpStatus.OK);
     }
 
     // Get bus by busID
     @GetMapping("{busID}")
     public ResponseEntity<Busses> Getbus(@PathVariable("busID") long busID) {
         return new ResponseEntity<Busses>(busService.Getbus(busID), HttpStatus.OK);
     }
 
     //Get All buss 
     @GetMapping()
     public ResponseEntity<List<Busses>> getEmployees() {
         return new ResponseEntity<List<Busses>>(busService.Getbusses(), HttpStatus.OK);
     }
     
     //Delete bus by busID
     @DeleteMapping("{busID}")
     public ResponseEntity<String> deleteEmployee(@PathVariable("busID") long busID) {
         busService.Deletebus(busID);
         return new ResponseEntity<String>("bus successfully deleted.",HttpStatus.OK);
     }
 
     //Update bus PUT
     @PutMapping("{busID}")
     public ResponseEntity<Busses> updateEmployee(@PathVariable("busID") long busID, @RequestBody Busses bus) {
         return new ResponseEntity<Busses>(busService.UpdateBus(bus, busID), HttpStatus.OK);
     }
}
