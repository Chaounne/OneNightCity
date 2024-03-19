package me.chaounne.onenightcity.utils;

public class Random {

    /**
     * @param min minimum
     * @param max maximum
     * @return a pseudorandom integer between min (inclusive) and max (inclusive)
     */
    public static int between(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

}