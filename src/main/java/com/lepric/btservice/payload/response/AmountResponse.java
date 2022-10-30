package com.lepric.btservice.payload.response;

import lombok.Data;

@Data
public class AmountResponse {
    
    private double newAmount;
    private double amount;
    private boolean isOk;
    public AmountResponse() {
    }
    public AmountResponse(boolean isOk,double newAmount, double amount) {
        this.newAmount = newAmount;
        this.amount = amount;
        this.isOk = isOk;
    }
}
