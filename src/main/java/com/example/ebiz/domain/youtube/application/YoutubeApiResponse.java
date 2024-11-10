package com.example.ebiz.domain.youtube.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class YoutubeApiResponse {

    @JsonProperty("items")
    private List<YouTubeApiItem> items;

    @Getter
    public static class YouTubeApiItem {
        private Snippet snippet;
        private Statistics statistics;
    }

    @Getter
    public static class Snippet {
        private String title;
        private String description;
        private String channelTitle;
        private String publishedAt;
    }

    @Getter
    public static class Statistics {
        private String viewCount;
        private String likeCount;
        private String commentCount;
    }
}
