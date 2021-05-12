package ru.duoxik.service.playerstats;

import ru.duoxik.entity.PlayerInfo;

import java.util.List;

/**
 * Интерфейс сервиса для работы со статистикой игроков
 */
public interface PlayersInfoService {

    /**
     * Возвращает информацию о игроке по заданному нику
     *
     * @param nickname никнейм игрока
     * @return статистика
     */
    PlayerInfo getPlayerInfo(String nickname);

    /**
     * озвращает информацию о игроке по fastcup_user_id
     *
     * @param fastcupUserId id игрока на платформе FASTCUP
     * @return информация об игроке
     */
    PlayerInfo getPlayerInfo(Integer fastcupUserId);

    /**
     * Возвращает информацию о всех игроках
     *
     * @return список игроков
     */
    List<PlayerInfo> getAllPlayerInfos();

    /**
     * Сохраняет игрока
     *
     * @param nickname      ник
     * @param fastcupUserId id игрока на платформе
     * @return игрок
     */
    PlayerInfo createPlayer(String nickname, Integer fastcupUserId);

    /**
     * Обновляет информацию об игроке
     *
     * @param playerInfo информацию об игроке
     * @return была ли обновлена информация
     */
    boolean updatePlayer(PlayerInfo playerInfo);
}
