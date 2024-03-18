package me.chaounne.onenightcity.utils;

public class Random {

    public static int between(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

}