package com.project.playtopia.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ConfirmedUserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "confirmed_order_game",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private List<Game> orderedGames;

    @ManyToOne
    @JoinColumn(name = "order_owner_id")
    private AppUser orderOwner;

    private String deliveryMethod;
    private String street;

    private String city;

    private String postalCode;

    private String country;

}
