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

    private int bounty = 0;

    private boolean isBounty = false;

    private GamePlayer bet = null;

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

    public boolean hasBounty() {
        return isBounty;
    }

    public void setBounty(int bounty) {
        this.bounty = bounty;
        this.isBounty = true;
    }

    public void removeBounty() {
        this.bounty = 0;
        this.isBounty = false;
    }

    public int getBounty() {
        return bounty;
    }

    public GamePlayer getBeter() {
        return bet;
    }

    public void setBeter(GamePlayer bet) {
        this.bet = bet;
    }

    public void removeBeter() {
        this.bet = null;
    }

}
