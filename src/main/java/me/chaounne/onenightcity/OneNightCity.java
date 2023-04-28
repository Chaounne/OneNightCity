package me.chaounne.onenightcity;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import me.chaounne.onenightcity.commands.CityCompleter;
import me.chaounne.onenightcity.commands.Commands;
import me.chaounne.onenightcity.events.Handler;
import org.bukkit.*;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class OneNightCity extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Location hologramLocation = new Location(Bukkit.getWorlds().get(0), 125, 156, -33);
        Hologram hologram = DHAPI.createHologram("Bienvenue", hologramLocation);
        DHAPI.addHologramLine(hologram, "Bienvenue sur le plugin OneNightCity");
        DHAPI.addHologramLine(hologram, "fait par Chaounne et Lucastrap09");
        DHAPI.addHologramLine(hologram, "et build par Mazlai et Lucastrap09");

        Location hologramLocation1 = new Location(Bukkit.getWorlds().get(0), 121, 157, -33);
        Hologram hologram1 = DHAPI.createHologram("Regle2", hologramLocation1);
        DHAPI.addHologramLine(hologram1, "Regle du jeu : Il faut être l'équipe");
        DHAPI.addHologramLine(hologram1, "ayant le plus de poudre à la fin du temps");
        DHAPI.addHologramLine(hologram1, "imparti : 4 heures");

        Location hologramLocation2 = new Location(Bukkit.getWorlds().get(0), 114, 157, -33);
        Hologram hologram2 = DHAPI.createHologram("Spawn", hologramLocation2);
        DHAPI.addHologramLine(hologram2, "Voici le Spawn, il dispose d'un marché");
        DHAPI.addHologramLine(hologram2, "ou vous pourrez échanger vos ressources");
        DHAPI.addHologramLine(hologram2, "contre des poudres spéciales. A vous de vous");
        DHAPI.addHologramLine(hologram2, "organiser pour en avoir le plus.");

        Location hologramLocation6 = new Location(Bukkit.getWorlds().get(0), 114, 156, -39);
        Hologram hologram6 = DHAPI.createHologram("hologram6", hologramLocation6);
        DHAPI.addHologramLine(hologram6, "PVP activé après 10 min de jeu.");
        DHAPI.addHologramLine(hologram6, "Le spawn est le seul endroit protégé.");
        DHAPI.addHologramLine(hologram6, "Créez votre base, pillez les autres, tuez-vous entre joueurs.");
        DHAPI.addHologramLine(hologram6, "Aucun cheat autorisé, même pas le X-RAY.");
        Location hologramLocation3 = new Location(Bukkit.getWorlds().get(0), 114, 157, -48);
        Hologram hologram3 = DHAPI.createHologram("test", hologramLocation3);
        DHAPI.addHologramLine(hologram3, "Concernant l'item spécial : la Poudre (c'est pas de la coke).");
        DHAPI.addHologramLine(hologram3, "Elle est échangeable contre des ressources au marché.");
        DHAPI.addHologramLine(hologram3, "Il existe une SuperPoudre qui vaut 10 Poudres.");
        DHAPI.addHologramLine(hologram3, "Un easter egg est présent au spawn :).");
        DHAPI.addHologramLine(hologram3, "Il risque d'y avoir des bugs, merci de nous les signaler.");
        DHAPI.addHologramLine(hologram3,"Et pour finir amusez-vous bien et bon jeu ! GL HF");

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
        Bukkit.getScoreboardManager().getMainScoreboard().getTeams().forEach(team -> team.unregister());
    }

    public static OneNightCity getInstance() {
        return getPlugin(OneNightCity.class);
    }

}
