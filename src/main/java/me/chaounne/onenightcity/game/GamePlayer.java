package me.chaounne.onenightcity.game;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GamePlayer {

    private static final Map<UUID, GamePlayer> players = new HashMap<>();

    private final Player player;

    private Team team;

    private int score;

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

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void substractScore(int amount) {
        score = Math.max(score - amount, 0);
    }

}