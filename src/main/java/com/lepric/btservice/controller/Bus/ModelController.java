package com.lepric.btservice.controller.Bus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lepric.btservice.model.BusModel;
import com.lepric.btservice.model.BusModelBrands;
import com.lepric.btservice.service.BusService;


@RestController
@RequestMapping("/busses")
public class ModelController {
    @Autowired
    BusService busService;

    
    @GetMapping("/models")
    public ResponseEntity<List<BusModel>> getBusses() {
        return new ResponseEntity<List<BusModel>>(busService.getModels(), HttpStatus.OK);
    }
    @GetMapping("/brands")
    public ResponseEntity<List<BusModelBrands>> getBrands() {
        return new ResponseEntity<List<BusModelBrands>>(busService.getModelBrands(), HttpStatus.OK);
    }



}
