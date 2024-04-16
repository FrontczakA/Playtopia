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
    @NotEmpty(message = "You can't leave empty fields")
    String title;
    @NotEmpty(message = "You can't leave empty fields")
    String description;
    @NotEmpty(message = "You can't leave empty fields")
    String genre;
    @NotEmpty(message = "You can't leave empty fields")
    String platform;
    @NotEmpty(message = "You can't leave empty fields")
    String photoUrl;
    @NotNull
    @Min(value = 1, message = "This must be greater than or equal to 1")
    Long inStock;

    @ManyToMany(mappedBy = "orderedGames", cascade = CascadeType.REMOVE)
    private List<ConfirmedUserOrder> confirmedUserOrders;
    @ManyToMany(mappedBy = "basketContent"  , cascade = CascadeType.REMOVE)
    private List<UserOrder> orders;
}
