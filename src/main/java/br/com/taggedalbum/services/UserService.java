package br.com.taggedalbum.services;

import br.com.taggedalbum.exception.FacebookResourceNotFound;
import br.com.taggedalbum.exception.ResourceNotFoundException;
import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

/**
 * Created by rafaelperetta on 18/07/16.
 */
public interface UserService {

    public void saveUserData(String accessToken, Long id) throws FacebookResourceNotFound;

    public User findUserById(Long id) throws ResourceNotFoundException;

    public Slice<Photo> findPhotoByUserId(Long id, Pageable pageRequest);

    public void deleteUserById(Long id) throws ResourceNotFoundException;
}
