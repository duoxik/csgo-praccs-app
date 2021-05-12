package ru.duoxik.service.matchparser;

import ru.duoxik.entity.Match;

import java.io.IOException;

/**
 * Интерфейс для работы со сторонними сервисами
 */
public interface PlatformService {

    /**
     * Возвращает матч по id
     *
     * @param matchId id матча
     * @return матч
     */
    Match getMatch(Integer matchId) throws IOException;
}
