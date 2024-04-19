package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class DrRaoult extends Trader {

    private static Trader instance;

    public DrRaoult(Location loc) {
        super(loc, "Dr. Raoult", Villager.Type.DESERT, Villager.Profession.CLERIC);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.SPIDER_EYE, new Integer[] {Random.between(1, 5), Random.between(50, 250)});
        items.put(Material.ROTTEN_FLESH, new Integer[] {Random.between(1, 5), Random.between(10, 50)});
        items.put(Material.BONE, new Integer[] {Random.between(1, 5), Random.between(10, 50)});
        items.put(Material.BONE_BLOCK, new Integer[] {Random.between(1, 5), Random.between(10, 75)});
        items.put(Material.BONE_MEAL, new Integer[] {Random.between(1, 5), Random.between(1, 25)});
        items.put(Material.GUNPOWDER, new Integer[] {Random.between(1, 5), Random.between(1, 50)});
        items.put(Material.RABBIT_FOOT, new Integer[] {Random.between(1, 1), Random.between(500, 2000)});
        items.put(Material.LEATHER, new Integer[] {Random.between(1, 5), Random.between(10, 100)});
        items.put(Material.RABBIT_HIDE, new Integer[] {Random.between(1, 5), Random.between(10, 50)});
        items.put(Material.SLIME_BALL, new Integer[] {Random.between(1, 3), Random.between(100, 1000)});
        items.put(Material.SCUTE, new Integer[] {Random.between(1, 1), Random.between(5000, 10000)});
        items.put(Material.WITHER_SKELETON_SKULL, new Integer[] {Random.between(1, 1), Random.between(4000, 8000)});
        items.put(Material.WITHER_ROSE, new Integer[] {Random.between(1, 1), Random.between(2500, 5000)});
        items.put(Material.BEACON, new Integer[] {Random.between(1, 1), Random.between(15000, 25000)});

        return items;
    }

}