package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class FrancisClodo extends Trader {

    private static Trader instance;

    public FrancisClodo(Location loc) {
        super(loc, "Francis Clodo", Villager.Type.SWAMP, Villager.Profession.BUTCHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.GLASS_BOTTLE, new Integer[] {Random.between(1, 3), Random.between(25, 50)});
        items.put(Material.HONEY_BOTTLE, new Integer[] {Random.between(1, 3), Random.between(50, 500)});
        items.put(Material.BEETROOT_SOUP, new Integer[] {Random.between(1, 1), Random.between(1000, 2000)});
        items.put(Material.ENCHANTING_TABLE, new Integer[] {Random.between(1, 2), Random.between(750, 1500)});
        items.put(Material.GHAST_TEAR, new Integer[] {Random.between(1, 1), Random.between(2000, 4000)});
        items.put(Material.HEART_OF_THE_SEA, new Integer[] {Random.between(1, 1), Random.between(5000, 10000)});
        items.put(Material.NAME_TAG, new Integer[] {Random.between(1, 1), Random.between(3000, 5000)});
        items.put(Material.SADDLE, new Integer[] {Random.between(1, 1), Random.between(4000, 8000)});
        items.put(Material.DIAMOND_HORSE_ARMOR, new Integer[] {Random.between(1, 1), Random.between(2000, 4000)});
        items.put(Material.GOLDEN_HORSE_ARMOR, new Integer[] {Random.between(1, 1), Random.between(1500, 3000)});
        items.put(Material.IRON_HORSE_ARMOR, new Integer[] {Random.between(1, 1), Random.between(1000, 2000)});
        items.put(Material.CHAINMAIL_HELMET, new Integer[] {Random.between(1, 1), Random.between(1500, 3000)});
        items.put(Material.CHAINMAIL_CHESTPLATE, new Integer[] {Random.between(1, 1), Random.between(1500, 3000)});
        items.put(Material.CHAINMAIL_LEGGINGS, new Integer[] {Random.between(1, 1), Random.between(1500, 3000)});
        items.put(Material.CHAINMAIL_BOOTS, new Integer[] {Random.between(1, 1), Random.between(1500, 3000)});
        items.put(Material.ENCHANTED_GOLDEN_APPLE, new Integer[] {Random.between(1, 1), Random.between(10000, 25000)});
        items.put(Material.LAVA_BUCKET, new Integer[] {Random.between(1, 1), Random.between(250, 500)});
        items.put(Material.TRIDENT, new Integer[] {Random.between(1, 1), Random.between(10000, 20000)});

        return items;
    }

}