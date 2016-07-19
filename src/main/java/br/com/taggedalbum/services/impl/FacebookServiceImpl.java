package br.com.taggedalbum.services.impl;

import br.com.taggedalbum.model.Album;
import br.com.taggedalbum.model.Gender;
import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.User;
import br.com.taggedalbum.services.FacebookService;
import com.restfb.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LongSummaryStatistics;

/**
 * Created by rafaelperetta on 19/07/16.
 */

@Service
public class FacebookServiceImpl implements FacebookService {


    private String USER_FIELDS = "name,gender,picture";
    private String PHOTO_FIELDS = "id,name,album,link,images";
    private String PHOTO_TYPE = "tagged";

    private FacebookClient getFacebookTemplate(String token) {
        return new DefaultFacebookClient(token, Version.LATEST);
    }

    @Override
    public User getUser(String accessToken) {
        FacebookClient facebookClient = getFacebookTemplate(accessToken);
        com.restfb.types.User facebookUser = facebookClient.fetchObject("me", com.restfb.types.User.class, Parameter.with("fields", USER_FIELDS));


        User user = new User();
        user.setId(Long.valueOf(facebookUser.getId()));
        user.setName(facebookUser.getName());
        user.setProfilePicture(facebookUser.getPicture().getUrl());
        user.setGender(Gender.getEnumFromValue(facebookUser.getGender()));

        return user;
    }

    @Override
    public List<Photo> getUserPhotos(Long userId, String accessToken) {
        FacebookClient facebookClient = getFacebookTemplate(accessToken);
        Connection<com.restfb.types.Photo> facebookPhotos = facebookClient.fetchConnection(userId + "/photos", com.restfb.types.Photo.class,
                Parameter.with("fields", PHOTO_FIELDS), Parameter.with("type", PHOTO_TYPE));

        User user = new User();
        user.setId(userId);

        Iterator facebookPhotosIterator = facebookPhotos.iterator();
        List<Photo> photos = new ArrayList<>();

        while(facebookPhotosIterator.hasNext()) {
            List<com.restfb.types.Photo> facebookPhotoList = (List<com.restfb.types.Photo>) facebookPhotosIterator.next();

            facebookPhotoList.forEach(item -> {
                String albumName = null;
                if (item.getAlbum() != null) {
                    albumName = item.getAlbum().getName();
                }
                Photo photo = new Photo(Long.valueOf(item.getId()), item.getName(), item.getLink(), item.getImages().get(0).getSource(), albumName, user);
                photos.add(photo);
            });
        }

        return photos;
    }
}
