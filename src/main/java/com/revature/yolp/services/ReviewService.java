package com.revature.yolp.services;

import com.revature.yolp.dtos.requests.NewReviewRequest;
import com.revature.yolp.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Transactional
    public void saveReview(NewReviewRequest req) {
        reviewRepository.saveReview(UUID.randomUUID().toString(), req.getRating(), req.getComment(), req.getUsername(), req.getRestaurant_id(), req.getUser_id());
    }
}
