package com.project.playtopia.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AppUserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(message ="This field can't be empty!")
    String username;

    @NotEmpty(message ="This field can't be empty!")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    String password;

    @NotEmpty(message ="This field can't be empty!")
    @Email(message = "Please provide a valid email address")
    String email;

    @NotEmpty(message ="This field can't be empty!")
    String name;

    @NotEmpty(message ="This field can't be empty!")
    String surname;

    @NotEmpty(message ="This field can't be empty!")
    String address;

}