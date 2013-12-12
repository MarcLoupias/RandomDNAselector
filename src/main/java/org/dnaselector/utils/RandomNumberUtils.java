package org.dnaselector.utils;

import java.util.List;
import java.util.Random;

public class RandomNumberUtils {
    public static Long generateLong(Long min, Long maxExclusive) {
        return (long)(Math.random() * (maxExclusive - min)) + min;
    }
    public static Long generateLong(Long min, Long maxExclusive, List<Long> excludedValues) {
        long random = generateLong(min, maxExclusive);

        boolean found = false;
        for(Long val : excludedValues) {
            if(val == random) {
                found = true;
            }
        }

        if(found){
            return generateLong(min, maxExclusive, excludedValues);
        } else {
            return random;
        }

    }

    public static Integer generateInteger(int min, int maxInclusive) {
        Random rand = new Random();

        return rand.nextInt((maxInclusive - min) + 1) + min;
    }
    public static Integer generateInteger(int min, int maxInclusive, List<Integer> excludedValues) {
        int random = generateInteger(min, maxInclusive);

        boolean found = false;
        for(Integer val : excludedValues) {
            if(val == random) {
                found = true;
            }
        }

        if(found){
            return generateInteger(min, maxInclusive, excludedValues);
        } else {
            return random;
        }

    }
}
