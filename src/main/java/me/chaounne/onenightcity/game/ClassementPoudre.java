package me.chaounne.onenightcity.game;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Comparator;

public class ClassementPoudre {

    private static final ONCGame game = ONCGame.getInstance();

    public static void showScoreboard() {
        // Trier les équipes par score décroissant
        ArrayList<Team> teams = game.getTeams();
        teams.sort(Comparator.comparingInt(Team::getScore).reversed());
        DHAPI.removeHologram("Classement");

        // Créer un hologramme pour afficher le classement
        Location hologramLocation = new Location(Bukkit.getWorlds().get(0), -1.5, 72.5, -1.5);
        Hologram scoreboardHologram = DHAPI.createHologram("Classement", hologramLocation);
        String title = ChatColor.RED + "C" + ChatColor.GOLD + "l" + ChatColor.YELLOW + "a" + ChatColor.GREEN + "s" + ChatColor.AQUA + "s" + ChatColor.BLUE + "e" + ChatColor.DARK_BLUE + "m" + ChatColor.DARK_PURPLE + "e" + ChatColor.LIGHT_PURPLE + "n" + ChatColor.RED + "t" + ChatColor.GOLD + " " + ChatColor.YELLOW + "d" + ChatColor.GREEN + "e" + ChatColor.AQUA + "s" + ChatColor.BLUE + " " + ChatColor.DARK_BLUE + "t" + ChatColor.DARK_PURPLE + "e" + ChatColor.LIGHT_PURPLE + "a" + ChatColor.RED + "m" + ChatColor.GOLD + "s" + ChatColor.YELLOW + " :";
        DHAPI.addHologramLine(scoreboardHologram, title);

        // Ajouter une ligne de texte pour chaque équipe, avec les 3 premières équipes en couleur
        for (int i = 0; i < teams.size(); i++) {
            ChatColor teamColor;
            if (i == 0)
                teamColor = ChatColor.GOLD;
            else if (i == 1)
                teamColor = ChatColor.GRAY;
            else if (i == 2)
                teamColor = ChatColor.DARK_RED;
            else
                teamColor = teams.get(i).getColor();

            String teamName = teams.get(i).getName();
            String formattedTeamName = teamColor + (i == 0 ? "1er " : "") + (i == 1 ? "2eme " : "") +(i == 2 ? "3eme " : "") + teamName;String hologramText = formattedTeamName + " : " + teams.get(i).getScore() + " poudres";
            DHAPI.addHologramLine(scoreboardHologram, hologramText);
        }

        // Afficher l'hologramme pour tous les joueurs
       scoreboardHologram.showAll();

        // Supprimer l'hologramme après 20 secondes
        Bukkit.getScheduler().runTaskLater(OneNightCity.getInstance(), () -> {
            if (game.hasStarted())
                scoreboardHologram.delete();
            if (!game.hasStarted())
                DHAPI.moveHologram(scoreboardHologram, new Location(Bukkit.getWorlds().get(0),122,157,-37));
        }, 40L);
    }

}