package com.project.playtopia.models;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Game {
    public Game() {
    }
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    @Positive(message = "Only positive numbers")
    Double price;
    @NotEmpty(message = "This field can't be empty")
    String title;
    @NotEmpty(message = "This field can't be empty")
    String description;
    @NotEmpty(message = "This field can't be empty")
    String genre;
    @NotEmpty(message = "This field can't be empty")
    String platform;
    @NotEmpty(message = "This field can't be empty")
    String photoUrl;
    @NotNull
    @Min(value = 1, message = "This must be greater than or equal to 1")
    Long inStock;

    @ManyToMany(mappedBy = "basketContent")
    private List<UserOrder> orders;
}
