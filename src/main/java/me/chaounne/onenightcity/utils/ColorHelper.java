package me.chaounne.onenightcity.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;

public class ColorHelper {

    public static Color getColorFromChatColor(ChatColor chatColor) {
        if (chatColor == ChatColor.AQUA) return Color.AQUA;
        if (chatColor == ChatColor.BLACK) return Color.BLACK;
        if (chatColor == ChatColor.BLUE) return Color.BLUE;
        if (chatColor == ChatColor.DARK_AQUA) return Color.TEAL;
        if (chatColor == ChatColor.DARK_BLUE) return Color.NAVY;
        if (chatColor == ChatColor.DARK_GRAY) return Color.GRAY;
        if (chatColor == ChatColor.DARK_GREEN) return Color.fromRGB(0, 65, 0);
        if (chatColor == ChatColor.DARK_PURPLE) return Color.PURPLE;
        if (chatColor == ChatColor.DARK_RED) return Color.MAROON;
        if (chatColor == ChatColor.GOLD) return Color.fromRGB(218, 165, 32);
        if (chatColor == ChatColor.GRAY) return Color.SILVER;
        if (chatColor == ChatColor.GREEN) return Color.LIME;
        if (chatColor == ChatColor.LIGHT_PURPLE) return Color.FUCHSIA;
        if (chatColor == ChatColor.RED) return Color.RED;
        if (chatColor == ChatColor.WHITE) return Color.WHITE;
        if (chatColor == ChatColor.YELLOW) return Color.YELLOW;
        return Color.WHITE;
    }

    public static ChatColor getChatColorFromColor(Color color) {
        if (color == Color.AQUA) return ChatColor.AQUA;
        if (color == Color.BLACK) return ChatColor.BLACK;
        if (color == Color.BLUE) return ChatColor.BLUE;
        if (color == Color.TEAL) return ChatColor.DARK_AQUA;
        if (color == Color.NAVY) return ChatColor.DARK_BLUE;
        if (color == Color.GRAY) return ChatColor.DARK_GRAY;
        if (color == Color.fromRGB(0, 65, 0)) return ChatColor.DARK_GREEN;
        if (color == Color.PURPLE) return ChatColor.DARK_PURPLE;
        if (color == Color.MAROON) return ChatColor.DARK_RED;
        if (color == Color.fromRGB(218, 165, 32)) return ChatColor.GOLD;
        if (color == Color.SILVER) return ChatColor.GRAY;
        if (color == Color.LIME) return ChatColor.GREEN;
        if (color == Color.FUCHSIA) return ChatColor.LIGHT_PURPLE;
        if (color == Color.RED) return ChatColor.RED;
        if (color == Color.WHITE) return ChatColor.WHITE;
        if (color == Color.YELLOW) return ChatColor.YELLOW;
        return ChatColor.WHITE;
    }

    public static ChatColor getRandomChatColor() {
        int index = (int) (Math.random() * 16);
        switch (index) {
            case 0:
                return ChatColor.AQUA;
            case 1:
                return ChatColor.BLACK;
            case 2:
                return ChatColor.BLUE;
            case 3:
                return ChatColor.DARK_AQUA;
            case 4:
                return ChatColor.DARK_BLUE;
            case 5:
                return ChatColor.DARK_GRAY;
            case 6:
                return ChatColor.DARK_GREEN;
            case 7:
                return ChatColor.DARK_PURPLE;
            case 8:
                return ChatColor.DARK_RED;
            case 9:
                return ChatColor.GOLD;
            case 10:
                return ChatColor.GRAY;
            case 11:
                return ChatColor.GREEN;
            case 12:
                return ChatColor.LIGHT_PURPLE;
            case 13:
                return ChatColor.RED;
            case 14:
                return ChatColor.WHITE;
            case 15:
                return ChatColor.YELLOW;
            default:
                return ChatColor.WHITE;
        }
    }

}
