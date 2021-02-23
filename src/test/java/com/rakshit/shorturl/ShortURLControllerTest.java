package com.rakshit.shorturl;

import com.rakshit.shorturl.Controller.ShortURLController;
import com.rakshit.shorturl.DTO.GetShortenedUrlReqDTO;
import com.rakshit.shorturl.DTO.GetShortenedUrlRespDTO;
import com.rakshit.shorturl.Exceptions.ClientNotOnBoardedException;
import com.rakshit.shorturl.Exceptions.InvalidInputException;
import com.rakshit.shorturl.Exceptions.InvalidUrlException;
import com.rakshit.shorturl.Service.ShortURLService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ShortURLControllerTest {

    ShortURLController shortURLController;
    @Before
    public void setup() {
        ShortURLService shortURLService = new ShortURLService();
        shortURLController = new ShortURLController(shortURLService);
        shortURLService.onboardClients(new ArrayList<String>(Arrays.asList("obs0", "obs1")));
    }

    @Test(expected = InvalidUrlException.class)
    public void InvalidUrlExceptionTest() throws Exception {
        shortURLController.getShortenedURL(new GetShortenedUrlReqDTO("obs1","www. google"));
    }

    @Test(expected = InvalidInputException.class)
    public void InvalidInputExceptionTest() throws Exception {
        shortURLController.getShortenedURL(new GetShortenedUrlReqDTO(null,"www.google"));
    }

    @Test(expected = ClientNotOnBoardedException.class)
    public void ClientNotOnBoardedExceptionTest() throws Exception {
        shortURLController.getShortenedURL(new GetShortenedUrlReqDTO("obs","www.google"));
    }

    @Test
    public void happyFlowTest() throws Exception {
        GetShortenedUrlReqDTO getShortenedUrlReqDTO = new GetShortenedUrlReqDTO("obs0","http://www.yahoo.com");
        GetShortenedUrlRespDTO getShortenedUrlRespDTO = shortURLController.getShortenedURL(getShortenedUrlReqDTO);
    }

    @Test
    public void SameClientSameURLTest() throws Exception {
        GetShortenedUrlRespDTO getShortenedUrlRespDTO1 = shortURLController.getShortenedURL(new GetShortenedUrlReqDTO("obs0","http://www.yahoo.com"));
        GetShortenedUrlRespDTO getShortenedUrlRespDTO2 = shortURLController.getShortenedURL(new GetShortenedUrlReqDTO("obs0","http://www.yahoo.com"));
        assertEquals(getShortenedUrlRespDTO1.getShortUrl(),getShortenedUrlRespDTO2.getShortUrl());
    }

    @Test
    public void DifferentClientSameURLTest() throws Exception {
        GetShortenedUrlRespDTO getShortenedUrlRespDTO1 = shortURLController.getShortenedURL(new GetShortenedUrlReqDTO("obs0","http://www.yahoo.com"));
        GetShortenedUrlRespDTO getShortenedUrlRespDTO2 = shortURLController.getShortenedURL(new GetShortenedUrlReqDTO("obs1","http://www.yahoo.com"));
        assertNotEquals(getShortenedUrlRespDTO1.getShortUrl(),getShortenedUrlRespDTO2.getShortUrl());
    }

    @Test
    public void DifferentClientDifferentURLTest() throws Exception {
        GetShortenedUrlRespDTO getShortenedUrlRespDTO1 = shortURLController.getShortenedURL(new GetShortenedUrlReqDTO("obs0","http://www.google.com"));
        GetShortenedUrlRespDTO getShortenedUrlRespDTO2 = shortURLController.getShortenedURL(new GetShortenedUrlReqDTO("obs1","http://www.yahoo.com"));
        assertNotEquals(getShortenedUrlRespDTO1.getShortUrl(),getShortenedUrlRespDTO2.getShortUrl());
    }

    @Test
    public void SameClientDifferentURLTest() throws Exception {
        GetShortenedUrlRespDTO getShortenedUrlRespDTO1 = shortURLController.getShortenedURL(new GetShortenedUrlReqDTO("obs0","http://www.google.com"));
        GetShortenedUrlRespDTO getShortenedUrlRespDTO2 = shortURLController.getShortenedURL(new GetShortenedUrlReqDTO("obs0","http://www.yahoo.com"));
        assertNotEquals(getShortenedUrlRespDTO1.getShortUrl(),getShortenedUrlRespDTO2.getShortUrl());
    }
}
