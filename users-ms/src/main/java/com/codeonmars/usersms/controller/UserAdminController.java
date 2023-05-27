package com.codeonmars.usersms.controller;

import com.codeonmars.commonsms.aspects.IsSuperUser;
import com.codeonmars.usersms.model.user.dto.UserSummaryDto;
import com.codeonmars.usersms.service.UserAdminService;
import com.codeonmars.usersms.service.UserAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/admin/users")
public class UserAdminController {

    private final UserAdminService userAdminService;

    public UserAdminController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }


    @GetMapping
    @IsSuperUser
    public ResponseEntity<List<UserSummaryDto>> getAllUsers(){
        return new ResponseEntity<>(userAdminService.findAllUsers(), HttpStatus.OK);
    }

}
