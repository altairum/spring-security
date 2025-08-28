package com.altairum.controller;

import com.altairum.dto.RegistrationRequest;
import com.altairum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        var user = userService.createUser(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
