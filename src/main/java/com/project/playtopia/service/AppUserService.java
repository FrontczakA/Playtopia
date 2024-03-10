package com.project.playtopia.service;

import com.project.playtopia.models.AppUser;
import com.project.playtopia.models.Game;
import com.project.playtopia.repository.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

}
