package com.project.playtopia.service;

import com.project.playtopia.dto.GameDto;
import com.project.playtopia.models.Game;
import com.project.playtopia.repository.GameRepository;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService  {
    GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameDto> findAllGames() {
        List<Game> games = gameRepository.findAll();
        return games.stream()
                .map(this::mapToGameDto)
                .collect(Collectors.toList());
    }

    public Page<GameDto> findAllGamesPage(Pageable pageable) {
        Page<Game> gamesPage = gameRepository.findAll(pageable);
        return gamesPage.map(this::mapToGameDto);
    }

    public Game saveGame(Game game){
        return gameRepository.save(game);
    }

    public void deleteGame(Long id){
        gameRepository.deleteById(id);
    }
    public GameDto findGameById(Long id) throws NotFoundException {
        Game game = gameRepository.findById(id).get();
        return mapToGameDto(game);
    }

    public Page<GameDto> filterGames(double min, double max, String console, String category, Pageable pageable) {
        Page<Game> games = gameRepository.filterGamesList(min, max, console, category, pageable);
        return games.map(this::mapToGameDto);
    }


    public void updateGame(Long id, Game game) {
        Game gameToUpdate = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
        gameToUpdate.setPrice(game.getPrice());
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setDescription(game.getDescription());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPlatform(game.getPlatform());
        gameToUpdate.setPhotoUrl(game.getPhotoUrl());
        gameToUpdate.setInStock(game.getInStock());
        gameRepository.save(gameToUpdate);
    }

    public GameDto mapToGameDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        gameDto.setPrice(game.getPrice());
        gameDto.setTitle(game.getTitle());
        gameDto.setDescription(game.getDescription());
        gameDto.setGenre(game.getGenre());
        gameDto.setPlatform(game.getPlatform());
        gameDto.setPhotoUrl(game.getPhotoUrl());
        return gameDto;
    }

    public Game mapToGame(GameDto gameDto) {
        Game game = new Game();
        game.setId(gameDto.getId());
        game.setPrice(gameDto.getPrice());
        game.setTitle(gameDto.getTitle());
        game.setDescription(gameDto.getDescription());
        game.setGenre(gameDto.getGenre());
        game.setPlatform(gameDto.getPlatform());
        game.setPhotoUrl(gameDto.getPhotoUrl());
        return game;
    }
}
