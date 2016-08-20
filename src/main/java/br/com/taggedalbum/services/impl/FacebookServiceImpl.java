package br.com.taggedalbum.services.impl;

import br.com.taggedalbum.enums.Gender;
import br.com.taggedalbum.model.Photo;
import br.com.taggedalbum.model.Reaction;
import br.com.taggedalbum.model.User;
import br.com.taggedalbum.services.FacebookService;
import com.restfb.*;
import com.restfb.types.Reactions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rafaelperetta on 19/07/16.
 */

@Service
public class FacebookServiceImpl implements FacebookService {


    public static final String FIELDS = "fields";
    public static final String TYPE = "type";

    public static final String USER_FIELDS = "name,gender,picture";
    public static final String PHOTO_FIELDS = "id,name,album,link,images,reactions";
    public static final String REACTION_FIELDS =  "id,name,type";
    public static final String PHOTO_TYPE = "tagged";

    public static final String PHOTOS_PATH = "/photos";
    public static final String REACTIONS_PATH = "/reactions";

    private FacebookClient getFacebookTemplate(String token) {
        return new DefaultFacebookClient(token, Version.LATEST);
    }

    @Override
    public User getUser(String accessToken, Long userId) {
        FacebookClient facebookClient = getFacebookTemplate(accessToken);
        com.restfb.types.User facebookUser = facebookClient.fetchObject(String.valueOf(userId), com.restfb.types.User.class, Parameter.with("fields", USER_FIELDS));


        User user = new User();
        user.setId(Long.valueOf(facebookUser.getId()));
        user.setName(facebookUser.getName());
        user.setProfilePicture(facebookUser.getPicture().getUrl());
        user.setGender(Gender.getEnumFromValue(facebookUser.getGender()));

        return user;
    }

    @Override
    public List<Photo> getUserPhotos(Long userId, String accessToken) {

        Connection<com.restfb.types.Photo> facebookPhotos = fetchUserPhotos(userId, accessToken);

        User user = new User();
        user.setId(userId);

        Iterator facebookPhotosIterator = facebookPhotos.iterator();
        List<Photo> photos = new ArrayList<>();

        while(facebookPhotosIterator.hasNext()) {
            List<com.restfb.types.Photo> facebookPhotoList = (List<com.restfb.types.Photo>) facebookPhotosIterator.next();

            facebookPhotoList.forEach(item -> {

                String albumName = item.getAlbum() != null ? item.getAlbum().getName() : null;

                Connection<Reactions.ReactionItem> reactions = fetchPhotosReactions(Long.valueOf(item.getId()), accessToken);
                List<Reaction> reactionList = getReactionList(reactions);

                Photo photo = new Photo(Long.valueOf(item.getId()), item.getName(), item.getLink(), item.getImages().get(0).getSource(), albumName, user);

                reactionList.forEach(reaction -> {
                    photo.addReaction(reaction);
                });

                photos.add(photo);
            });
        }

        return photos;
    }

    protected Connection<com.restfb.types.Photo> fetchUserPhotos(Long userId, String accessToken) {
        FacebookClient facebookClient = getFacebookTemplate(accessToken);
        Connection<com.restfb.types.Photo> facebookPhotos = facebookClient.fetchConnection(userId + PHOTOS_PATH, com.restfb.types.Photo.class,
                Parameter.with(FIELDS, PHOTO_FIELDS), Parameter.with(TYPE, PHOTO_TYPE));

        return facebookPhotos;
    }

    protected Connection<Reactions.ReactionItem> fetchPhotosReactions(Long photoId, String accessToken) {
        FacebookClient facebookClient = getFacebookTemplate(accessToken);
        Connection<Reactions.ReactionItem> reactions = facebookClient.fetchConnection(photoId + REACTIONS_PATH, Reactions.ReactionItem.class,
                Parameter.with(FIELDS, REACTION_FIELDS));

        return reactions;
    }

    protected List<Reaction> getReactionList(Connection<Reactions.ReactionItem> reactionItemConnection) {
        Iterator<List<Reactions.ReactionItem>> reactionIterator = reactionItemConnection.iterator();
        List<Reaction> reactions = new ArrayList<>();

        while(reactionIterator.hasNext()) {
            List<Reactions.ReactionItem> reactionItemList = reactionIterator.next();

            reactionItemList.forEach(item -> {
                Reaction reaction = new Reaction(Long.valueOf(item.getId()), Reaction.Type.valueOf(item.getType()));
                reactions.add(reaction);
            });

        }

        return reactions;
    }
}
