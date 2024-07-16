package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.dto.ReviewDTO;
import com.example.model.Review;
import com.example.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/{email}")
    public ReviewDTO createReview(@PathVariable String email, @RequestBody Review review) {
        return reviewService.createReview(email, review);
    }

    @GetMapping("/product/{productId}")
    public List<ReviewDTO> getReviewsByProductId(@PathVariable Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }
}
