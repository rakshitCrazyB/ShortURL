package com.rakshit.shorturl.Service;
public class URLObj {
    private String longURL;
    private String clientId;
    private String shortURL;

    public String getClientId() {
        return clientId;
    }

    public String getLongURL() {
        return longURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public URLObj(String longURL, String clientId, String shortURL) {
        this.longURL = longURL;
        this.clientId = clientId;
        this.shortURL = shortURL;
    }
}
