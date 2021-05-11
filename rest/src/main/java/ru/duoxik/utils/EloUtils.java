package ru.duoxik.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EloUtils {

    /**
     * Метод рассчитывает новый рейтинг команды А с учетом, если она выиграет команду Б
     *
     * @param rankA рейтинг команды А
     * @param rankB рейтинг команды Б
     * @return новый рейтинг команды А
     */
    public static int calculateWin(int rankA, int rankB) {
        double K = 1 / (1 + Math.pow(10, (rankB - rankA) / 400d));
        return (int) Math.round(rankA + 50 * (1 - K));
    }

    /**
     * Метод рассчитывает новый рейтинг команды А с учетом, если она проиграет команде Б
     *
     * @param rankA рейтинг команды А
     * @param rankB рейтинг команды Б
     * @return новый рейтинг команды А
     */
    public static int calculateLose(int rankA, int rankB) {
        double K = 1 / (1 + Math.pow(10, (rankB - rankA) / 400d));
        return (int) Math.round(rankA - 50 * K);
    }
}
