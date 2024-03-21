package me.chaounne.onenightcity.game;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GamePlayer {

    private static final Map<UUID, GamePlayer> players = new HashMap<>();

    private final Player player;

    private GameTeam team;

    private int score;

    public GamePlayer(Player player) {
        this.player = player;
    }


    public static GamePlayer getInstance(Player player) {
        UUID playerUUID = player.getUniqueId();
        if (!players.containsKey(playerUUID))
            players.put(playerUUID, new GamePlayer(player));
        return players.get(playerUUID);
    }

    public Player getPlayer() {
        return player;
    }

    public GameTeam getTeam() {
        return team;
    }

    public void setTeam(GameTeam team) {
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
    public int getKills() {
        return player.getStatistic(Statistic.PLAYER_KILLS);
    }
    public int resetKills() {
        if (player != null) {
            player.setStatistic(Statistic.PLAYER_KILLS, 0);
            return 0;
        } else {
            return -1;
        }
    }
    public int getDeaths(){
        if (player != null) {
            player.getStatistic(Statistic.DEATHS);
            return 0;
        } else {
            return -1;
        }
    }
}