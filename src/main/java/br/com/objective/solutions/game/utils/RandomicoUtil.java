package br.com.objective.solutions.game.utils;

import java.util.Random;

public final class RandomicoUtil {

    private RandomicoUtil() {
    }

    private static Random getRandom() {
        return new Random();
    }

    private static int gerarValorRandomico() {
        int min = 1;
        int max = 5000;
        return min + getRandom().nextInt(max);
    }

    public static Long gerarValorRandomicoLong() {
        return (long) gerarValorRandomico();
    }

    public static Integer gerarValorRandomicoInteger() {
        return gerarValorRandomico();
    }

    public static Integer gerarValorRandomicoAte(int max) {
        int min = 1;
        return min + getRandom().nextInt(max);
    }
}
