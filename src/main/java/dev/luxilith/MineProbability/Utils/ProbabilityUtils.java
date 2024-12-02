package dev.luxilith.MineProbability.Utils;

import java.util.Random;

public class ProbabilityUtils {

    private static final Random random = new Random();

    public static double generateRandomProbability(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static boolean isValidProbability(int value) {
        return value >= 1 && value <= 100;
    }
}