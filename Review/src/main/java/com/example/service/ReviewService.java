package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ReviewDTO;
import com.example.model.Review;
import com.example.model.User;
import com.example.repository.ReviewRepository;
import com.example.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public ReviewDTO createReview(String email, Review review) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            review.setUser(user);
            Review savedReview = reviewRepository.save(review);
            return convertToDTO(savedReview);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<ReviewDTO> getReviewsByProductId(Long productId) {
        return reviewRepository.findAll().stream()
                .filter(review -> review.getProduct().getId().equals(productId))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ReviewDTO convertToDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setUserName(review.getUser().getFirstName() + " " + review.getUser().getLastName());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }
}
