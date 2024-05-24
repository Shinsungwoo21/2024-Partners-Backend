package com.example.urlshortener.myurlshortener.repository;

import com.example.urlshortener.myurlshortener.entity.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {
    Optional<ShortLink> findByHash(String hash);
    Optional<ShortLink> findByOriginalUrl(String originalUrl);
}

