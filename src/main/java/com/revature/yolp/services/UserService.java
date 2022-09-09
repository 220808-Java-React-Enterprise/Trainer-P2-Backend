package com.revature.yolp.services;

import com.revature.yolp.dtos.requests.LoginRequest;
import com.revature.yolp.dtos.requests.NewUserRequest;
import com.revature.yolp.dtos.responses.Principal;
import com.revature.yolp.models.User;
import com.revature.yolp.repositories.UserRepository;
import com.revature.yolp.utils.custom_exceptions.AuthenticationException;
import com.revature.yolp.utils.custom_exceptions.InvalidRequestException;
import com.revature.yolp.utils.custom_exceptions.ResourceConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    //@Autowired
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User register(NewUserRequest request) {
        User user = null;

        if (isValidUsername(request.getUsername())) {
            if (!isDuplicateUsername(request.getUsername())) {
                if (isValidPassword(request.getPassword1())) {
                    if (isSamePassword(request.getPassword1(), request.getPassword2())) {
                        user = new User(UUID.randomUUID().toString(), request.getUsername(), request.getPassword1());
                        userRepo.save(user);
                    }
                }
            }
        }

        return user;
    }

    public Principal login(LoginRequest request) {
        User user = userRepo.findUserByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user == null) throw new AuthenticationException("\nIncorrect username or password :(");
        return new Principal(user.getId(), user.getUsername(), user.getRole());
    }

    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    public User getUserByUsername(String username) {
        if (userRepo.findUserByUsername(username) == null) throw new InvalidRequestException("\nInvalid request! There is no user by that username");
        return userRepo.findUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    public boolean isValidUsername(String username) {
        if (!username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) throw new InvalidRequestException("\nInvalid username! username is 8-20 characters long. no _ or . at the beginning. no __ or _. or ._ or .. inside");
        return true;
    }

    public boolean isValidPassword(String password) {
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) throw new InvalidRequestException("\nInvalid password! Minimum eight characters, at least one letter and one number");
        return true;
    }

    public boolean isDuplicateUsername(String username) {
        if (userRepo.findUsernameByUsername(username) != null) throw new ResourceConflictException("\nSorry, " + username + " already been taken :(");
        return false;
    }

    public boolean isSamePassword(String password, String password2) {
        if (!password.equals(password2)) throw new InvalidRequestException("\nPassword do not match :(");
        return true;
    }
}
