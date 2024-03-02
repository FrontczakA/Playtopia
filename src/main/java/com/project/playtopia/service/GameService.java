package com.project.playtopia.service;

import com.project.playtopia.dto.GameDto;
import com.project.playtopia.models.Game;
import com.project.playtopia.repository.GameRepository;
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
