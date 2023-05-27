package com.codeonmars.usersms.model.user.enums;

public enum Role {
    ROLE_ADMIN("All access"),
    ROLE_OWNER("Access his/her properties and users"),
    ROLE_USER("Access his/her assigned properties and all public API's");

    String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
