package com.example.urlshortener.myurlshortener.controller;

import com.example.urlshortener.myurlshortener.entity.ShortLink;
import com.example.urlshortener.myurlshortener.service.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/short-links")
public class ShortLinkController {

    @Autowired
    private ShortLinkService shortLinkService;

    @PostMapping
    public ResponseEntity<ShortLink> createShortLink(@RequestBody String originalUrl) {
        ShortLink shortLink = shortLinkService.createShortLink(originalUrl);
        return ResponseEntity.ok(shortLink);
    }

    @GetMapping("/{hash}")
    public ResponseEntity<String> redirectToOriginalUrl(@PathVariable String hash) {
        Optional<ShortLink> shortLink = shortLinkService.findByHash(hash);
        if (shortLink.isPresent()) {
            return ResponseEntity.status(302).header("Location", shortLink.get().getOriginalUrl()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
