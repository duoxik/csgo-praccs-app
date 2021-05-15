package ru.duoxik.service.shuffle;

import ru.duoxik.entity.Match;
import ru.duoxik.entity.ShuffleVariation;

import java.util.List;

public interface ShuffleService {

    ShuffleVariation shuffle(List<Integer> fastcupUserIds);
}
