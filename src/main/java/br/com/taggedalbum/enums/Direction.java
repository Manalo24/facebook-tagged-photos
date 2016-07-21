package br.com.taggedalbum.enums;

import br.com.taggedalbum.model.Photo;

import java.util.Comparator;

/**
 * Created by rafaelperetta on 20/07/16.
 */
public enum Direction {
    ASC(1), DESC(-1);

    int value;

    Direction(int value) {
        this.value = value;
    }

    public static Direction fromValue(int value) {

        if (value == DESC.value) {
            return DESC;
        }

        return ASC;
    }

    public Comparator<Photo> sort() {

        if (value > 0) {
            return (photo1, photo2) -> photo1.getTotalOfReactions() - photo2.getTotalOfReactions();
        }

        return (photo1, photo2) -> photo2.getTotalOfReactions() - photo1.getTotalOfReactions();
    }
}