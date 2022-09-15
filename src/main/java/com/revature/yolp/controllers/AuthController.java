package com.revature.yolp.controllers;

import com.revature.yolp.dtos.requests.LoginRequest;
import com.revature.yolp.dtos.responses.Principal;
import com.revature.yolp.services.UserService;
import com.revature.yolp.utils.custom_exceptions.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin // CORS
    @PostMapping(value = "/auth", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Principal login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody AuthenticationException handleAuthenticationException(AuthenticationException e) {
        return e;
    }
}
