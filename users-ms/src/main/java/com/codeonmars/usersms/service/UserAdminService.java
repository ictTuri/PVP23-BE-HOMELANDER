package com.codeonmars.usersms.service;

import com.codeonmars.usersms.model.user.dto.UserSummaryDto;
import com.codeonmars.usersms.repository.UserRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserAdminService {

    private final UserRepository userRepository;
    private final Mapper dozer;

    public UserAdminService(UserRepository userRepository, Mapper dozer) {
        this.userRepository = userRepository;
        this.dozer = dozer;
    }

    @Transactional(readOnly = true)
    public List<UserSummaryDto> findAllUsers() {
        var users = userRepository.findAll();
        return users.stream().map(user -> dozer.map(user, UserSummaryDto.class)).toList();
    }

    /* SUPPORTING METHODS */
}
