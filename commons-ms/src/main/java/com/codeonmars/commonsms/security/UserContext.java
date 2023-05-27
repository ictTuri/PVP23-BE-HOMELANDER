package com.codeonmars.commonsms.security;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserContext {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean superuser;
    private PropertiesSlots propertiesSlots;
    private TenantsSlots tenantsSlots;
    private Set<String> roles;
    private JsonNode frontEndConfiguration;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PropertiesSlots {
        private Integer available;
        private Integer used;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TenantsSlots {
        private Integer available;
        private Integer used;
    }

    public static UserContextBuilder builder() {
        return new UserContextBuilder();
    }


    public static class UserContextBuilder {
        private Long id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private Boolean superuser;
        private PropertiesSlots propertySlots;
        private TenantsSlots tenantsSlots;
        private Set<String> roles;
        private JsonNode frontEndConfiguration;

        UserContextBuilder(){}

        public UserContextBuilder id(final Long id){
            this.id = id;
            return this;
        }

        public UserContextBuilder firstName(final String firstName){
            this.firstName = firstName;
            return this;
        }

        public UserContextBuilder lastName(final String lastName){
            this.lastName = lastName;
            return this;
        }

        public UserContextBuilder username(final String username){
            this.username = username;
            return this;
        }

        public UserContextBuilder email(final String email){
            this.email = email;
            return this;
        }

        public UserContextBuilder phone(final String phone){
            this.phone = phone;
            return this;
        }

        public UserContextBuilder superuser(final Boolean superuser){
            this.superuser = superuser;
            return this;
        }

        public UserContextBuilder propertySlots(final PropertiesSlots propertySlots){
            this.propertySlots = propertySlots;
            return this;
        }

        public UserContextBuilder tenantsSlots(final TenantsSlots tenantsSlots){
            this.tenantsSlots = tenantsSlots;
            return this;
        }

        public UserContextBuilder roles(final Set<String> roles){
            this.roles = roles;
            return this;
        }

        public UserContextBuilder frontEndConfiguration(final JsonNode frontEndConfiguration){
            this.frontEndConfiguration = frontEndConfiguration;
            return this;
        }

        public UserContext build() {
            if ((username == null || username.isEmpty()) && (email == null || email.isEmpty())) {
                throw new IllegalArgumentException("Username and email are empty");
            } /*else if (!superuser && roles.isEmpty()) {
                throw new IllegalArgumentException("User has no roles");
            }*/

            return new UserContext(this.id, this.username, this.firstName, this.lastName, this.email, this.phone, this.superuser, this.propertySlots,
                    this.tenantsSlots, this.roles, this.frontEndConfiguration);
        }
    }

    public boolean isSuperUser(){
        return superuser;
    }

    public boolean canAddTenants(){
        return (tenantsSlots.getAvailable() - tenantsSlots.getUsed()) > 0;
    }

    public boolean canAddProperties(){
        return (propertiesSlots.getAvailable() - propertiesSlots.getUsed()) > 0;
    }
}
