package com.project.playtopia.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;

    @ManyToMany
    @JoinTable(
            name = "order_game",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Game> basketContent;

    @ManyToOne
    @JoinColumn(name = "order_owner_id")
    private AppUser orderOwner;

}