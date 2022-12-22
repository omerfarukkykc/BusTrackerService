package com.lepric.btservice.payload.response;

import com.lepric.btservice.model.Dealer;
import com.lepric.btservice.model.Location;

import lombok.Data;

@Data
public class DealerResponse {
    private long dealersID;
    private String dealerName;
    public DealerResponse(Dealer dealer) {
        this.dealersID = dealer.getDealersID();
        this.dealerName = dealer.getDealerName();
        this.location = new LocationResponse(dealer.getLocation().getLocation());
    }
    private LocationResponse location;
}
