package br.com.taggedalbum.repository;

import br.com.taggedalbum.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by rafaelperetta on 18/07/16.
 */
public interface UserRepository extends Repository<User, Long> {

    @Query("from User u where u.id = :facebookId")
    Optional<User> findById(@Param("facebookId") Long facebookId);

    @Modifying(clearAutomatically = true)
    @Query("delete User u where u.id = :facebookId")
    void deleteUserById(@Param("facebookId") Long facebookId);

    User save(User user);

}
