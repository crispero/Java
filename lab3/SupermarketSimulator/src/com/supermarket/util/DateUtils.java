package com.supermarket.util;

import java.util.Date;

public class DateUtils {
    private final static Integer SIMULATED_TIME = 1800000; // 30 min in ms
    private final static Integer ONE_HOUR_IN_MS = 3600000;
    private final static int OPENING_HOUSE = 8;

    public static void simulateTime(Date date) {
        date.setTime(date.getTime() + RandomUtils.generateRandomNumber(SIMULATED_TIME));
    }

    public static Date getSupermarketCloseTime(Date date) {
        return new Date(date.getTime() + ONE_HOUR_IN_MS * OPENING_HOUSE);
    }
}
