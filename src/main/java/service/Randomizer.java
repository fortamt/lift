package service;

import java.util.Random;

public class Randomizer {

    public static Integer randomValueInRange(Integer min, Integer max) {
        return new Random().nextInt(max+1 - min) + min;
    }
}