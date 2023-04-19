package me.chaounne.onenightcity;

import me.chaounne.onenightcity.commands.Commands;
import me.chaounne.onenightcity.events.Handler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class OneNightCity extends JavaPlugin {

    private List<ChatColor> availableColors = new ArrayList<>(Arrays.asList(ChatColor.values()));


    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("One Night City is starting...");

        Commands cmd = new Commands();

        getCommand("city").setExecutor(cmd);

        getPlugin(OneNightCity.class).getServer().getPluginManager().registerEvents(new Handler(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        //A NE PAS ACTIVER QUAND ON LANCERA L'EVENT
        //Bukkit.getScoreboardManager().getMainScoreboard().getTeams().forEach(team -> team.unregister());
    }

    public static OneNightCity getInstance() {
        return getPlugin(OneNightCity.class);
    }

}
