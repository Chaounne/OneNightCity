package me.chaounne.onenightcity.utils;

import java.util.List;

public class RandomFromList {
    
    public static <T> T get(List<T> list) {
        return list.get((int) (Math.random() * list.size()));
    }

}
