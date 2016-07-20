package br.com.taggedalbum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rafaelperetta on 19/07/16.
 */

@Entity
@Table(name = "reaction_stats")
public class ReactionStats implements Serializable{

    private int count;

    @Id
    @Enumerated(EnumType.STRING)
    private Reaction.Type type;

    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    public int getCount() {
        return count;
    }

    public Reaction.Type getType() {
        return type;
    }

    public Photo getPhoto() {
        return photo;
    }
}
