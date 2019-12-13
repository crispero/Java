package com.supermarket.util;

import java.util.Random;

public class RandomUtils {
    public static Integer generateRandomNumber(int bound) {
        return new Random().nextInt(bound);
    }
}
