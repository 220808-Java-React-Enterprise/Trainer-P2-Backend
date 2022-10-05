package com.revature.yolp.repositories;

import com.revature.yolp.models.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, String> {
    @Modifying
    @Query(value = "INSERT INTO reviews (id, rating, comment, username, restaurant_id, user_id) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void saveReview(String id, int rating, String comment, String username, String restaurant_id, String user_id);
}
