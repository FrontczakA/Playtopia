package com.project.playtopia.service;

import com.project.playtopia.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
}
