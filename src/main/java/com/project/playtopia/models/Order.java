package com.project.playtopia.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double price;
    @ManyToMany
    List<Game> orderContent;
    @ManyToOne
    AppUser orderOwner;

}
