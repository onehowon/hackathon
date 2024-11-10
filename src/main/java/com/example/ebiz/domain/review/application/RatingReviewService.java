package com.example.ebiz.domain.review.application;

import com.example.ebiz.domain.review.dto.RatingReviewRequest;
import com.example.ebiz.domain.review.entity.RatingReview;
import com.example.ebiz.domain.youtube.application.YoutubeService;
import com.example.ebiz.domain.review.dto.RatingReviewResponse;
import com.example.ebiz.domain.review.dto.ReviewSummaryResponse;
import com.example.ebiz.domain.review.repository.RatingReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingReviewService {

    @Autowired
    private RatingReviewRepository ratingReviewRepository;

    @Autowired
    private YoutubeService youtubeService;

    public void saveRatingReview(String videoUrl, RatingReviewRequest request) {
        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new IllegalArgumentException("평점은 1점과 5점 사이여야 합니다.");
        }

        if (request.getReview().length() > 300) {
            throw new IllegalArgumentException("후기는 300자 이하로 작성해야 합니다.");
        }

        RatingReview ratingReview = RatingReview.builder()
                .videoUrl(videoUrl)
                .rating(request.getRating())
                .review(request.getReview())
                .build();

        ratingReviewRepository.save(ratingReview);
    }


    public RatingReviewResponse getAverageRating(String videoUrl) {
        String title = youtubeService.getVideoTitle(videoUrl);
        Double averageRating = ratingReviewRepository.findAverageRatingByVideoUrl(videoUrl);

        return new RatingReviewResponse(
                title,
                averageRating != null ? averageRating : 0.0
        );
    }


    public ReviewSummaryResponse getSummaryOfPositiveAndNegativeReviews(String videoUrl) {
        List<String> negativeReviews = ratingReviewRepository.findTop2ByVideoUrlAndNegativeRating(videoUrl)
                .stream()
                .map(RatingReview::getReview)
                .collect(Collectors.toList());

        List<String> positiveReviews = ratingReviewRepository.findTop2ByVideoUrlAndPositiveRating(videoUrl)
                .stream()
                .map(RatingReview::getReview)
                .collect(Collectors.toList());

        return new ReviewSummaryResponse(positiveReviews, negativeReviews);
    }

}