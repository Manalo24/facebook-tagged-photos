package br.com.taggedalbum.services;

import br.com.taggedalbum.exception.ResourceNotFoundException;
import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.User;

import java.util.List;

/**
 * Created by rafaelperetta on 18/07/16.
 */
public interface UserService {

    public void saveUserData(String accessToken);

    public User findUserById(Long id) throws ResourceNotFoundException;

    public List<Photo> findPhotoByUserId(Long id);

    public List<Photo> findPhotoByUserId(Long id, boolean sortByReaction, int direction);

    public void deleteUserById(Long id) throws ResourceNotFoundException;
}
