package ru.duoxik.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Match {
    private final Team team1;
    private final Team team2;
}
