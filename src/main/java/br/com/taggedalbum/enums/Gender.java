package br.com.taggedalbum.enums;

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
        for (Gender gender: Gender.values()) {
            if (gender.getValue().equals(value)) {
                return  gender;
            }
        }
        return null;
    }
}
