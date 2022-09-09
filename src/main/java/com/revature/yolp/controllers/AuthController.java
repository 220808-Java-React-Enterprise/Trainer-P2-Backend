package com.revature.yolp.controllers;

import com.revature.yolp.dtos.requests.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping(consumes = "application/json")
    public void login(@RequestBody LoginRequest request, HttpServletResponse resp) {
        resp.setHeader("Authorization", token); // token (need implementation but you get the gist)
    }
}
