package me.chaounne.onenightcity.game.jump;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.chaounne.onenightcity.utils.JumpScore;

public class JumpManager {
    
    private Player player;
    private boolean hasWool, hasEmerald;
    private long startTime;
    private long completeTime;
    private Checkpoint currentCheckpoint;
    private int CheckpointIndex;
    boolean finished;

    public JumpManager(Player p, Checkpoint cp) {
        this.player = p;
        this.currentCheckpoint = cp;
        this.startJump();
        this.hasWool = false;
        this.hasEmerald = false;
    }

    public boolean isFinished() {
		return finished;
	}

	public int getCheckpointIndex() {
        return this.CheckpointIndex;
    }

    public void setCheckpointIndex(int index) {
        this.CheckpointIndex = index;
    }

    public long getTime() {
        return this.completeTime;
    }

    private void startJump() {
        this.startTime = System.nanoTime();

        if (!this.hasWool) {
            player.getInventory().addItem(createOrangeWool());
            player.getInventory().addItem(createRedWool());
            this.hasWool = true;
        }
        this.finished = false;
        this.CheckpointIndex = 0;
        this.player.sendMessage(ChatColor.GREEN + "Tu commences le jump ! Bonne chance ! :)");
    }

    public void setCheckpoint(Checkpoint cp) {
        this.currentCheckpoint = cp;
        this.player.sendMessage(ChatColor.YELLOW + "Nouveau checkpoint ! GG :)");
    }

    public void teleport(boolean msg) {
        this.player.teleport(this.currentCheckpoint.getLocation());
        if (msg) this.player.sendMessage(ChatColor.GOLD + "Retour au dernier checkpoint");
    }

    public void restart() {
        this.removeRestartEmerald();
        this.hasEmerald = false;
        this.startJump();
    }

    public void deactivate(boolean msg) {
        this.removeWools();
        this.removeRestartEmerald();
        this.hasWool = false;
        this.hasEmerald = false;
        this.player.teleport(new Location(Bukkit.getWorlds().get(0), 122, 154, -40));
        if (msg) this.player.sendMessage(ChatColor.RED + "Tu abandonnes le jump. :(");
    }

    public void finish() {
        this.completeTime = System.nanoTime() - this.startTime;
        this.finished = true;
        this.removeWools();
        this.hasWool = false;
        if (!this.hasEmerald) {
            player.getInventory().addItem(this.createRestartEmerald());
            this.hasEmerald = true;
        }
        this.player.sendMessage(ChatColor.AQUA + "Tu as fini le jump en " + JumpScore.formatJumpTime(completeTime) + " ! Tu es une légende ! :)");
    }

    private ItemStack createOrangeWool() {
        ItemStack orangeWool = new ItemStack(Material.ORANGE_WOOL);
        ItemMeta orangeMeta = orangeWool.getItemMeta();
        orangeMeta.setDisplayName(ChatColor.GOLD + "Revenir au checkpoint");
        orangeWool.setItemMeta(orangeMeta);
        return orangeWool;
    }

    private ItemStack createRedWool() {
        ItemStack redWool = new ItemStack(Material.RED_WOOL);
        ItemMeta redMeta = redWool.getItemMeta();
        redMeta.setDisplayName(ChatColor.RED + "Abandonner");
        redWool.setItemMeta(redMeta);
        return redWool;
    }

    private void removeWools() {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && (item.getType() == Material.ORANGE_WOOL || item.getType() == Material.RED_WOOL)) {
                item.setAmount(0);
            }
        }
    }

    private ItemStack createRestartEmerald() {
        ItemStack restartEmerald = new ItemStack(Material.EMERALD);
        ItemMeta restartEmeraldMeta = restartEmerald.getItemMeta();
        restartEmeraldMeta.setDisplayName(ChatColor.GREEN + "Recommencer");
        restartEmerald.setItemMeta(restartEmeraldMeta);
        return restartEmerald;
    }

    private void removeRestartEmerald() {
        ItemStack restartEmerald = createRestartEmerald();
        player.getInventory().removeItem(restartEmerald);
    }

}
