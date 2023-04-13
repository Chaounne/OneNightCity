package me.chaounne.onenightcity.game;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {

    private ArrayList<Player> players;

    private int score;

    public Team() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
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
}
