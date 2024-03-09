package me.chaounne.onenightcity.villager.spawners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.chaounne.onenightcity.OneNightCity;

public class Spawner {

    private Material material;
    private Location pos;
    private int taskId;

    public Spawner(Material mat, Location pos) {
        this.pos = pos;
        this.material = mat;
        this.scheduleSpawn();
    }

	public Material getMaterial() {
		return material;
	}

	public Location getPos() {
		return pos;
	}

    private void scheduleSpawn() {
        BlockState blockState = this.pos.getBlock().getState();
        Barrel barrel = (Barrel) blockState;
        Inventory inv = barrel.getInventory();
        ItemStack oreToSpawn = new ItemStack(this.material);
        this.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
            inv.addItem(oreToSpawn);
        }, 20, 20);
    }

    private void unScheduleSpawn() {
        Bukkit.getScheduler().cancelTask(this.taskId);
    }

    public boolean checkIfBroken(Location brockenBlockLocation) {
        if(brockenBlockLocation == this.pos) {
            this.unScheduleSpawn();
            return true;
        }
        return false;
    }

}
