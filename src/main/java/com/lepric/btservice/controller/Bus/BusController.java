package com.lepric.btservice.controller.Bus;

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

import com.lepric.btservice.model.Bus;
import com.lepric.btservice.service.BusService;



@RestController
@RequestMapping("/busses")
public class BusController {
    
    @Autowired
    BusService busService;
     //Create a new bus
     @PostMapping()
     public ResponseEntity<Bus> Addbus(@RequestBody Bus bus) {
         return new ResponseEntity<Bus>(busService.AddBus(bus), HttpStatus.OK);
     }
 
     // Get bus by busID
     @GetMapping("{busID}")
     public ResponseEntity<Bus> Getbus(@PathVariable("busID") long busID) {
         return new ResponseEntity<Bus>(busService.GetBus(busID), HttpStatus.OK);
     }
 
     //Get All buss 
     @GetMapping()
     public ResponseEntity<List<Bus>> getEmployees() {
         return new ResponseEntity<List<Bus>>(busService.GetBusses(), HttpStatus.OK);
     }
     
     //Delete bus by busID
     @DeleteMapping("{busID}")
     public ResponseEntity<String> deleteEmployee(@PathVariable("busID") long busID) {
         busService.DeleteBus(busID);
         return new ResponseEntity<String>("bus successfully deleted.",HttpStatus.OK);
     }
     //Update bus PUT
     @PutMapping("{busID}")
     public ResponseEntity<Bus> updateEmployee(@PathVariable("busID") long busID, @RequestBody Bus bus) {
         return new ResponseEntity<Bus>(busService.UpdateBus(bus, busID), HttpStatus.OK);
     }
}
