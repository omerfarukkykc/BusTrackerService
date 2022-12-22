package com.lepric.btservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lepric.btservice.model.Dealer;
import com.lepric.btservice.repository.DealerRepository;
import com.lepric.btservice.service.DealerService;

@Service
public class DealerServiceImpl implements DealerService{
    @Autowired
    DealerRepository dealerRepository;
    @Override
    public List<Dealer> GetDealers() {
        
        return dealerRepository.findAll();
    }
    
    
}
