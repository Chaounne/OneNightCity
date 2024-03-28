package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class Henry extends Trader {

    private static Trader instance;

    public Henry(Location loc) {
        super(loc, "Henry", null, Villager.Profession.FARMER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.MELON, new Integer[] {Random.between(1, 3), Random.between(15, 50)});
        items.put(Material.PUMPKIN, new Integer[] {Random.between(1, 3), Random.between(15, 100)});
        items.put(Material.CARROT, new Integer[] {Random.between(1, 3), Random.between(15, 35)});
        items.put(Material.POTATO, new Integer[] {Random.between(1, 3), Random.between(15, 35)});
        items.put(Material.BEETROOT, new Integer[] {Random.between(1, 3), Random.between(15, 35)});
        items.put(Material.WHEAT, new Integer[] {Random.between(1, 3), Random.between(15, 25)});
        items.put(Material.SUGAR_CANE, new Integer[] {Random.between(1, 5), Random.between(15, 50)});
        items.put(Material.COCOA_BEANS, new Integer[] {Random.between(1, 3), Random.between(15, 100)});
        items.put(Material.APPLE, new Integer[] {Random.between(1, 3), Random.between(15, 100)});

        return items;
    }

}