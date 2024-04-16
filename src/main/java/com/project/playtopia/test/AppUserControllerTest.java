package com.project.playtopia.test;

import com.project.playtopia.controllers.AppUserController;
import com.project.playtopia.models.AppUser;
import com.project.playtopia.repository.AppUserRepository;
import com.project.playtopia.service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class AppUserControllerTest {

    @Mock
    private AppUserService appUserService;

    @Mock
    private Model model;

    @InjectMocks
    private AppUserController appUserController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegister() {
        AppUser user = new AppUser();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(appUserService.findUserByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(appUserService.findUserByEmail(user.getEmail())).thenReturn(Optional.empty());

        String viewName = appUserController.register(user, result, model);

        verify(appUserService).saveUser(user);
        assert viewName.equals("redirect:/games");
    }

    @Test
    void testRegisterExistingUsername() {
        AppUser user = new AppUser();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(appUserService.findUserByUsername(user.getUsername())).thenReturn(Optional.of(user));

        String viewName = appUserController.register(user, result, model);

        verify(model).addAttribute("errorMessage", "Username already exists.");
        assert viewName.equals("register");
    }

    @Test
    void testRegisterExistingEmail() {
        AppUser user = new AppUser();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        when(appUserService.findUserByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(appUserService.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        String viewName = appUserController.register(user, result, model);

        verify(model).addAttribute("errorMessage", "Email already exists.");
        assert viewName.equals("register");
    }

}
