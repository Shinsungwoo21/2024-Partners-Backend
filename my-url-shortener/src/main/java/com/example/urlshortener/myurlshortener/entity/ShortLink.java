package com.example.urlshortener.myurlshortener.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ShortLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String originalUrl;

    @Column(nullable = false, unique = true)
    private String hash;

    @Column(nullable = false)
    private String shortUrl;

    @Column(nullable = false)
    private LocalDateTime createdAt;



    // 생성자
    public ShortLink(String originalUrl, String hash, String shortUrl, LocalDateTime createdAt) {
        this.originalUrl = originalUrl;
        this.hash = hash;
        this.shortUrl = shortUrl;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

