package com.altairum.config;

import com.altairum.dto.RegistrationRequest;
import com.altairum.model.Role;
import com.altairum.model.RoleEnum;
import com.altairum.repository.RoleRepository;
import com.altairum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
//        initializeRole();
//        initializeAdmin();
    }

    private void initializeAdmin() {
        var admin = userService.createUser(
            new RegistrationRequest("admin", "test@mail.com", "12345"));
        userService.changeRole(admin.getId(), RoleEnum.ADMIN);
    }

    private void initializeRole() {
        for (RoleEnum role : RoleEnum.values()) {
            if (roleRepository.findByName(role).isEmpty())
                roleRepository.save(new Role(role));
        }
    }
}
