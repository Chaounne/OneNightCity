package me.chaounne.onenightcity;

import me.chaounne.onenightcity.events.Handler;
import org.bukkit.plugin.java.JavaPlugin;

public final class OneNightCity extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("One Night City is starting...");

        getPlugin(OneNightCity.class).getServer().getPluginManager().registerEvents(new Handler(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static OneNightCity getInstance() {
        return getPlugin(OneNightCity.class);
    }
}
