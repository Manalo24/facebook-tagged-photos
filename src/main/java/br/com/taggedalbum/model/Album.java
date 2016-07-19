package br.com.taggedalbum.model;

/**
 * Created by rafaelperetta on 19/07/16.
 */
public class Album {

    public Album(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
