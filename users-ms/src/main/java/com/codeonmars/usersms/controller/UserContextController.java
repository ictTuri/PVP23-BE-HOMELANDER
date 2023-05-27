package com.codeonmars.usersms.controller;

import com.codeonmars.commonsms.security.UserContext;
import com.codeonmars.commonsms.security.UserContextHolder;
import com.codeonmars.usersms.exception.CustomMessageException;
import com.codeonmars.usersms.model.user.dto.UserSummaryDto;
import com.codeonmars.usersms.repository.UserRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users/context")
public class UserContextController {

    public static final String CONTACT_ADMIN = "Error retrieving user data! Contact admin.";

    private final UserRepository userRepository;
    private final Mapper dozer;

    public UserContextController(UserRepository userRepository, Mapper dozer) {
        this.userRepository = userRepository;
        this.dozer = dozer;
    }

    @GetMapping("/{credential}")
    public ResponseEntity<UserSummaryDto> getContext(@PathVariable(name = "credential") String credential) {
        Optional<UserContext> context = UserContextHolder.getContext();
        var eventualRequester = context.map(UserContext::getUsername);

        if (eventualRequester.isEmpty() || !eventualRequester.get().equalsIgnoreCase(credential)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        var user = userRepository.findByUsername(credential);
        if (user.isPresent()) {
            return new ResponseEntity<>(dozer.map(user.get(), UserSummaryDto.class), HttpStatus.OK);
        }
        throw new CustomMessageException(CONTACT_ADMIN);
    }
}
