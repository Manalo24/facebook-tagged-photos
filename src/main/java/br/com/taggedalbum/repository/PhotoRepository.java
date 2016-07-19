package br.com.taggedalbum.repository;

import br.com.taggedalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rafaelperetta on 19/07/16.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
