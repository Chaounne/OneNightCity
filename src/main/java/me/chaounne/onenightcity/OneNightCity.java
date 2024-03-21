package me.chaounne.onenightcity;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import fr.mrmicky.fastboard.FastBoard;
import fr.mrmicky.fastinv.FastInvManager;
import me.chaounne.onenightcity.commands.Commands;
import me.chaounne.onenightcity.commands.Completer;
import me.chaounne.onenightcity.events.Handler;
import me.chaounne.onenightcity.events.JumpHandler;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.jump.Checkpoint;
import org.bukkit.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

public final class OneNightCity extends JavaPlugin {

    private JumpHandler jumpHandler;

    public static OneNightCity getInstance() {
        return getPlugin(OneNightCity.class);
    }

    public JumpHandler getJumpHandler() {
        return jumpHandler;
    }

    @Override
    public void onEnable() {
        for (World world : Bukkit.getWorlds())
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);

        World world = Bukkit.getWorlds().get(0);
        world.setGameRule(GameRule.FALL_DAMAGE, false);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setTime(0);

        // supprime les villageois à 100 blocks de rayon du spawn
        Location spawn_loc = new Location(world, 0, 70, 0);
        for (Entity entity : world.getEntities()) {
            if (entity.getType() == EntityType.VILLAGER) {
                if (entity.getLocation().distance(spawn_loc) <= 100)
                    entity.remove();
            }
        }

        Bukkit.getOnlinePlayers().forEach(player -> player.getAdvancementProgress(Bukkit.getAdvancement(NamespacedKey.minecraft("story/root"))).awardCriteria("impossible"));
        ONCGame.getInstance().resetTeams();
        FastInvManager.register(this);

        Hologram welcome = DHAPI.createHologram("Bienvenue", new Location(world, 125, 156, -33));
        DHAPI.addHologramLine(welcome, "Bienvenue sur OneNightCity");
        DHAPI.addHologramLine(welcome, "fait par Chaounne, Lucastrap09 et S0meB0dy_");
        DHAPI.addHologramLine(welcome, "et build par Mazlai et Lucastrap09");

        Hologram rules1 = DHAPI.createHologram("Regle", new Location(world, 121, 157, -33));
        DHAPI.addHologramLine(rules1, "Règle du jeu : il faut être l'équipe");
        DHAPI.addHologramLine(rules1, "ayant le plus de poudre à la fin");
        DHAPI.addHologramLine(rules1, "du temps imparti : 3 heures");

        Hologram spawn = DHAPI.createHologram("Spawn", new Location(world, 114, 157, -33));
        DHAPI.addHologramLine(spawn, "Voici le spawn, il dispose d'un marché");
        DHAPI.addHologramLine(spawn, "ou vous pourrez échanger vos ressources");
        DHAPI.addHologramLine(spawn, "contre des poudres magiques. À vous de vous");
        DHAPI.addHologramLine(spawn, "organisez pour en avoir le plus.");

        Hologram rules2 = DHAPI.createHologram("Regle2", new Location(world, 114, 156, -39));
        DHAPI.addHologramLine(rules2, "PVP activé après 10 min de jeu.");
        DHAPI.addHologramLine(rules2, "Le spawn est le seul endroit protégé.");
        DHAPI.addHologramLine(rules2, "Créez votre base, pillez les autres, entretuez-vous !");
        DHAPI.addHologramLine(rules2, "Aucun cheat autorisé, même pas X-RAY.");

        Hologram details = DHAPI.createHologram("details", new Location(world, 114, 157, -48));
        DHAPI.addHologramLine(details, "Concernant l'item spécial : la Poudre .");
        DHAPI.addHologramLine(details, "Vous pouvez en avoir en tradant des items au spawn.");
        DHAPI.addHologramLine(details, ChatColor.GOLD + "Il existe une " + ChatColor.BOLD + "SuperPoudre " + ChatColor.GOLD + "qui vaut" + ChatColor.BOLD + " 1000 Poudres.");
        DHAPI.addHologramLine(details, "Si vous rencontrez un bug, merci de nous le signaler.");
        DHAPI.addHologramLine(details, "Et pour finir amusez-vous bien et bon jeu ! GL HF");

