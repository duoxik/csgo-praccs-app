package ru.duoxik.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class ShuffleVariation {
    private final List<PlayerInfo> leftTeam;
    private final Integer leftTeamAvgRank;
    private final List<PlayerInfo> rightTeam;
    private final Integer rightTeamAvgRank;
}
