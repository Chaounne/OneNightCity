package me.chaounne.onenightcity.utils;

public class JumpScore {
    
    public static String formatJumpTime(long time) {
        int secondes = (int) (time / 1000000000);
        int miliemes = ((int) (time % 1000000000) / 1000000);
        int minutes = secondes / 60;
        secondes = secondes % 60;
        return (minutes > 9 ? minutes : "0" + minutes) + ":" + (secondes > 9 ? secondes : "0" + secondes) + "." + (miliemes > 9 ? (miliemes > 99 ? miliemes : "0" + miliemes) : "00" + miliemes);
    }

}
