package br.com.taggedalbum.repository;

import br.com.taggedalbum.model.Photo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by rafaelperetta on 19/07/16.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Query("from Photo u where u.user.id = :facebookId")
    Slice<Photo> findByUserId(@Param("facebookId") Long facebookId, Pageable pageRequest);
}
