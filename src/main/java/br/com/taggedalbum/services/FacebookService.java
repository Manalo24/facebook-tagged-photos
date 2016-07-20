package br.com.taggedalbum.services;

import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.User;

import java.util.List;

/**
 * Created by rafaelperetta on 19/07/16.
 */
public interface FacebookService {

    public User getUser(String accessToken);

    List<Photo> getUserPhotos(Long userId, String accessToken);

}
