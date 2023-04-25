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
        for(Player players : Bukkit.getOnlinePlayers()){
            players.sendMessage("pendant 1");
        }
        // Trier les équipes par score décroissant
        ArrayList<Team> teams = game.getTeams();
        teams.sort(Comparator.comparingInt(Team::getScore).reversed());

        // Créer un hologramme
        Location location = new Location(Bukkit.getWorlds().get(0), 0, 72, 0);
        Hologram hologram = DHAPI.createHologram("Classement", location);
        for(Player players : Bukkit.getOnlinePlayers()){
            players.sendMessage(String.valueOf(teams.size()));
        }
        // Ajouter une ligne de texte pour chaque équipe
        for (Team team : teams) {
            ChatColor teamColor = team.getColor();
            String teamName = team.getName();
            // Utiliser des codes de couleurs pour afficher le nom de l'équipe dans sa couleur
            String formattedTeamName = teamColor + teamName;
            // Afficher le score de l'équipe à côté de son nom
            String hologramText = formattedTeamName + " : " + team.getScore() + " poudres";
            DHAPI.addHologramLine(hologram, hologramText);

            // Ajouter un remplaçant de texte pour mettre à jour le score de l'équipe
            team.getScoreboardTeam().setSuffix(" : " + team.getScore() + " poudres");
        }

        hologram.setLocation(location);
        hologram.showAll();
        hologram.enable();


        Hologram hologram2 = DHAPI.createHologram("name", location);

        DHAPI.addHologramLine(hologram, "Line content");
        hologram2.setLocation(location);
        hologram2.showAll();
        hologram2.enable();

        // Mettre à jour l'hologramme toutes les 10 secondes
        for(Player players : Bukkit.getOnlinePlayers()){
            players.sendMessage("pendant 2");
        }
    }

    public void hideHologram() {
        hologram.delete();
    }
}
