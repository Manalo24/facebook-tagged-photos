package br.com.taggedalbum.services;

import br.com.taggedalbum.exception.ResourceNotFoundException;
import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

/**
 * Created by rafaelperetta on 18/07/16.
 */
public interface UserService {

    void saveUserData(String accessToken, Long id);

    User findUserById(Long id) throws ResourceNotFoundException;

    Slice<Photo> findPhotoByUserId(Long id, Pageable pageRequest);

    void deleteUserById(Long id) throws ResourceNotFoundException;
}
