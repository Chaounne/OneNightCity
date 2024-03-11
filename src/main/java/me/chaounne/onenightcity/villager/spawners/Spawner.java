package me.chaounne.onenightcity.villager.spawners;

import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.Inventory;
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

}