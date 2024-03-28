package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class Jeaneau extends Trader {

    private static Trader instance;

    public Jeaneau(Location loc) {
        super(loc, "Jeaneau", Villager.Type.PLAINS, Villager.Profession.FISHERMAN);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.PRISMARINE, new Integer[] {Random.between(1, 5), Random.between(85, 150)});
        items.put(Material.PRISMARINE_SHARD, new Integer[] {Random.between(1, 5), Random.between(85, 150)});
        items.put(Material.PRISMARINE_BRICKS, new Integer[] {Random.between(1, 5), Random.between(85, 150)});
        items.put(Material.PRISMARINE_CRYSTALS, new Integer[] {Random.between(1, 5), Random.between(85, 150)});
        items.put(Material.PRISMARINE_BRICK_SLAB, new Integer[] {Random.between(1, 5), Random.between(85, 150)});
        items.put(Material.PRISMARINE_BRICK_STAIRS, new Integer[] {Random.between(1, 5), Random.between(85, 150)});
        items.put(Material.PRISMARINE_SLAB, new Integer[] {Random.between(1, 5), Random.between(85, 150)});
        items.put(Material.PRISMARINE_STAIRS, new Integer[] {Random.between(1, 5), Random.between(85, 150)});
        items.put(Material.PRISMARINE_WALL, new Integer[] {Random.between(1, 5), Random.between(85, 150)});
        items.put(Material.SPONGE, new Integer[] {Random.between(1, 2), Random.between(1000, 3500)});

        return items;
    }

}