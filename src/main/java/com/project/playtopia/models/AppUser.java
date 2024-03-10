package com.project.playtopia.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty(message ="This field can't be empty!")
    String username;
    @NotEmpty(message ="This field can't be empty!")
    String password;
    @NotEmpty(message ="This field can't be empty!")
    String email;
    @NotEmpty(message ="This field can't be empty!")
    String name;
    @NotEmpty(message ="This field can't be empty!")
    String surname;
    String role;
}
