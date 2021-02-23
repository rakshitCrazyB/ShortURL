package com.rakshit.shorturl.Service;

import com.rakshit.shorturl.Exceptions.ClientNotOnBoardedException;
import io.seruco.encoding.base62.Base62;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ShortURLService {

    //Count of total URLObject with us
    private Integer count =0;

    //HashSet containing all ClientIDs
    private ConcurrentHashMap<String,String> clientSet = new ConcurrentHashMap<>();

    //ClientId,LongURL -> ShortURL Object Mapping
    private ConcurrentHashMap<Pair<String,String>,URLObj> urlObjList = new ConcurrentHashMap<Pair<String,String>,URLObj>();

    //ShortURL -> ClientId,LongURL Mapping . NOt usable now for future extensibility.
    private ConcurrentHashMap<String,URLObj> shortUrltoUrlObjMap = new ConcurrentHashMap<String,URLObj>();

    public ShortURLService() {
        onboardClients(new ArrayList<String>(Arrays.asList("obs0","obs1"))); // Just for test to be removed in prod
    }

    synchronized public Integer getAndIncCount(){
        count++;
        return count;
    }
    public boolean checkIfClientExists(String clientID){
        return clientSet.containsKey(clientID);
    }

    public void onboardClients(ArrayList<String> clientIDs){
        for(String clientId :clientIDs){
            clientSet.put(clientId, "");
        }
    }

    public String generateShortURLHash(){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
        md.update(new byte[] {getAndIncCount().byteValue()});
        String encryptedShortUrl = new String(md.digest());

        Base62 base62 = Base62.createInstance();
        String base62EncryptedShortUrl = new String(base62.encode(encryptedShortUrl.getBytes()));

        if(base62EncryptedShortUrl.length()>9){
            base62EncryptedShortUrl=base62EncryptedShortUrl.substring(0,9);
        }
        return  base62EncryptedShortUrl;
    }

    public URLObj getShortenedUrl(final String clientId, final String longUrl) {
        if(!checkIfClientExists(clientId)){
            throw new ClientNotOnBoardedException();
        }

        if (urlObjList.containsKey(new Pair<String,String>(longUrl, clientId))){
            return urlObjList.get(new Pair<String,String>(longUrl, clientId));
        }

        String shortUrl = generateShortURLHash();
        URLObj generatedURLObj = new URLObj(longUrl, clientId, shortUrl);
        urlObjList.put(new Pair<String, String>(longUrl, clientId), generatedURLObj);
        shortUrltoUrlObjMap.put(shortUrl, generatedURLObj);
        return generatedURLObj;
    }
}
