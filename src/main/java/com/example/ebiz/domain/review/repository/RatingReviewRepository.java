package com.example.ebiz.domain.review.repository;

import com.example.ebiz.domain.review.entity.RatingReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingReviewRepository extends JpaRepository<RatingReview, Long> {

    @Query("SELECT AVG(r.rating) FROM RatingReview r WHERE r.videoUrl = :videoUrl")
    Double findAverageRatingByVideoUrl(@Param("videoUrl") String videoUrl);

    // 부정적 평가(평점 2점 이하) 중 최근 2개
    @Query("SELECT r FROM RatingReview r WHERE r.videoUrl = :videoUrl AND r.rating <= 2 ORDER BY r.id DESC")
    List<RatingReview> findTop2ByVideoUrlAndNegativeRating(@Param("videoUrl") String videoUrl);

    // 긍정적 평가(평점 4점 이상) 중 최근 2개
    @Query("SELECT r FROM RatingReview r WHERE r.videoUrl = :videoUrl AND r.rating >= 4 ORDER BY r.id DESC")
    List<RatingReview> findTop2ByVideoUrlAndPositiveRating(@Param("videoUrl") String videoUrl);
}