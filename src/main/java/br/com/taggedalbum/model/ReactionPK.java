package br.com.taggedalbum.model;

import java.io.Serializable;

/**
 * Created by rafaelperetta on 19/07/16.
 */

public class ReactionPK implements Serializable {


    private Long id;

    private Reaction.Type type;

    private Photo photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reaction.Type getType() {
        return type;
    }

    public void setType(Reaction.Type type) {
        this.type = type;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ReactionPK)) return false;
        if (obj == null) return false;
        ReactionPK pk = (ReactionPK) obj;
        return pk.id == id && pk.type.equals(type) && pk.photo.getId().equals(photo.getId());
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }
}
