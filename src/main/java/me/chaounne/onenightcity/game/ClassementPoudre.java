package me.chaounne.onenightcity.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.api.holograms.HologramLine;
import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ClassementPoudre {
    private Hologram hologram;
    private static ONCGame game = ONCGame.getInstance();

    public ClassementPoudre() {
    }

    public static void showScoreboard() {
        // Envoyer un message à tous les joueurs en ligne


        // Trier les équipes par score décroissant
        ArrayList<Team> teams = game.getTeams();
        teams.sort(Comparator.comparingInt(Team::getScore).reversed());

        // Créer un hologramme pour afficher le classement
        Location hologramLocation = new Location(Bukkit.getWorlds().get(0), -1.5, 72.5, -1.5);
       // Hologram scoreboardHologram = DHAPI.createHologram("Classement", hologramLocation);
        String title = ChatColor.RED + "C" + ChatColor.GOLD + "l" + ChatColor.YELLOW + "a" + ChatColor.GREEN + "s" + ChatColor.AQUA + "s" + ChatColor.BLUE + "e" + ChatColor.DARK_BLUE + "m" + ChatColor.DARK_PURPLE + "e" + ChatColor.LIGHT_PURPLE + "n" + ChatColor.RED + "t" + ChatColor.GOLD + " " + ChatColor.YELLOW + "d" + ChatColor.GREEN + "e" + ChatColor.AQUA + "s" + ChatColor.BLUE + " " + ChatColor.DARK_BLUE + "t" + ChatColor.DARK_PURPLE + "e" + ChatColor.LIGHT_PURPLE + "a" + ChatColor.RED + "m" + ChatColor.GOLD + "s" + ChatColor.YELLOW + " :";
       // DHAPI.addHologramLine(scoreboardHologram, title);

        // Ajouter une ligne de texte pour chaque équipe, avec les 3 premières équipes en couleur
        for (int i = 0; i < teams.size(); i++) {
            ChatColor teamColor;
            if (i == 0) {
                teamColor = ChatColor.GOLD;
            } else if (i == 1) {
                teamColor = ChatColor.GRAY;
            } else if (i == 2) {
                teamColor = ChatColor.DARK_RED;
            } else {
                teamColor = teams.get(i).getColor();
            }
            String teamName = teams.get(i).getName();
            String formattedTeamName = teamColor + (i == 0 ? "1er " : "") + (i == 1 ? "2eme " : "") +(i == 2 ? "3eme " : "") + teamName;String hologramText = formattedTeamName + " : " + teams.get(i).getScore() + " poudres";
        //    DHAPI.addHologramLine(scoreboardHologram, hologramText);
        }

        // Afficher l'hologramme pour tous les joueurs
      //  scoreboardHologram.showAll();

        // Supprimer l'hologramme après 20 secondes
        Bukkit.getScheduler().runTaskLater(OneNightCity.getInstance(), new Runnable() {
            @Override
            public void run() {
          //      if(game.isStarted()==true){scoreboardHologram.delete();}
          //      if(game.isStarted()==true){scoreboardHologram.delete();}
            }
        }, 40L);
    }


    public void hideHologram() {
        hologram.delete();
    }
}
