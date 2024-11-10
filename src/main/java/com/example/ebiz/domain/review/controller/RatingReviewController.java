package com.example.ebiz.domain.review.controller;

import com.example.ebiz.domain.review.application.RatingReviewService;
import com.example.ebiz.domain.review.dto.RatingReviewRequest;
import com.example.ebiz.domain.review.dto.RatingReviewResponse;
import com.example.ebiz.domain.review.dto.ReviewSummaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video")
public class RatingReviewController {

    @Autowired
    private RatingReviewService ratingReviewService;

    @PostMapping("/ratings_reviews")
    public ResponseEntity<String> submitRatingReview(@RequestParam String url, @RequestBody RatingReviewRequest request) {
        ratingReviewService.saveRatingReview(url, request);
        return ResponseEntity.ok("리뷰 저장에 성공했습니다.");
    }

    @GetMapping("/ratings/mean")
    public RatingReviewResponse getAverageRating(@RequestParam String url) {
        return ratingReviewService.getAverageRating(url);
    }

    @GetMapping("/reviews/summary")
    public ReviewSummaryResponse getReviewSummary(@RequestParam String url) {
        return ratingReviewService.getSummaryOfPositiveAndNegativeReviews(url);
    }

}