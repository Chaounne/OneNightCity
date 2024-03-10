package me.chaounne.onenightcity;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import fr.mrmicky.fastinv.FastInvManager;
import me.chaounne.onenightcity.commands.CityCompleter;
import me.chaounne.onenightcity.commands.Commands;
import me.chaounne.onenightcity.events.Handler;
import me.chaounne.onenightcity.events.JumpHandler;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.Team;
import me.chaounne.onenightcity.game.jump.Checkpoint;

import org.bukkit.*;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
public final class OneNightCity extends JavaPlugin {

    @Override
    public void onEnable() {
        for (World world : Bukkit.getWorlds()) {
            // Désactiver la règle de jeu des achievements pour chaque monde
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        }
        World world = Bukkit.getWorlds().get(0); // Vous pouvez changer cela si besoin

        for (   Entity entity : world.getEntities()) {
            if (entity.getType() == EntityType.VILLAGER) {
                Location entityLocation = entity.getLocation();
                double distance = entityLocation.distanceSquared(new Location(world, 0, 70, 0));
                if (distance <= 200 * 200) {
                    entity.remove();
                }
            }
        }

        Bukkit.getOnlinePlayers().forEach(player -> player.getAdvancementProgress(Bukkit.getAdvancement(NamespacedKey.minecraft("story/root"))).awardCriteria("impossible"));
        ONCGame.getInstance().resetTeams();
        FastInvManager.register(this);

        // Plugin startup logic
        Location hologramLocation = new Location(Bukkit.getWorlds().get(0), 125, 156, -33);
        Hologram hologram = DHAPI.createHologram("Bienvenue", hologramLocation);
        DHAPI.addHologramLine(hologram, "Bienvenue sur le plugin OneNightCity");
        DHAPI.addHologramLine(hologram, "fait par Chaounne, Lucastrap09 et S0meB0dy_");
        DHAPI.addHologramLine(hologram, "et build par Mazlai et Lucastrap09");

        Location hologramLocation1 = new Location(Bukkit.getWorlds().get(0), 121, 157, -33);
        Hologram hologram1 = DHAPI.createHologram("Regle2", hologramLocation1);
        DHAPI.addHologramLine(hologram1, "Regle du jeu : Il faut être l'équipe");
        DHAPI.addHologramLine(hologram1, "ayant le plus de poudre à la fin du temps");
        DHAPI.addHologramLine(hologram1, "imparti : 3 heures");

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
        DHAPI.addHologramLine(hologram6, "Créez votre base, pillez les autres, entre tuez-vous !");
        DHAPI.addHologramLine(hologram6, "Aucun cheat autorisé, même pas le X-RAY.");
        Location hologramLocation3 = new Location(Bukkit.getWorlds().get(0), 114, 157, -48);
        Hologram hologram3 = DHAPI.createHologram("test", hologramLocation3);
        DHAPI.addHologramLine(hologram3, "Concernant l'item spécial : la Poudre .");
        DHAPI.addHologramLine(hologram3, "Vous pouvez en avoir en tradant des items au spawn.");
        DHAPI.addHologramLine(hologram3, ChatColor.GOLD+"Il existe une "+ChatColor.BOLD+"SuperPoudre "+ChatColor.GOLD+"qui vaut "+ChatColor.BOLD+" 1000 "+"Poudres.");

        DHAPI.addHologramLine(hologram3, "Il risque d'y avoir des bugs, merci de nous les signaler.");
        DHAPI.addHologramLine(hologram3,"Et pour finir amusez-vous bien et bon jeu ! GL HF");


        Location hologramLocation8 = new Location(Bukkit.getWorlds().get(0), 123, 158, -49);
        Hologram hologram8 = DHAPI.createHologram("nouveautes", hologramLocation8);
        DHAPI.addHologramLine(hologram8, ChatColor.RED + "NOUVEAUTES");
        DHAPI.addHologramLine(hologram8, "Fix des bugs de la derniere game!");
        DHAPI.addHologramLine(hologram8, "Rework des générateurs de minerais");
        DHAPI.addHologramLine(hologram8, "Rework du système de jump au lobby");
        DHAPI.addHologramLine(hologram8, "Mise à jour de la safe zone (+ de pvp au spawn)");
        DHAPI.addHologramLine(hologram8, "Plus d'info dans le guide sur discord ! Amusez-vous bien !");

        Location hologramLocation4 = new Location(Bukkit.getWorlds().get(0), 125, 157, -40);
        Hologram hologram4 = DHAPI.createHologram("test2", hologramLocation4);

        DHAPI.addHologramLine(hologram4, ChatColor.RED + "Création et gestion de team :");
        DHAPI.addHologramLine(hologram4, ChatColor.WHITE + "Pour créer une team : /city team create \"NomDeLaTeam\"");
        DHAPI.addHologramLine(hologram4, ChatColor.WHITE + "Pour ajouter un joueur à votre équipe : /city team add \"NomDuJoueur\"");
        DHAPI.addHologramLine(hologram4, ChatColor.WHITE + "Pour voir les membres de votre équipe : /city team list");
        DHAPI.addHologramLine(hologram4, ChatColor.GREEN + "Amusez-vous bien !");

        Location leaderboardLocation = new Location(Bukkit.getWorlds().get(0), 118, 218, -36);
        DHAPI.createHologram("Classement", leaderboardLocation);

        Checkpoint checkpoint = new Checkpoint(new Location(Bukkit.getWorlds().get(0), 128, 155, -47));
        Checkpoint secondCheckpoint = new Checkpoint(new Location(Bukkit.getWorlds().get(0), 128, 170, -47));
        Checkpoint thirdCheckpoint = new Checkpoint(new Location(Bukkit.getWorlds().get(0), 128, 175, -34));
        Checkpoint fourthCheckpoint = new Checkpoint(new Location(Bukkit.getWorlds().get(0), 115, 186, -34));
        Checkpoint fifthCheckpoint = new Checkpoint(new Location(Bukkit.getWorlds().get(0), 128, 190, -40));
        Checkpoint sixthCheckpoint = new Checkpoint(new Location(Bukkit.getWorlds().get(0), 129, 198, -40));
        Checkpoint seventhCheckpoint = new Checkpoint(new Location(Bukkit.getWorlds().get(0), 120, 203, -46));
        Checkpoint eighthCheckpoint = new Checkpoint(new Location(Bukkit.getWorlds().get(0), 128, 206, -35));
        Checkpoint ninthCheckpoint = new Checkpoint(new Location(Bukkit.getWorlds().get(0), 123, 211, -34));
        Checkpoint finalCheckpoint = new Checkpoint(new Location(Bukkit.getWorlds().get(0), 122, 213, -40));
        
        JumpHandler jumpHandler = new JumpHandler( 
            checkpoint, 
            secondCheckpoint,
            thirdCheckpoint, 
            fourthCheckpoint, 
            fifthCheckpoint, 
            sixthCheckpoint,
            seventhCheckpoint,
            eighthCheckpoint,
            ninthCheckpoint,
            finalCheckpoint            
        );

        getServer().getPluginManager().registerEvents(jumpHandler, this);

        System.out.println("One Night City is starting...");

        for (Player player : Bukkit.getOnlinePlayers()) {
            // Téléportez chaque joueur à la position spécifiée et définissez son mode de jeu sur l'aventure
            player.teleport(new Location(player.getWorld(), 121, 154, -40));
            player.setGameMode(GameMode.ADVENTURE);

            // Vérifiez si le jeu n'a pas encore commencé
            // Vérifiez si le jeu n'a pas encore commencé
            if (!ONCGame.getInstance().isStarted()) {
                // Vérifiez si le joueur n'a pas de boules de neige
                Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
                    // Parcourez chaque joueur en ligne
                    if (!ONCGame.getInstance().isStarted()) {

                        for (Player player1 : Bukkit.getOnlinePlayers()) {
                            boolean hasSnowball = false;
                            for (ItemStack item : player1.getInventory().getContents()) {
                                if (item != null && item.getType() == Material.SNOWBALL) {
                                    hasSnowball = true;
                                    break;
                                }
                            }

                            // Si le joueur n'a pas de boules de neige, donnez-lui une boule de neige
                            if (!hasSnowball) {

                                ItemStack snowball = new ItemStack(Material.SNOWBALL, 1); // Créez une boule de neige
                                player1.getInventory().addItem(snowball); // Donnez-la au joueur
                                player1.updateInventory(); // Mettez à jour l'inventaire du joueur
                            }
                        }
                    }
                }, 0L, 50); // Planifiez la tâche toutes les 60 secondes (20 ticks par seconde)
            }

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
