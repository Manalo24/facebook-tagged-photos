package br.com.taggedalbum.services.impl;

import br.com.taggedalbum.exception.ResourceNotFoundException;
import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.User;
import br.com.taggedalbum.repository.PhotoRepository;
import br.com.taggedalbum.repository.UserRepository;
import br.com.taggedalbum.services.FacebookService;
import br.com.taggedalbum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by rafaelperetta on 19/07/16.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PhotoRepository photoRepository;

    private FacebookService facebookService;

    @Autowired
    UserServiceImpl(UserRepository userRepository, PhotoRepository photoRepository, FacebookService facebookService) {
        this.userRepository = userRepository;
        this.facebookService = facebookService;
        this.photoRepository = photoRepository;
    }

    @Override
    @Transactional
    public void saveUserData(String accessToken, Long facebookId) {

        Optional<User> userOptional = userRepository.findById(facebookId);
        if (userOptional.isPresent()) {
            userRepository.deleteUserById(facebookId);
        }

        User user = facebookService.getUser(accessToken, facebookId);
        userRepository.save(user);
        saveUserPhotos(user.getId(), accessToken);
    }

    @Transactional
    protected void saveUserPhotos(Long userId, String accessToken) {
        List<Photo> photos = facebookService.getUserPhotos(userId, accessToken);
        photoRepository.save(photos);
    }

    @Override
    public User findUserById(Long id) throws ResourceNotFoundException {
        Optional<User> userResult = userRepository.findById(id);
        return userResult.orElseThrow(() -> new ResourceNotFoundException());
    }

    @Override
    public Slice<Photo> findPhotoByUserId(Long id, Pageable pageRequest) {
        return photoRepository.findByUserId(id, pageRequest);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) throws ResourceNotFoundException {
        User user = findUserById(id);
        userRepository.deleteUserById(user.getId());
    }
}
