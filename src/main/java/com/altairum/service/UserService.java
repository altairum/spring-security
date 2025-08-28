package com.altairum.service;

import com.altairum.dto.RegistrationRequest;
import com.altairum.mapper.UserMapper;
import com.altairum.model.RoleEnum;
import com.altairum.model.User;
import com.altairum.repository.RoleRepository;
import com.altairum.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(RegistrationRequest registrationRequest) {
        String email = registrationRequest.getEmail();
        if (userRepository.existsByEmail(email))
            throw new RuntimeException("Email " + email + " is already in use");
        User user = UserMapper.toEntity(registrationRequest);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        var userRole = roleRepository.findByName(RoleEnum.USER).orElseThrow(() ->
                new EntityNotFoundException("Role USER not found"));
        user.setRole(userRole);
        return userRepository.save(user);
    }

    public void changeRole(Long id, RoleEnum newRole) {
        var user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found"));
        if (user.getRole().getName().equals(newRole))
            throw new RuntimeException("User already has this role");
        var role = roleRepository.findByName(newRole).orElseThrow(() ->
                new EntityNotFoundException("Role " + newRole + "not found"));
        user.setRole(role);
        userRepository.save(user);
    }

}
