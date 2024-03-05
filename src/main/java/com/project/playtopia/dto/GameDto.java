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
}
