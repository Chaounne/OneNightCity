package me.chaounne.onenightcity.game;

import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {

    private ArrayList<Player> players;
    private int score;
    private String name;
    private org.bukkit.scoreboard.Team scoreboardTeam;

    private ChatColor color;

    public Team(String name) {
        players = new ArrayList<>();
        score = 0;
        this.name = name;
        scoreboardTeam = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(name);
        scoreboardTeam.setAllowFriendlyFire(false);
        scoreboardTeam.setCanSeeFriendlyInvisibles(true);
    }

    public org.bukkit.scoreboard.Team getScoreboardTeam() {
        return scoreboardTeam;
    }

    public void setColor(ChatColor color){
        scoreboardTeam.setPrefix(color.toString());
        this.color = color;
    }

    public ChatColor getColor(){
        return color;
    }

    public void addPlayer(Player player) {
        players.add(player);
        scoreboardTeam.addEntry(player.getName());
    }

    public void removePlayer(Player player) {
        players.remove(player);
        scoreboardTeam.removeEntry(player.getName());
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setScore(int score) {
        if(score < 0)
            this.score = 0;
        else
            this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void removeScore(int score) {
        if(this.score - score >= 0){
            this.score -= score;
        } else {
            this.score = 0;
        }
    }

    public String getName() {
        return name;
    }
}
