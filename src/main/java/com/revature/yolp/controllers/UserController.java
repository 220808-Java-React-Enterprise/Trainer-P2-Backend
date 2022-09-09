package com.revature.yolp.controllers;

import com.revature.yolp.dtos.requests.NewUserRequest;
import com.revature.yolp.models.User;
import com.revature.yolp.services.UserService;
import com.revature.yolp.utils.custom_exceptions.InvalidRequestException;
import com.revature.yolp.utils.custom_exceptions.ResourceConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
     * @PostMapping = making a post request
     * @ResponseBody annotation tells a controller that the object returned a JSON.
     * @RequestBody annotation maps the HttpRequest body to a DTO.
     */
    @ExceptionHandler(value = {ResourceConflictException.class, InvalidRequestException.class})
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/signup", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String signup(@RequestBody NewUserRequest request) {
        return userService.register(request).getId();
    }

    @GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getAll() {

    }
}
