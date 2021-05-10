package ru.duoxik.service;

import ru.duoxik.entity.PlayerStats;

import java.util.List;

/**
 * Интерфейс сервиса для работы со статистикой игроков
 */
public interface PlayersService {

    /**
     * Возвращает статистику игрок по заданному нику
     *
     * @param nickname никнейм игрока
     * @return статистика
     */
    PlayerStats getPlayerStats(String nickname);

    /**
     * Возвращает статистику всех игроков
     *
     * @return список статистик игроков
     */
    List<PlayerStats> getAllPlayerStats();
}
