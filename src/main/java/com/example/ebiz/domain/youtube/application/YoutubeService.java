package com.example.ebiz.domain.youtube.application;

import com.example.ebiz.domain.youtube.dto.TedInfoDTO;
import com.example.ebiz.domain.youtube.entity.TedInfo;
import com.example.ebiz.domain.youtube.repository.YoutubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class YoutubeService {

    @Autowired
    private YoutubeRepository youtubeRepository;

    @Value("${youtube.api.key}")
    private String apiKey;

    private static final String YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/videos";

    public TedInfoDTO getVideoInfo(String videoUrl) {
        String videoId = extractVideoId(videoUrl);

        try {
            // API URL 설정
            String url = UriComponentsBuilder.fromHttpUrl(YOUTUBE_API_URL)
                    .queryParam("part", "snippet,statistics")
                    .queryParam("id", videoId)
                    .queryParam("key", apiKey)
                    .toUriString();

            // API 호출
            RestTemplate restTemplate = new RestTemplate();
            YoutubeApiResponse response = restTemplate.getForObject(url, YoutubeApiResponse.class);

            if (response == null || response.getItems() == null || response.getItems().isEmpty()) {
                throw new IllegalStateException("YouTube API로부터 유효한 응답을 받지 못했습니다.");
            }

            YoutubeApiResponse.YouTubeApiItem item = response.getItems().get(0);

            // TED 정보 DTO 반환
            return TedInfoDTO.builder()
                    .title(item.getSnippet().getTitle())
                    .description(item.getSnippet().getDescription())
                    .channelName(item.getSnippet().getChannelTitle())
                    .viewCount(Long.parseLong(item.getStatistics().getViewCount()))
                    .likeCount(Long.parseLong(item.getStatistics().getLikeCount()))
                    .commentCount(Long.parseLong(item.getStatistics().getCommentCount()))
                    .uploadDate(item.getSnippet().getPublishedAt())
                    .build();

        } catch (Exception e) {
            System.err.println("YouTube API 호출 중 예외 발생: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("YouTube API 호출 중 오류가 발생했습니다. 나중에 다시 시도해 주세요.");
        }
    }

    private String extractVideoId(String videoUrl) {
        if (videoUrl.contains("v=")) {
            return videoUrl.split("v=")[1].split("&")[0];
        } else if (videoUrl.contains("youtu.be/")) {
            return videoUrl.split("youtu.be/")[1];
        }
        throw new IllegalArgumentException("Invalid YouTube URL format");
    }

    public String getVideoTitle(String videoUrl) {
        String videoId = extractVideoId(videoUrl);

        String url = UriComponentsBuilder.fromHttpUrl(YOUTUBE_API_URL)
                .queryParam("part", "snippet")
                .queryParam("id", videoId)
                .queryParam("key", apiKey)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        YoutubeApiResponse response = restTemplate.getForObject(url, YoutubeApiResponse.class);

        if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
            // YoutubeApiResponse의 중첩 클래스 사용
            return response.getItems().get(0).getSnippet().getTitle();
        }

        return "알 수 없는 제목";
    }
}