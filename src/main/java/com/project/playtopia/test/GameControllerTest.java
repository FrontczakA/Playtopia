package com.project.playtopia.test;

import com.project.playtopia.controllers.GameController;
import com.project.playtopia.dto.GameDto;
import com.project.playtopia.models.Game;
import com.project.playtopia.service.GameService;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.*;

public class GameControllerTest {

    @Mock
    private GameService gameService;

    @Mock
    private Model model;

    @InjectMocks
    private GameController gameController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllGames() {
        Pageable pageable = Pageable.unpaged();
        Page<GameDto> gameDtoPage = mock(Page.class);
        when(gameService.findAllGamesPage(pageable)).thenReturn(gameDtoPage);

        String result = gameController.getAllGames(pageable, model);

        verify(model).addAttribute("gamesPage", gameDtoPage);
        assert result.equals("games-list");
    }

    @Test
    void testAddNewGame() {
        Game game = new Game();
        BindingResult result = mock(BindingResult.class);

        String viewName = gameController.addNewGame(game, result);

        verify(gameService).saveGame(game);
        assert viewName.equals("redirect:/games");
    }

    @Test
    void testAddNewGameValidationErrors() {
        Game game = new Game();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        String viewName = gameController.addNewGame(game, result);

        assert viewName.equals("add-game");
    }

    @Test
    void testAddGameForm() {
        String viewName = gameController.addGameForm(model);

        assert viewName.equals("add-game");
    }

    @Test
    void testDeleteGameForm() {
        String viewName = gameController.deleteGameForm(model);

        assert viewName.equals("delete-game");
    }

    @Test
    void testDeleteGame() {
        Long id = 1L;

        String viewName = gameController.deleteGame(id, model);

        verify(gameService).deleteGame(id);
        assert viewName.equals("redirect:/games");
    }

    @Test
    void testEditGameForm() {
        String viewName = gameController.editGameForm(model);

        assert viewName.equals("edit-game");
    }

    @Test
    void testEditGame() {
        Long id = 1L;
        Game game = new Game();
        BindingResult result = mock(BindingResult.class);

        String viewName = gameController.editGame(id, model, game, result);

        verify(gameService).updateGame(id, game);
        assert viewName.equals("redirect:/games");
    }

    @Test
    void testGameDetails() throws NotFoundException {
        Long id = 1L;
        GameDto gameDto = new GameDto();
        when(gameService.findGameById(id)).thenReturn(gameDto);

        String viewName = gameController.gameDetails(id, model);

        verify(gameService).findGameById(id);
        assert viewName.equals("game-details");
    }

}
