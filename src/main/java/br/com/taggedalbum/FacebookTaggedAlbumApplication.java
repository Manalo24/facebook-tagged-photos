package br.com.taggedalbum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * Created by rafaelperetta on 17/07/16.
 */

@SpringBootApplication
@EnableSpringDataWebSupport
public class FacebookTaggedAlbumApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacebookTaggedAlbumApplication.class, args);
    }
}
