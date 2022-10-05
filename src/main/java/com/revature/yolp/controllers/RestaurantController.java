package com.revature.yolp.controllers;

import com.revature.yolp.dtos.responses.Principal;
import com.revature.yolp.models.Restaurant;
import com.revature.yolp.services.RestaurantService;
import com.revature.yolp.services.TokenService;
import com.revature.yolp.utils.custom_exceptions.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {
    private final RestaurantService restoService;
    private final TokenService tokenService;

    public RestaurantController(RestaurantService restoService, TokenService tokenService) {
        this.restoService = restoService;
        this.tokenService = tokenService;
    }

    @CrossOrigin
    @GetMapping
    public List<Restaurant> getAllRestaurants(@RequestHeader(value = "authorization") String token) {
        if (token == null || token.isEmpty())
            throw new AuthenticationException("Sorry, you are not logged in correctly");
        return restoService.getAllRestaurants();
    }

    @CrossOrigin
    @GetMapping(value = "id")
    public Optional<Restaurant> getRestaurantById(@RequestHeader(value = "authorization") String token, @RequestParam(value = "id") String id) {
        if (token == null || token.isEmpty())
            throw new AuthenticationException("Sorry, you are not logged in correctly");

        Principal principal = tokenService.extractRequesterDetails(token);

        if (principal == null)
            throw new AuthenticationException("Sorry, you are not logged in correctly");

        return restoService.getRestaurantById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationException handledAuthenticationException(AuthenticationException e) {
        return e;
    }
}
