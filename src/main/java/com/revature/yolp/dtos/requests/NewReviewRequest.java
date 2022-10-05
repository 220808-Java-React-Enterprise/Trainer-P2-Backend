package com.revature.yolp.dtos.requests;

public class NewReviewRequest {
    private int rating;
    private String comment;
    private String username;
    private String restaurant_id;
    private String user_id;

    public NewReviewRequest() {
    }

    public NewReviewRequest(int rating, String comment, String username, String restaurant_id, String user_id) {
        this.rating = rating;
        this.comment = comment;
        this.username = username;
        this.restaurant_id = restaurant_id;
        this.user_id = user_id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "NewReviewRequest{" +
                "rating=" + rating +
                ", comment='" + comment + '\'' +
                ", username='" + username + '\'' +
                ", restaurant_id='" + restaurant_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
