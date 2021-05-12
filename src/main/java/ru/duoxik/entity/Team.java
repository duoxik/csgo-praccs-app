package ru.duoxik.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Team {
    private final Integer id;
    private final List<PlayerStats> players;
    private final Boolean winner;
    private final Integer score;
}