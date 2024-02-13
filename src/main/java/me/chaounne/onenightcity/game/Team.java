package me.chaounne.onenightcity.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {

    private ArrayList<Player> players;
    private int score;
    private String name;
    private org.bukkit.scoreboard.Team scoreboardTeam;
    private Player leader;

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

    public void setLeader(Player player){
        leader = player;
    }

    public Player getLeader(){
        return leader;
    }

    public ChatColor getColor(){
        return color;
    }

    public void addPlayer(Player player) {
        players.add(player);
        scoreboardTeam.addEntry(player.getName());
    }

    public int getPlayersSize() {
        return players.size();
    }

    public void removePlayer(Player player) {
        if(leader == player)
            leader = null;
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

    public void reset(){
        // Supprimer l'équipe du scoreboard

        // Retirer les joueurs de l'équipe et réinitialiser leurs références
        for(Player player : players){
            scoreboardTeam.removeEntry(player.getName());
            GamePlayer.getInstance(player).setTeam(null);
        }
        scoreboardTeam.unregister();


        // Réinitialiser les autres attributs de l'équipe
        players.clear();
        score = 0;
        leader = null;
    }
}
