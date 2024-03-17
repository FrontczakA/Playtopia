package com.project.playtopia.service;

import com.project.playtopia.models.AppUser;
import com.project.playtopia.models.Game;
import com.project.playtopia.repository.AppUserRepository;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {
    AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user =  appUserRepository.findByUsername(username);
        if(user.isPresent()){
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        }else{
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(AppUser user){
        if(user.getRole() == null) return new String[]{"USER"};
        return user.getRole().split(",");
    }

    public AppUser saveUser(AppUser user){
        return appUserRepository.save(user);
    }
    public Optional<AppUser> findUserById(Long id){
        return appUserRepository.findById(id);
    }

    public Optional<AppUser> findUserByUsername(String username){
        return appUserRepository.findByUsername(username);
    }
    public void changeEmail(String username, String newEmail, String confirmEmail) throws Exception {
        if (!newEmail.equals(confirmEmail)) {
            throw new Exception("New email and confirm email do not match");
        }
        Optional<AppUser> userOptional = appUserRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            user.setEmail(newEmail);
            appUserRepository.save(user);
        } else {
            throw new Exception("User not found");
        }
    }

    public void changeName(String username, String newName, String newSurname) throws NotFoundException {
        Optional<AppUser> userOptional = appUserRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            user.setName(newName);
            user.setSurname(newSurname);
            appUserRepository.save(user);
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public void changeAddress(String username, String street, String city, String postalCode, String country) throws NotFoundException {
        Optional<AppUser> userOptional = appUserRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            user.setStreet(street);
            user.setCity(city);
            user.setPostalCode(postalCode);
            user.setCountry(country);
            appUserRepository.save(user);
        } else {
            throw new NotFoundException("User not found");
        }
    }

    public Optional<AppUser> findUserByEmail(String email){
        return appUserRepository.findByEmail(email);
    }

}