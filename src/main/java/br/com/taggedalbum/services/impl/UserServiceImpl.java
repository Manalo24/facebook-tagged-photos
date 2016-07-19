package br.com.taggedalbum.services.impl;

import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.User;
import br.com.taggedalbum.repository.PhotoRepository;
import br.com.taggedalbum.repository.UserRepository;
import br.com.taggedalbum.services.FacebookService;
import br.com.taggedalbum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void saveUserData(String accessToken) {
        User user = facebookService.getUser(accessToken);
        userRepository.save(user);
        saveUserPhotos(user.getId(), accessToken);
    }

    protected void saveUserPhotos(Long userId, String accessToken) {
        List<Photo> photos = facebookService.getUserPhotos(userId, accessToken);
        photoRepository.save(photos);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id);
    }
}
