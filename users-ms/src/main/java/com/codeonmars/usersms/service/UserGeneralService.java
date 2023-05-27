package com.codeonmars.usersms.service;

import com.codeonmars.commonsms.security.UserContextHolder;
import com.codeonmars.usersms.model.user.UserEntity;
import com.codeonmars.usersms.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserGeneralService {

    private final UserRepository userRepository;

    public UserGeneralService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void increaseUsedSlot() {
        var context = UserContextHolder.getContext();
        if(context.isPresent()){
            var userContext = context.get();
            var user = userRepository.findByUsernameAndEmail(userContext.getUsername(), userContext.getEmail());
            user.ifPresent(UserEntity::increasePropertySlot);
            user.ifPresent(userRepository::save);
        }
    }

    public void decreaseUsedSlot() {
        var context = UserContextHolder.getContext();
        if(context.isPresent()){
            var userContext = context.get();
            var user = userRepository.findByUsernameAndEmail(userContext.getUsername(), userContext.getEmail());
            user.ifPresent(UserEntity::decreasePropertySlot);
            user.ifPresent(userRepository::save);
        }
    }


    /* SUPPORTING METHODS */
}
