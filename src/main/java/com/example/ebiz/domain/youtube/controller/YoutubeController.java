package com.example.ebiz.domain.youtube.controller;

import com.example.ebiz.domain.youtube.application.YoutubeService;
import com.example.ebiz.domain.youtube.dto.TedInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/video")
public class YoutubeController {

    private static final Logger logger = LoggerFactory.getLogger(YoutubeController.class);

    @Autowired
    private YoutubeService youtubeService;

    @GetMapping("/basic_info")
    public ResponseEntity<TedInfoDTO> getBasicInfo(@RequestParam("url") String url) {
        // 로깅: 들어오는 URL 확인
        logger.debug("Received URL: {}", url);

        // YoutubeService를 사용하여 URL을 처리하도록 구성
        TedInfoDTO info = youtubeService.getVideoInfo(url);

        // 로깅: 서비스에서 받은 결과 확인
        logger.debug("Returning info: {}", info);

        return ResponseEntity.ok(info);
    }
}
