package com.project.playtopia.repository;

import com.project.playtopia.dto.GameDto;
import com.project.playtopia.models.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT g FROM Game g WHERE (:min IS NULL OR g.price > :min) AND (:max IS NULL OR g.price < :max) AND (:console IS NULL OR g.platform = :console) AND (:category='' OR g.genre = :category)")
    Page<Game> filterGamesList(double min, double max, String console, String category, Pageable pageable);
}
