package ru.duoxik.service.matchparser.factory;

import ru.duoxik.entity.Platform;
import ru.duoxik.service.matchparser.PlatformService;

public interface PlatformServiceFactory {

    /**
     * Возвращает сервис парсер по платформе
     *
     * @param platform платформа
     * @return сервис
     */
    PlatformService getService(Platform platform);
}
