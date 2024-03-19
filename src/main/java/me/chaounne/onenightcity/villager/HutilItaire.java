package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class HutilItaire extends Trader {

    private static Trader instance;

    public HutilItaire(Location loc) {
        super(loc, "Hutil Itaire", Villager.Type.PLAINS, Villager.Profession.CARTOGRAPHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.CRAFTING_TABLE, new Integer[] {Random.between(1, 3), Random.between(2, 20)});
        items.put(Material.STONECUTTER, new Integer[] {Random.between(1, 3), Random.between(20, 50)});
        items.put(Material.CARTOGRAPHY_TABLE, new Integer[] {Random.between(1, 3), Random.between(20, 50)});
        items.put(Material.FLETCHING_TABLE, new Integer[] {Random.between(1, 3), Random.between(20, 50)});
        items.put(Material.SMITHING_TABLE, new Integer[] {Random.between(1, 3), Random.between(20, 50)});
        items.put(Material.GRINDSTONE, new Integer[] {Random.between(1, 3), Random.between(20, 50)});
        items.put(Material.LOOM, new Integer[] {Random.between(1, 3), Random.between(20, 50)});
        items.put(Material.FURNACE, new Integer[] {Random.between(1, 3), Random.between(20, 50)});
        items.put(Material.SMOKER, new Integer[] {Random.between(1, 3), Random.between(20, 50)});
        items.put(Material.BLAST_FURNACE, new Integer[] {Random.between(1, 3), Random.between(20, 50)});
        items.put(Material.NOTE_BLOCK, new Integer[] {Random.between(1, 3), Random.between(20, 50)});
        items.put(Material.JUKEBOX, new Integer[] {Random.between(1, 3), Random.between(70, 500)});

        return items;
    }

}