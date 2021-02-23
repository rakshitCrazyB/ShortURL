package com.rakshit.shorturl.Controller;

import com.rakshit.shorturl.DTO.GetShortenedUrlReqDTO;
import com.rakshit.shorturl.DTO.GetShortenedUrlRespDTO;
import com.rakshit.shorturl.Exceptions.InvalidUrlException;
import com.rakshit.shorturl.Service.ShortURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ShortURLController {

    @Autowired
    ShortURLService projectService;

    public ShortURLController(ShortURLService projectService) {
        this.projectService = projectService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/getShortenedURL")
    public GetShortenedUrlRespDTO getShortenedURL(@RequestBody GetShortenedUrlReqDTO getShortenedUrlReqDTO){
        getShortenedUrlReqDTO.validate();
        try {
            URI url = new URI(getShortenedUrlReqDTO.getLongUrl());
        }catch (Exception e){
            throw  new InvalidUrlException();
        }
        return new GetShortenedUrlRespDTO(projectService.
                getShortenedUrl(getShortenedUrlReqDTO.getClientId(), getShortenedUrlReqDTO.getLongUrl()).getShortURL());
    }

}
