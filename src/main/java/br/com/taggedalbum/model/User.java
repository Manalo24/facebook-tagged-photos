package br.com.taggedalbum.model;

import br.com.taggedalbum.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by rafaelperetta on 18/07/16.
 */
@Entity
@Table(name = "facebook_user")
public class User {


    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "profile_picture")
    private String profilePicture;

    @JsonIgnore
    @OneToMany(mappedBy = "user", targetEntity = Photo.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Photo> photos;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public List<Photo> getPhotos() {
        return photos;
    }
}
