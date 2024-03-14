package com.project.playtopia.controllers;

import com.project.playtopia.dto.GameDto;
import com.project.playtopia.models.AppUser;
import com.project.playtopia.service.AppUserService;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AppUserController {
    AppUserService appUserService;
    PasswordEncoder passwordEncoder;

    public AppUserController(AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") AppUser user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "register";
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(user.getUsername());
        appUser.setEmail(user.getEmail());
        appUser.setName(user.getName());
        appUser.setSurname(user.getSurname());
        appUser.setPassword(passwordEncoder.encode(user.getPassword()));
        appUser.setRole("USER");
        appUserService.saveUser(appUser);
        return "redirect:/games";
    }
    @GetMapping("/profile")
    public String userProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<AppUser> user = appUserService.findUserByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user-page";
        } else {
            return "redirect:/games";
        }
    }
}
