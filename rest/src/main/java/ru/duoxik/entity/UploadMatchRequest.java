package ru.duoxik.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UploadMatchRequest {
    private final Platform platform;
    private final Integer matchId;
}
