package me.chaounne.onenightcity.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.jump.Checkpoint;
import me.chaounne.onenightcity.game.jump.JumpManager;
import me.chaounne.onenightcity.utils.JumpScore;

public class JumpHandler implements Listener {

    private HashMap<Player, Long> bestScores;
    private List<Checkpoint> checkpoints;
    private HashMap<Player, JumpManager> playerManagers;

    public JumpHandler(Checkpoint... cps) {
        this.checkpoints = new ArrayList<Checkpoint>();
        for (Checkpoint cp : cps) {
            this.checkpoints.add(cp);
        }
        this.playerManagers = new HashMap<Player, JumpManager>();
        this.bestScores = new HashMap<Player, Long>();

        this.updateHologram();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!ONCGame.getInstance().isStarted()) {

            Player player = event.getPlayer();
            ItemStack item = event.getItem();
            Block clickedBlock = event.getClickedBlock();

            if (clickedBlock != null) {
                if (checkCheckpoint(clickedBlock, this.checkpoints.get(0))) {
                    if (!this.playerManagers.containsKey(player)) {
                        this.playerManagers.put(player, new JumpManager(player, this.checkpoints.get(0)));
                    }
                } 
                else if (checkLastCheckpoint(clickedBlock, this.checkpoints.get(this.checkpoints.size() - 1))) {
                    if (!this.playerManagers.containsKey(player)) return;
                    if (!this.playerManagers.get(player).isFinished()) {
                        this.playerManagers.get(player).finish();
                        if (this.bestScores.containsKey(player)) {
                            if (this.bestScores.get(player) > this.playerManagers.get(player).getTime()) {
                                this.bestScores.put(player, this.playerManagers.get(player).getTime());
                            }
                        } else {
                            this.bestScores.put(player, this.playerManagers.get(player).getTime());
                        }
                        this.updateHologram();
                    }
                }
                else {
                    if (!this.playerManagers.containsKey(player)) return;
                    int size = this.checkpoints.size() - 1;
                    for (int i = this.playerManagers.get(player).getCheckpointIndex() + 1; i < size; i++) {
                        if (this.checkCheckpoint(clickedBlock, this.checkpoints.get(i))) {
                            this.playerManagers.get(player).setCheckpoint(this.checkpoints.get(i));
                            this.playerManagers.get(player).setCheckpointIndex(i);
                        }
                    }
                }
            }
            
            if (item != null && item.getType() == Material.RED_WOOL && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                this.deactivateJumpManagerForPlayer(player, true);
            }
            else if (item != null && item.getType() == Material.ORANGE_WOOL && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                this.playerManagers.get(player).teleport(true);
            } 
            else if (item != null && item.getType() == Material.EMERALD && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                this.deactivateJumpManagerForPlayer(player, false);
                this.playerManagers.put(player, new JumpManager(player, this.checkpoints.get(0)));
                this.playerManagers.get(player).teleport(false);
            }
        }
    }

    private boolean checkCheckpoint(Block clickedBlock, Checkpoint checkpoint) {
        return clickedBlock.getType().equals(Material.HEAVY_WEIGHTED_PRESSURE_PLATE) &&
                clickedBlock.getLocation().equals(checkpoint.getLocation());
    }

    private boolean checkLastCheckpoint(Block clickedBlock, Checkpoint checkpoint) {
        return clickedBlock.getType().equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE) &&
                clickedBlock.getLocation().equals(checkpoint.getLocation());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        deactivateJumpManagerForPlayer(player, false);
    }

    private void deactivateJumpManagerForPlayer(Player p, boolean msg) {
        if (this.playerManagers.containsKey(p)) {
            this.playerManagers.get(p).deactivate(msg);
            this.playerManagers.remove(p);
        }
    }

    private void updateHologram() {
        Hologram leaderboardHologram = DHAPI.getHologram("Classement");

        if (leaderboardHologram == null) {
            Location hologramLocation = new Location(Bukkit.getWorlds().get(0), 118, 218, -36);
            leaderboardHologram = DHAPI.createHologram("Classement", hologramLocation);
        } else {
            DHAPI.setHologramLines(leaderboardHologram, Collections.emptyList());
        }
        
        List<Map.Entry<Player, Long>> sortedTimes = this.bestScores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        int count = 1;
        for (Map.Entry<Player, Long> entry : sortedTimes.subList(0, Math.min(sortedTimes.size(), 10))) {
            String playerName = entry.getKey().getName();
            long time = entry.getValue();
            String formattedTime = ChatColor.YELLOW + "Top " + count + ": " + playerName + " - " + JumpScore.formatJumpTime(time);

            DHAPI.addHologramLine(leaderboardHologram, formattedTime);
            count++;
        }
    }

}