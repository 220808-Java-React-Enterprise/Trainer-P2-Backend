package com.revature.yolp.controllers;

import com.revature.yolp.dtos.requests.NewReviewRequest;
import com.revature.yolp.dtos.responses.Principal;
import com.revature.yolp.services.ReviewService;
import com.revature.yolp.services.TokenService;
import com.revature.yolp.utils.custom_exceptions.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final TokenService tokenService;

    public ReviewController(ReviewService reviewService, TokenService tokenService) {
        this.reviewService = reviewService;
        this.tokenService = tokenService;
    }

    @CrossOrigin
    @PostMapping(value = "/add", consumes = "application/json")
    public void leaveReview(@RequestHeader(value = "authorization") String token, @RequestBody NewReviewRequest request) {
        if (token == null || token.isEmpty())
            throw new AuthenticationException("Sorry, you do not have authorization to do this!");

        Principal principal = tokenService.extractRequesterDetails(token);

        if (principal.getRole().equals("DEFAULT") || principal.getRole().equals("ADMIN")) {
            reviewService.saveReview(request);
        } else throw new AuthenticationException("Sorry, you do not have authorization to do this!");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AuthenticationException handledAuthenticationException(AuthenticationException e) {
        return e;
    }
}
