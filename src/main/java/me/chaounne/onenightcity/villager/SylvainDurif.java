package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class SylvainDurif extends Trader {

    private static Trader instance;

    public SylvainDurif(Location loc) {
        super(loc, "Civique Cosmique Durif", Villager.Type.SWAMP, Villager.Profession.LEATHERWORKER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.CHORUS_FRUIT, new Integer[] {Random.between(1, 3), Random.between(15, 35)});
        items.put(Material.CHORUS_FLOWER, new Integer[] {Random.between(1, 3), Random.between(50, 150)});
        items.put(Material.PURPUR_BLOCK, new Integer[] {Random.between(1, 3), Random.between(50, 80)});
        items.put(Material.PURPUR_PILLAR, new Integer[] {Random.between(1, 3), Random.between(50, 80)});
        items.put(Material.PURPUR_SLAB, new Integer[] {Random.between(1, 3), Random.between(50, 60)});
        items.put(Material.PURPUR_STAIRS, new Integer[] {Random.between(1, 3), Random.between(50, 60)});
        items.put(Material.END_STONE_BRICKS, new Integer[] {Random.between(2, 5), Random.between(30, 30)});
        items.put(Material.END_STONE_BRICK_SLAB, new Integer[] {Random.between(2, 5), Random.between(30, 90)});
        items.put(Material.END_STONE_BRICK_STAIRS, new Integer[] {Random.between(2, 5), Random.between(30, 60)});
        items.put(Material.END_STONE_BRICK_WALL, new Integer[] {Random.between(2, 5), Random.between(30, 120)});
        items.put(Material.END_CRYSTAL, new Integer[] {Random.between(1, 1), Random.between(2500, 12000)});
        items.put(Material.END_ROD, new Integer[] {Random.between(1, 5), Random.between(25, 50)});
        items.put(Material.OBSIDIAN, new Integer[] {Random.between(1, 2), Random.between(100, 150)});
        items.put(Material.SHULKER_BOX, new Integer[] {Random.between(1, 1), Random.between(5000, 10000)});
        items.put(Material.ELYTRA, new Integer[] {Random.between(1, 1), Random.between(15000, 25000)});
        items.put(Material.ENDER_CHEST, new Integer[] {Random.between(1, 1), Random.between(2500, 5000)});

        return items;
    }

}