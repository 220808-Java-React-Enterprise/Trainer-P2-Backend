package com.revature.yolp.services;

import com.revature.yolp.models.Restaurant;
import com.revature.yolp.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepository restoRepository;

    public RestaurantService(RestaurantRepository restoRepository) {
        this.restoRepository = restoRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        return (List<Restaurant>) restoRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(String id) {
        return restoRepository.findById(id);
    }
}
