package com.project.playtopia.service;

import com.project.playtopia.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService  {
    GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


}
