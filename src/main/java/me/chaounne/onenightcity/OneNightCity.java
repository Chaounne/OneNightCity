package me.chaounne.onenightcity;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import me.chaounne.onenightcity.commands.CityCompleter;
import me.chaounne.onenightcity.commands.Commands;
import me.chaounne.onenightcity.events.Handler;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class OneNightCity extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("One Night City is starting...");
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.teleport(new Location(player.getWorld(), 121, 154, -40));
            player.getPlayer().setGameMode(GameMode.ADVENTURE);
        }

        Commands cmd = new Commands();
        TabCompleter tab = new CityCompleter();

        getCommand("city").setExecutor(cmd);
        getCommand("city").setTabCompleter(tab);
        getCommand("bounty").setExecutor(cmd);

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
