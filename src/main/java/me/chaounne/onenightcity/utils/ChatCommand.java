package me.chaounne.onenightcity.utils;

import org.bukkit.Bukkit;

public class ChatCommand {

    public static void execute(String command) {
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
    }

}