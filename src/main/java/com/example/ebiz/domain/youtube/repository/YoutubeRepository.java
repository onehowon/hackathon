package com.example.ebiz.domain.youtube.repository;

import com.example.ebiz.domain.youtube.entity.TedInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface YoutubeRepository extends JpaRepository<TedInfo, String>{
    Optional<TedInfo> findByTitle(String title);
}
