package com.project.playtopia.controllers;

import com.project.playtopia.dto.GameDto;
import com.project.playtopia.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {
    GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping("")
    String getAllGames(Model model){
        List<GameDto> gamesList = gameService.findAllGames();
        model.addAttribute("gamesList", gamesList);
        return "games-list";
    }

}
