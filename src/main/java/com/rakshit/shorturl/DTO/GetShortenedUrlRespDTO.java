package com.rakshit.shorturl.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetShortenedUrlRespDTO {
    @JsonProperty("short_url")
    String shortUrl;

    public GetShortenedUrlRespDTO(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}
