package br.com.taggedalbum.services;

import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.User;

import java.util.List;

/**
 * Created by rafaelperetta on 18/07/16.
 */
public interface UserService {

    public void saveUserData(String accessToken);

    public User findUserById(Long id);

    public List<Photo> findPhotoByUserId(Long id);

    public List<Photo> findPhotoByUserId(Long id, boolean sortByReaction, int direction);
}
