package com.lepric.btservice.controller.Bus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lepric.btservice.model.BusBrand;
import com.lepric.btservice.model.BusBrandModel;
import com.lepric.btservice.service.BusService;


@RestController
@RequestMapping("/busses")
public class ModelController {
    @Autowired
    BusService busService;

    
    @GetMapping("/brands")
    public ResponseEntity<List<BusBrand>> getBrands() {
        return new ResponseEntity<List<BusBrand>>(busService.getBrands(), HttpStatus.OK);
    }
    @GetMapping("/brands/{brandID}")
    public ResponseEntity<List<BusBrandModel>> getBrandsModels(@PathVariable long brandID) {
        return new ResponseEntity<List<BusBrandModel>>(busService.getBrandModels(brandID), HttpStatus.OK);
    }



}
