package ru.duoxik.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class PlayerInfo {
    private final Integer id;
    private final String nickname;
    private final Integer rank;
    private final Integer kills;
    private final Integer deaths;
    private final Integer totalMatches;
    private final Integer wonMatches;
}
