package me.chaounne.onenightcity.game;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class Spawner {

    private final Material material;
    private final Location location;
    private int taskId;

    public Spawner(Material material, Location location) {
        this.location = location;
        this.material = material;
        this.scheduleSpawn();
    }

	public Material getMaterial() {
		return material;
	}

    public Location getLocation() {
        return location;
	}

    private void scheduleSpawn() {
        BlockState blockState = this.location.getBlock().getState();
        Barrel barrel = (Barrel) blockState;
        Inventory inv = barrel.getInventory();
        ItemStack oreToSpawn = new ItemStack(this.material);
        this.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(),
                () -> inv.addItem(oreToSpawn), 20, 20);
    }

    private void unScheduleSpawn() {
        Bukkit.getScheduler().cancelTask(this.taskId);
    }

    public boolean checkIfBroken(Location brockenBlockLocation) {
        if (brockenBlockLocation == this.location) {
            this.unScheduleSpawn();
            return true;
        }
        return false;
    }

    public static ItemStack getIronSpawner() {
        return new ItemBuilder(Material.BARREL).name(ChatColor.GRAY + "Spawner de fer")
                .enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

    public static ItemStack getGoldSpawner() {
        return new ItemBuilder(Material.BARREL).name(ChatColor.GOLD + "Spawner d'or")
                .enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

    public static ItemStack getDiamondSpawner() {
        return new ItemBuilder(Material.BARREL).name(ChatColor.BLUE + "Spawner de diamant")
                .enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

    public static ItemStack getEmeraldSpawner() {
        return new ItemBuilder(Material.BARREL).name(ChatColor.GREEN + "Spawner d'émeraude")
                .enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

}