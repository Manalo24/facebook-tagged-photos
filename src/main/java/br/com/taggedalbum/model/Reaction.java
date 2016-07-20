package br.com.taggedalbum.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rafaelperetta on 19/07/16.
 */
@Entity
@Table(name="reaction")
@IdClass(ReactionPK.class)
public class Reaction implements Serializable{

    public enum Type {
        NONE, LIKE, LOVE, WOW, HAHA, SAD, ANGRY, THANKFUL
    }

    public Reaction(Long id, Reaction.Type type) {
        this.id = id;
        this.type = type;
    }

    public Reaction() {


    }

    @Id
    private Long id;

    @Id
    @Enumerated(value = EnumType.STRING)
    private Reaction.Type type;

    @Id
    @ManyToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    public Long getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reaction reaction = (Reaction) o;

        if (id != null ? !id.equals(reaction.id) : reaction.id != null) return false;
        if (type != reaction.type) return false;
        return photo != null ? photo.equals(reaction.photo) : reaction.photo == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }
}
