package me.chaounne.onenightcity.game;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GamePlayer {

    private static final Map<UUID, GamePlayer> players = new HashMap<>();

    private final Player player;

    private Team team;

    private int score = 0;

    private GamePlayer(Player player) {
        this.player = player;
    }

    public static GamePlayer getInstance(Player player) {
        UUID playerUUID = player.getUniqueId();
        if (!players.containsKey(playerUUID)) {
            players.put(playerUUID, new GamePlayer(player));
        }
        return players.get(playerUUID);
    }

    public Player getPlayer() {
        return player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void removeTeam() {
        this.team = null;
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
