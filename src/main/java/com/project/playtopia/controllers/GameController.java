package com.project.playtopia.controllers;

import com.project.playtopia.dto.GameDto;
import com.project.playtopia.models.Game;
import com.project.playtopia.service.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {
    GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping("")
    String getAllGames(@PageableDefault(size = 6) Pageable pageable, Model model) {
        Page<GameDto> gamesPage = gameService.findAllGamesPage(pageable);
        model.addAttribute("gamesPage", gamesPage);
        return "games-list";
    }

    @PostMapping("/new")
    String addNewGame(@Validated @ModelAttribute("game") Game game, BindingResult result){
        if(result.hasErrors()) {
            return "add-game";
        }
        gameService.saveGame(game);
        return "redirect:/games";
    }
    @GetMapping("/new")
    public String addGameForm(Model model){
        Game game = new Game();
        model.addAttribute("game", game);
        return "add-game";
    }
    @GetMapping("/delete")
    String deleteGameForm(Model model){
        List<GameDto> gamesList = gameService.findAllGames();
        model.addAttribute("gamesList", gamesList);
        return "delete-game";
    }

    @PostMapping("/delete")
    String deleteGame(@RequestParam("gameId") Long id, Model model) {
        gameService.deleteGame(id);
        List<GameDto> gamesList = gameService.findAllGames();
        model.addAttribute("gamesList", gamesList);
        return "redirect:/games";
    }

    @GetMapping("/edit")
    String editGameForm(Model model){
        List<GameDto> gamesList = gameService.findAllGames();
        model.addAttribute("gamesList", gamesList);
        model.addAttribute("game", new Game());
        return "edit-game";
    }

    @PostMapping("/edit")
    String editGame(@RequestParam("id") Long id, Model model, @Validated @ModelAttribute("game") Game game, BindingResult result){
        if(result.hasErrors()) {
            model.addAttribute("game", game);
            return "edit-game";
        }
        gameService.updateGame(id, game);
        return "redirect:/games";
    }

}
