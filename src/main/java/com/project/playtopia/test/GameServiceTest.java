package com.project.playtopia.test;
import com.project.playtopia.dto.GameDto;
import com.project.playtopia.models.Game;
import com.project.playtopia.repository.GameRepository;
import com.project.playtopia.service.GameService;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAllGames() {
        List<Game> games = new ArrayList<>();
        when(gameRepository.findAll()).thenReturn(games);

        List<GameDto> result = gameService.findAllGames();

        verify(gameRepository).findAll();
        assert result.isEmpty();
    }

    @Test
    void testFindAllGamesPage() {
        Pageable pageable = Pageable.unpaged();
        Page<Game> gamePage = mock(Page.class);
        when(gameRepository.findAll(pageable)).thenReturn(gamePage);

        Page<GameDto> result = gameService.findAllGamesPage(pageable);

        verify(gameRepository).findAll(pageable);
        assert result.equals(gamePage);
    }

    @Test
    void testSaveGame() {
        Game game = new Game();

        gameService.saveGame(game);

        verify(gameRepository).save(game);
    }

    @Test
    void testDeleteGame() {
        Long id = 1L;

        gameService.deleteGame(id);

        verify(gameRepository).deleteById(id);
    }

    @Test
    void testFindGameById() throws NotFoundException {
        Long id = 1L;
        Game game = new Game();
        when(gameRepository.findById(id)).thenReturn(Optional.of(game));

        GameDto result = gameService.findGameById(id);

        verify(gameRepository).findById(id);
        assert result != null;
    }

    @Test
    void testFilterGames() {
        double min = 0;
        double max = 100;
        String console = "Xbox";
        String category = "Action";
        Pageable pageable = Pageable.unpaged();
        Page<Game> games = mock(Page.class);
        when(gameRepository.filterGamesList(min, max, console, category, pageable)).thenReturn(games);

        Page<GameDto> result = gameService.filterGames(min, max, console, category, pageable);

        verify(gameRepository).filterGamesList(min, max, console, category, pageable);
        assert result != null;
    }

    @Test
    void testUpdateGame() {
        Long id = 1L;
        Game game = new Game();
        Game savedGame = new Game();
        savedGame.setId(id);

        when(gameRepository.findById(id)).thenReturn(Optional.of(savedGame));

        gameService.updateGame(id, game);

        verify(gameRepository).save(savedGame);
    }

}
