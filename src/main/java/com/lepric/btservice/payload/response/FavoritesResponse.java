package com.lepric.btservice.payload.response;

import java.util.List;

import com.lepric.btservice.model.Favorite;

import lombok.Data;

@Data
public class FavoritesResponse {
    private List<Favorite> stations;
    private List<Favorite> routes;
}
