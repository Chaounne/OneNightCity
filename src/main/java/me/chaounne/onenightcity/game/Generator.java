package me.chaounne.onenightcity.game;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class Generator {

    private final Material material;
    private final Location location;
    private int taskId;

    public Generator(Material material, Location location) {
        this.location = location;
        this.material = material;
        scheduleGeneration();
    }

    public Location getLocation() {
        return location;
    }

    private void scheduleGeneration() {
        Barrel barrel = (Barrel) location.getBlock().getState();
        Inventory inv = barrel.getInventory();
        ItemStack item = new ItemStack(material);
        int period = 20;
        if (material == Material.EMERALD)
            period = 40;
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(),
                () -> inv.addItem(item), period, period);
    }

    public void unScheduleGeneration() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    public static ItemStack getIronGenerator() {
        return new ItemBuilder(Material.BARREL).name(ChatColor.GRAY + "Générateur de fer")
                .enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

    public static ItemStack getGoldGenerator() {
        return new ItemBuilder(Material.BARREL).name(ChatColor.GOLD + "Générateur d'or")
                .enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

    public static ItemStack getDiamondGenerator() {
        return new ItemBuilder(Material.BARREL).name(ChatColor.BLUE + "Générateur de diamant")
                .enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

    public static ItemStack getEmeraldGenerator() {
        return new ItemBuilder(Material.BARREL).name(ChatColor.GREEN + "Générateur d'émeraude")
                .enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

}