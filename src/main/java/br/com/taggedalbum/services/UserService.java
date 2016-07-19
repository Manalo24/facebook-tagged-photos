package br.com.taggedalbum.services;

import br.com.taggedalbum.model.User;

/**
 * Created by rafaelperetta on 18/07/16.
 */
public interface UserService {

    public void saveUserData(String accessToken);

    public User findUserById(Long id);
}
