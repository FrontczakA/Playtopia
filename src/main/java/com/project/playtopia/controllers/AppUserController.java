package com.project.playtopia.controllers;

import com.project.playtopia.models.AppUser;
import com.project.playtopia.service.AppUserService;
import jakarta.validation.Valid;
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
    public String register(@Valid @ModelAttribute("user") AppUser user, BindingResult result){
        if(result.hasErrors()){
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        appUserService.saveUser(user);
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

    @GetMapping("/change-password")
    public String changePassword(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<AppUser> user = appUserService.findUserByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "change-password";
        } else {
            return "redirect:/games";
        }
    }
    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<AppUser> userOptional = appUserService.findUserByUsername(username);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                model.addAttribute("errorMessage", "Incorrect old password");
                return "change-password";
            }
            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("errorMessage", "New password and confirm password do not match");
                return "change-password";
            }
            user.setPassword(passwordEncoder.encode(newPassword));
            appUserService.saveUser(user);
            return "redirect:/profile";
        } else {
            return "redirect:/games";
        }
    }

    @GetMapping("/change-email")
    public String showChangeEmailForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<AppUser> user = appUserService.findUserByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "change-email";
        } else {
            return "redirect:/games";
        }
    }

    @PostMapping("/change-email")
    public String changeEmail(@RequestParam("newEmail") String newEmail,
                              @RequestParam("confirmEmail") String confirmEmail,
                              Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            appUserService.changeEmail(username, newEmail, confirmEmail);
            model.addAttribute("successMessage", "Email successfully changed.");
            return "redirect:/profile?successMessage=Email%20changed%20successfully";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "change-email";
        }
    }

    @GetMapping("/change-name")
    public String showChangeNameForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<AppUser> user = appUserService.findUserByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "change-name";
        } else {
            return "redirect:/games";
        }
    }

    @PostMapping("/change-name")
    public String changeName(@RequestParam("newName") String newName,
                             @RequestParam("newSurname") String newSurname,
                             Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            appUserService.changeName(username, newName, newSurname);
            model.addAttribute("successMessage", "Name and surname successfully changed.");
            return "redirect:/profile?successMessage=Name%20and%20surname%20changed%20successfully";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "change-name";
        }
    }

    @GetMapping("/change-address")
    public String changeAddressForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "change-address";
    }

    @PostMapping("/change-address")
    public String changeAddress(@RequestParam("street") String street,
                                @RequestParam("city") String city,
                                @RequestParam("postalCode") String postalCode,
                                @RequestParam("country") String country,
                                Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            appUserService.changeAddress(username, street, city, postalCode, country);
            return "redirect:/profile";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "change-address";
        }
    }
}
