package ru.duoxik.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PlayerStats{
    private final Integer id;
    private final String nickname;
    private final Integer rank;
    private final Integer kills;
    private final Integer deaths;
    private final Integer totalMatches;
    private final Integer wonMatches;
}
