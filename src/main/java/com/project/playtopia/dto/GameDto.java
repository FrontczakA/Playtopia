package com.project.playtopia.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDto {
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
}
