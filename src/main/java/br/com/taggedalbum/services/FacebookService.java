package br.com.taggedalbum.services;

import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.User;

import java.util.List;

/**
 * The {@link FacebookService} is responsible to fetch data from facebook graph API.
 */
public interface FacebookService {

    User getUser(String accessToken, Long userId);

    List<Photo> getUserPhotos(Long userId, String accessToken);

}
