package ru.duoxik.utils;

import lombok.experimental.UtilityClass;

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
}
