package br.com.taggedalbum.repository;

import br.com.taggedalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by rafaelperetta on 19/07/16.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("from Photo u where u.user.id = :facebookId")
    List<Photo> findByUserId(@Param("facebookId") Long facebookId);
}
