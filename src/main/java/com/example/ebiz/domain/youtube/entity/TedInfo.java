package com.example.ebiz.domain.youtube.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TedVideo")
public class TedInfo {

    @Id
    private String videoId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "channelName")
    private String channelName;

    @Column(name = "viewCount")
    private long viewCount;

    @Column(name = "likeCount")
    private long likeCount;

    @Column(name = "commentCount")
    private long commentCount;

    @Column(name = "uploadDate")
    private String uploadDate;
}
