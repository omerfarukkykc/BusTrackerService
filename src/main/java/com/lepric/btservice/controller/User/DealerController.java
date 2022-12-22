package com.lepric.btservice.controller.User;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lepric.btservice.payload.response.DealerResponse;
import com.lepric.btservice.service.DealerService;

@RestController
@RequestMapping("/dealer")
public class DealerController {

    @Autowired
    DealerService dealerService;

    @GetMapping("")
    public ResponseEntity<List<DealerResponse>> getDealers() {
        List<DealerResponse> response = dealerService.GetDealers().stream().map(dealer -> new DealerResponse(dealer))
                .collect(Collectors.toList());
        return new ResponseEntity<List<DealerResponse>>(response, HttpStatus.OK);
    }
}
