package com.rakshit.shorturl.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rakshit.shorturl.Exceptions.InvalidInputException;

public class GetShortenedUrlReqDTO {
    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("long_url")
    private String longUrl;

    public GetShortenedUrlReqDTO() {
    }

    public void validate(){
        if(longUrl == null || longUrl.isEmpty() || clientId ==null || clientId.isEmpty()){
            throw new InvalidInputException();
        }
    }

    public GetShortenedUrlReqDTO(String clientId, String longUrl) {
        this.clientId = clientId;
        this.longUrl = longUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
