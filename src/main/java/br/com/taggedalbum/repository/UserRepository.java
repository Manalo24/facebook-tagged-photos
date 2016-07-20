package br.com.taggedalbum.repository;

import br.com.taggedalbum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by rafaelperetta on 18/07/16.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u where u.id = :facebookId")
    User findById(@Param("facebookId") Long facebookId);

    @Modifying
    @Query("delete User u where u.id = :facebookId")
    void deleteUserById(@Param("facebookId") Long facebookId);
}
