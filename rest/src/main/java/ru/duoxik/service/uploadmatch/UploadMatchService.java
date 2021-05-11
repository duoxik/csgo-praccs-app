package ru.duoxik.service.uploadmatch;

import ru.duoxik.entity.Match;
import ru.duoxik.entity.Platform;

public interface UploadMatchService {

    /**
     * Парсит и загружает данные из матча
     *
     * @param platform платформа
     * @param matchId  id матча
     * @return результат загрузки
     */
    Match uploadMatch(Platform platform, Integer matchId);
}
