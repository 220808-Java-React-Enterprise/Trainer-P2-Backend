package com.revature.yolp.controllers;

import com.revature.yolp.dtos.requests.LoginRequest;
import com.revature.yolp.dtos.responses.Principal;
import com.revature.yolp.services.TokenService;
import com.revature.yolp.services.UserService;
import com.revature.yolp.utils.custom_exceptions.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final TokenService tokenService;

    public AuthController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @CrossOrigin(exposedHeaders = "authorization")
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Principal login(@RequestBody LoginRequest request, HttpServletResponse resp) {
        Principal principal = userService.login(request);
        String token = tokenService.generateToken(principal);
        resp.setHeader("authorization", token);
        return principal;
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationException handleAuthenticationException(AuthenticationException e) {
        return e;
    }
}
