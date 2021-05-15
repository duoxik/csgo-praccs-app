package ru.duoxik.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import ru.duoxik.entity.PlayerInfo;

import java.util.List;

@UtilityClass
public class EloUtils {

    /**
     * Метод рассчитывает сколько рейтинга нужно добавить команде А при выигрыше у команды Б
     *
     * @param rankA рейтинг команды А
     * @param rankB рейтинг команды Б
     * @return delta кол-во очков нужно добавить
     */
    public static int calculateDeltaWin(int rankA, int rankB) {
        return (int) Math.round(50 * (1 - 1 / (1 + Math.pow(10, (rankB - rankA) / 400d))));
    }

    /**
     * Метод рассчитывает сколько рейтинга нужно отнять у команды А при проигрыше команде Б
     *
     * @param rankA рейтинг команды А
     * @param rankB рейтинг команды Б
     * @return delta кол-во очков нужно отнять
     */
    public static int calculateDeltaLose(int rankA, int rankB) {
        return (int) Math.round(50 / (1 + Math.pow(10, (rankB - rankA) / 400d)));
    }

    public static int calculateTeamAvgRank(@NonNull List<PlayerInfo> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("size of players list should be > 0");
        }

        return (int) Math.round(players.stream().map(PlayerInfo::getRank)
                .mapToInt(Integer::intValue).average().getAsDouble());
    }
}
