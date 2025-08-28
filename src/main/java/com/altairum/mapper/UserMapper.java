package com.altairum.mapper;

import com.altairum.dto.RegistrationRequest;
import com.altairum.model.User;

public class UserMapper {
    public static User toEntity(RegistrationRequest registrationRequest) {
        return User.builder()
                .fullName(registrationRequest.getFullName())
                .email(registrationRequest.getEmail())
                .password(registrationRequest.getPassword())
                .build();
    }
}
