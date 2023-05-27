package com.codeonmars.usersms.controller;

import com.codeonmars.usersms.service.UserGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/user")
public class UserController {

    @Autowired
    private UserGeneralService userGeneralService;


    @PutMapping("/increase-used-property")
    public void increaseUsedSlot(){
        userGeneralService.increaseUsedSlot();
    }

    @PutMapping("/decrease-used-property")
    public void decreaseUsedSlot(){
        userGeneralService.decreaseUsedSlot();
    }
}
