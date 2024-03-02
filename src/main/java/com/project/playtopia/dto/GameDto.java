package com.project.playtopia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDto {
    Long id;
    Double price;
    String title;
    String description;
    String genre;
    String platform;
    String photoUrl;
}
