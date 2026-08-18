package com.alexduzi.expensetracking.controller;

import com.alexduzi.expensetracking.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController()
@RequestMapping(name = "${api.prefix}/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<void> register(@Valid @RequestBody RegisterRequest request) {
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{email}")
//                .buildAndExpand(result.email())
//                .toUri();
//        return ResponseEntity.created(location).body(result);
//    }
}
