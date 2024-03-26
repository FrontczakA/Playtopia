package com.project.playtopia.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    @Size(min = 5, message = "Password must be at least 6 characters long")
    String password;

    @NotEmpty(message ="This field can't be empty!")
    @Email(message = "Please provide a valid email address")
    String email;

    @NotEmpty(message ="This field can't be empty!")
    String name;

    @NotEmpty(message ="This field can't be empty!")
    String surname;

    String street;

    String city;

    String postalCode;

    String country;

    String role;

    @OneToMany(mappedBy = "orderOwner", cascade = CascadeType.ALL)
    private List<UserOrder> userOrders;


    }
