package br.com.taggedalbum.model;

/**
 * Created by rafaelperetta on 18/07/16.
 */
public enum Gender {

    MALE("male"), FEMALE("female");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Gender getEnumFromValue(String value) {
        for(int i = 0; i < Gender.values().length; i++) {
            if (Gender.values()[i].getValue().equals(value)) {
                return Gender.values()[i];
            }
        }
        return null;
    }
}
