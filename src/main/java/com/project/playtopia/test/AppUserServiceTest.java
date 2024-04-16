package com.project.playtopia.test;

import com.project.playtopia.models.AppUser;
import com.project.playtopia.repository.AppUserRepository;
import com.project.playtopia.service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AppUserService appUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        String username = "testUser";
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword("password");
        when(appUserRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        UserDetails userDetails = appUserService.loadUserByUsername(username);

        assertTrue(userDetails instanceof User);
        assertEquals(username, userDetails.getUsername());
        assertEquals("encodedPassword", userDetails.getPassword());
    }

    @Test
    void testSaveUser() {
        AppUser user = new AppUser();

        appUserService.saveUser(user);

        verify(appUserRepository).save(user);
    }

}
