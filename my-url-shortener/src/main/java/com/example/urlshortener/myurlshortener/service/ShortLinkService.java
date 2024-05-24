package com.example.urlshortener.myurlshortener.service;

import com.example.urlshortener.myurlshortener.entity.ShortLink;
import com.example.urlshortener.myurlshortener.repository.ShortLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ShortLinkService {

    @Autowired
    private ShortLinkRepository shortLinkRepository;

    public ShortLink createShortLink(String originalUrl) {
        Optional<ShortLink> existingLink = shortLinkRepository.findByOriginalUrl(originalUrl);
        if (existingLink.isPresent()) {
            return existingLink.get();
        }

        String hash = generateHash(originalUrl);
        String shortUrl = "http://localhost:8000/short-links/" + hash;

        ShortLink shortLink = new ShortLink(originalUrl, hash, shortUrl, LocalDateTime.now());

        return shortLinkRepository.save(shortLink);
    }

    public Optional<ShortLink> findByHash(String hash) {
        return shortLinkRepository.findByHash(hash);
    }

    private String generateHash(String originalUrl) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(originalUrl.getBytes(StandardCharsets.UTF_8));
            StringBuilder hashString = new StringBuilder();
            for (byte b : hashBytes) {
                hashString.append(String.format("%02x", b));
            }
            return hashString.toString().substring(0, 8); // 해시를 짧게 자름
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found", e);
        }
    }
}

