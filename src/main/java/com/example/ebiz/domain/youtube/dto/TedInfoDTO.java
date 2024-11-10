package com.example.ebiz.domain.youtube.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@Data
public class TedInfoDTO {

    private String title;
    private String description;
    private String channelName;
    private long viewCount;
    private long likeCount;
    private long commentCount;
    private String uploadDate;
}