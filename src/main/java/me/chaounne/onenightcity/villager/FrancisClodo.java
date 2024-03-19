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

        items.put(Material.GLASS_BOTTLE, new Integer[] {Random.between(1, 10), Random.between(5, 20)});
        items.put(Material.HONEY_BOTTLE, new Integer[] {Random.between(1, 5), Random.between(15, 30)});
        items.put(Material.MILK_BUCKET, new Integer[] {Random.between(1, 1), Random.between(20, 250)});
        items.put(Material.BEETROOT_SOUP, new Integer[] {Random.between(1, 1), Random.between(50, 200)});
        items.put(Material.MUSHROOM_STEW, new Integer[] {Random.between(1, 1), Random.between(15, 35)});
        items.put(Material.ENCHANTING_TABLE, new Integer[] {Random.between(1, 2), Random.between(750, 1500)});
        items.put(Material.GHAST_TEAR, new Integer[] {Random.between(1, 1), Random.between(2000, 4000)});
        items.put(Material.HEART_OF_THE_SEA, new Integer[] {Random.between(1, 1), Random.between(5000, 10000)});
        items.put(Material.NAME_TAG, new Integer[] {Random.between(1, 1), Random.between(3000, 5000)});
        items.put(Material.SADDLE, new Integer[] {Random.between(1, 1), Random.between(2000, 4000)});
        items.put(Material.DIAMOND_HORSE_ARMOR, new Integer[] {Random.between(1, 1), Random.between(2000, 4000)});
        items.put(Material.GOLDEN_HORSE_ARMOR, new Integer[] {Random.between(1, 1), Random.between(1500, 3000)});
        items.put(Material.IRON_HORSE_ARMOR, new Integer[] {Random.between(1, 1), Random.between(1000, 2000)});
        items.put(Material.LEATHER_HORSE_ARMOR, new Integer[] {Random.between(1, 1), Random.between(1000, 1500)});
        items.put(Material.CHAINMAIL_HELMET, new Integer[] {Random.between(1, 1), Random.between(1500, 3000)});
        items.put(Material.CHAINMAIL_CHESTPLATE, new Integer[] {Random.between(1, 1), Random.between(1500, 3000)});
        items.put(Material.CHAINMAIL_LEGGINGS, new Integer[] {Random.between(1, 1), Random.between(1500, 3000)});
        items.put(Material.CHAINMAIL_BOOTS, new Integer[] {Random.between(1, 1), Random.between(1500, 3000)});

        return items;
    }

}