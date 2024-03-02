package com.project.playtopia.controllers;

import com.project.playtopia.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
    GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping("/")
    String getAllGames(){

        return "games-list";
    }

}
