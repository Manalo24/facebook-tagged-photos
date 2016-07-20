package br.com.taggedalbum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        this.reactions = new HashSet<>();
    }

    public Photo() {

    }

    @Id
    private Long id;

    private String name;

    private String link;

    private String image;

    private String album;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "photo", targetEntity = Reaction.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reaction> reactions;

    @OneToMany(mappedBy = "photo", targetEntity = ReactionStats.class, fetch = FetchType.EAGER)
    private List<ReactionStats> reactionStats;

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

    public void addReaction(Reaction reaction) {
        this.reactions.add(reaction);
        reaction.setPhoto(this);
    }

    public List<ReactionStats> getReactionStats() {
        return reactionStats;
    }

    public int getTotalOfReactions() {
        return this.reactionStats.stream().mapToInt(item -> item.getCount()).sum();
    }
}
