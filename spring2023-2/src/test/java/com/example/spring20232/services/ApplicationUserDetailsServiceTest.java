package com.example.spring20232.services;

import com.example.spring20232.model.entity.UserEntity;
import com.example.spring20232.model.entity.UserRoleEntity;
import com.example.spring20232.model.enums.UserRoleEnum;
import com.example.spring20232.repository.UserRepository;
import com.example.spring20232.service.ApplicationUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsServiceTest {

    //this class mirrors the original class - has all his injected dependencies;

    private ApplicationUserDetailsService toTest;

    private final String EXISTING_EMAIL = "admin@example.com";
    private final String NOT_EXISTING_EMAIL = "pesho@example.com";

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setup() {
        toTest = new ApplicationUserDetailsService(mockUserRepository);
    }


    @Test
    void testUserFound() {

        UserRoleEntity testAdminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity testModeratorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);


        UserEntity testUserEntity = new UserEntity()
                .setEmail(EXISTING_EMAIL)
                .setPassword("topsecret")
                .setRoles(List.of(testAdminRole, testModeratorRole));

        when(mockUserRepository.findUserEntityByEmail(EXISTING_EMAIL))
                .thenReturn(Optional.of(testUserEntity));

        UserDetails userEntity = toTest.loadUserByUsername(EXISTING_EMAIL);

        //ASSERT
        Assertions.assertNotNull(userEntity);
        Assertions.assertEquals(EXISTING_EMAIL, userEntity.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(), userEntity.getPassword());

        Assertions.assertEquals(2, userEntity.getAuthorities().size());
        assertRole(userEntity.getAuthorities(), "ROLE_ADMIN");
        assertRole(userEntity.getAuthorities(), "ROLE_MODERATOR");
        //Assertions.assertEquals(2, userDetails.getAuthorities().size());
    }

    private void assertRole(Collection<? extends GrantedAuthority> getAuthorities,
                            String role) {
        getAuthorities.stream().filter(a -> role.equals(a.getAuthority()))
                .findAny()
                .orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));


    }

    @Test
    void testUserNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> {
            toTest.loadUserByUsername(NOT_EXISTING_EMAIL);
        });
    }


}
