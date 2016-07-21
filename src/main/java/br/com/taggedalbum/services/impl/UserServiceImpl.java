package br.com.taggedalbum.services.impl;

import br.com.taggedalbum.enums.OrderBy;
import br.com.taggedalbum.exception.ResourceNotFoundException;
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
import java.util.stream.Collectors;

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
    public User findUserById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        return user;
    }

    @Override
    public List<Photo> findPhotoByUserId(Long id) {
        return photoRepository.findByUserId(id);
    }

    @Override
    public List<Photo> findPhotoByUserId(Long id, boolean sortByReaction, int direction) {

        List<Photo> photos = findPhotoByUserId(id);

        if (sortByReaction) {
            OrderBy orderBy = OrderBy.fromValue(direction);

            return photos.stream()
                    .sorted(orderBy.sort())
                    .collect(Collectors.toList());
        }

        return photos;
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) throws ResourceNotFoundException {
        User user = findUserById(id);
        userRepository.deleteUserById(user.getId());
    }
}
