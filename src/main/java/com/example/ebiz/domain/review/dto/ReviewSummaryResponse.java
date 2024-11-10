package com.example.ebiz.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReviewSummaryResponse {
    private List<String> positiveReviews;
    private List<String> negativeReviews;
}