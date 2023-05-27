package com.codeonmars.usersms.model.user.enums;

public enum Gender {
    MALE("male"),
    FEMALE("female"),
    NULL("empty");

    String value;

    private Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
