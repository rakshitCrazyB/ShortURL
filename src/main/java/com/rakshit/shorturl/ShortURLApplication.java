package com.rakshit.shorturl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.rakshit.shorturl"})
public class ShortURLApplication {

    public static void main(String... args) {
        SpringApplication.run(ShortURLApplication.class, args);
    }

}
