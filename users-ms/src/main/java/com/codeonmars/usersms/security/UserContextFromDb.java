package com.codeonmars.usersms.security;

import com.codeonmars.commonsms.security.UserContext;
import com.codeonmars.commonsms.security.UserContextRetriever;
import com.codeonmars.usersms.model.user.UserEntity;
import com.codeonmars.usersms.repository.UserRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.codeonmars.commonsms.security.UserContext.PropertiesSlots;
import static com.codeonmars.commonsms.security.UserContext.TenantsSlots;

@Component
public class UserContextFromDb implements UserContextRetriever {

    private final UserRepository userRepo;
    private final Mapper dozer;

    public UserContextFromDb(UserRepository userRepo, Mapper dozer) {
        this.userRepo = userRepo;
        this.dozer = dozer;
    }

    @Override
    public Optional<UserContext> retrieveUserContext(String credential) {

        if (credential != null) {
            var userEntity = userRepo.findByUsername(credential);
            if (userEntity.isPresent()) {
                return userContextFromUser(userEntity.get());
            }
        }
        return Optional.empty();
    }

    private Optional<UserContext> userContextFromUser(UserEntity user) {
        return Optional.of(UserContext.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .tenantsSlots(dozer.map(user.getTenantsSlots(), TenantsSlots.class))
                .propertySlots(dozer.map(user.getPropertiesSlots(), PropertiesSlots.class))
                .superuser(user.getSuperuser()).build());
    }
}
