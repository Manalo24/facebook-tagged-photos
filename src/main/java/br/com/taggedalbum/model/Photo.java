package br.com.taggedalbum.model;

import javax.persistence.*;

/**
 * Created by rafaelperetta on 19/07/16.
 */

@Entity
@Table(name = "photos")
public class Photo {

    public Photo(Long id, String name, String link, String image, String album, User user) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.image = image;
        this.album = album;
        this.user = user;
    }

    public Photo() {

    }

    @Id
    private Long id;

    private String name;

    private String link;

    private String image;

    private String album;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public String getAlbum() {
        return this.album;
    }

    public User getUser() {
        return user;
    }
}