        Hologram news = DHAPI.createHologram("nouveautes", new Location(world, 123, 158, -49));
        DHAPI.addHologramLine(news, ChatColor.RED + "NOUVEAUTÉS");
        DHAPI.addHologramLine(news, "Fix des bugs de la derniere game!");
        DHAPI.addHologramLine(news, "Rework des générateurs de minerais");
        DHAPI.addHologramLine(news, "Rework du système de jump au lobby");
        DHAPI.addHologramLine(news, "Mise à jour de la safe zone (+ de pvp au spawn)");
        DHAPI.addHologramLine(news, "Plus d'info dans le guide sur discord ! Amusez-vous bien !");

        Hologram help = DHAPI.createHologram("aide", new Location(world, 125, 157, -40));
        DHAPI.addHologramLine(help, ChatColor.RED + "Création et gestion de team");
        DHAPI.addHologramLine(help, ChatColor.WHITE + "Pour créer une team : /city team create <team_name>");
        DHAPI.addHologramLine(help, ChatColor.WHITE + "Pour ajouter un joueur à votre équipe : /city team hire <player_name>");
        DHAPI.addHologramLine(help, ChatColor.WHITE + "Pour voir les membres de votre équipe : /city team members");
        DHAPI.addHologramLine(help, ChatColor.GREEN + "Amusez-vous bien !");

        Location leaderboardLoc = new Location(world, 118, 218, -36);
        DHAPI.createHologram("Classement", leaderboardLoc);

        jumpHandler = new JumpHandler(
                new Checkpoint(new Location(world, 128.5, 155, -46.5)),
                new Checkpoint(new Location(world, 128.5, 170, -46.5, 90, 0)),
                new Checkpoint(new Location(world, 128.5, 175, -33.5, 90, 0)),
                new Checkpoint(new Location(world, 115.5, 186, -33.5, -90, 0)),
                new Checkpoint(new Location(world, 128.5, 190, -39.5, 90, 0)),
                new Checkpoint(new Location(world, 129.5, 198, -39.5, -90, 0)),
                new Checkpoint(new Location(world, 120.5, 203, -45.5)),
                new Checkpoint(new Location(world, 128.5, 206, -34.5, 90, 0)),
                new Checkpoint(new Location(world, 123.5, 211, -33.5, 180, 0)),
                new Checkpoint(new Location(world, 122, 213, -40))
        );

        getServer().getPluginManager().registerEvents(jumpHandler, this);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.teleport(new Location(Bukkit.getWorlds().get(0), 122, 154, -39));
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
            player.updateInventory();
            FastBoard board = new FastBoard(player);
            board.delete();
            player.setExp(0);
            player.setLevel(0);
        }

        if (!ONCGame.getInstance().hasStarted()) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
                if (!ONCGame.getInstance().hasStarted()) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        boolean hasSnowball = false;
                        for (ItemStack item : player.getInventory().getContents()) {
                            if (item != null && item.getType() == Material.SNOWBALL) {
                                hasSnowball = true;
                                break;
                            }
                        }
                        if (!hasSnowball) {
                            ItemStack snowball = new ItemStack(Material.SNOWBALL, 1);
                            player.getInventory().addItem(snowball);
                            player.updateInventory();
                        }
                    }
                }
            }, 0, 50);
        }

        CommandExecutor cmd = new Commands();
        TabCompleter tab = new Completer();

        getCommand("city").setExecutor(cmd);
        getCommand("city").setTabCompleter(tab);
        getPlugin(OneNightCity.class).getServer().getPluginManager().registerEvents(new Handler(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getScoreboardManager().getMainScoreboard().getTeams().forEach(Team::unregister);
    }

}