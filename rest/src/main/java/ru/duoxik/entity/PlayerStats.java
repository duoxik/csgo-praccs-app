package ru.duoxik.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlayerStats {
    private final Integer fastcupUserId;
    private final Integer teamId;
    private final String nickname;
    private final Integer kills;
    private final Integer deaths;
}
