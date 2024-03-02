package com.project.playtopia.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Game {
    public Game() {
    }
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double price;
    String title;
    String description;
    String genre;
    String platform;
    String photoUrl;
}
