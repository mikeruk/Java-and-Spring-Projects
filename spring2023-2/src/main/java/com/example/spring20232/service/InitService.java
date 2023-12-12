package com.example.spring20232.service;


import com.example.spring20232.model.entity.UserEntity;
import com.example.spring20232.model.entity.UserRoleEntity;
import com.example.spring20232.model.enums.UserRoleEnum;
import com.example.spring20232.repository.UserRepository;
import com.example.spring20232.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InitService {

    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitService(UserRoleRepository userRoleRepository,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       @Value("${app.default.password}") String defaultPassword) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUsers();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            var moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);
            var adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);

            userRoleRepository.save(moderatorRole);
            userRoleRepository.save(adminRole);
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            initAdmin();
        }
    }


    private void initAdmin() {



        List<UserRoleEntity> listWithOnerole = new ArrayList<>();
        UserRoleEntity userRoleEntity = new UserRoleEntity();

        Optional<UserRoleEntity> userRoleEntityByRole =
                userRoleRepository
                        .findUserRoleEntityByRole(UserRoleEnum.ADMIN);

        listWithOnerole.add(userRoleEntityByRole.orElse(null));
        System.out.println();

        var adminUser = new UserEntity().
                setEmail("admin@example.com").
                setFirstName("Admin").
                setLastName("Adminov").
                setPassword(passwordEncoder.encode("topsecret")).
                setRoles(listWithOnerole);
                //setRoles(userRoleRepository.findAll());


        userRepository.save(adminUser);
    }

}
