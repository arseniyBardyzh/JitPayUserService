package com.jitpay.userservice.web.controller;

import com.jitpay.userservice.model.dto.inbound.UserData;
import com.jitpay.userservice.repository.storage.user.exception.UserRepositoryException;
import com.jitpay.userservice.service.user.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    public String saveUserData(@RequestBody @Valid UserData userData) throws UserRepositoryException {
        return userService.saveUser(userData);
    }

    @GetMapping("/{userId}")
    public UserData saveUserData(@PathVariable("userId") String userId) throws UserRepositoryException {
        return userService.getUserById(userId);
    }
}
