package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class NegeuxDemo extends Trader {

    private static Trader instance;

    public NegeuxDemo(Location loc) {
        super(loc, "Négeux Demo", Villager.Type.SNOW, Villager.Profession.MASON);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.SNOWBALL, new Integer[] {Random.between(2, 5), Random.between(15, 30)});
        items.put(Material.POWDER_SNOW_BUCKET, new Integer[] {Random.between(1, 1), Random.between(100, 500)});
        items.put(Material.SNOW, new Integer[] {Random.between(1, 5), Random.between(5, 25)});
        items.put(Material.SNOW_BLOCK, new Integer[] {Random.between(1, 5), Random.between(4, 60)});
        items.put(Material.CARVED_PUMPKIN, new Integer[] {Random.between(1, 5), Random.between(2, 25)});
        items.put(Material.ICE, new Integer[] {Random.between(1, 5), Random.between(5, 50)});
        items.put(Material.BLUE_ICE, new Integer[] {Random.between(1, 5), Random.between(25, 75)});
        items.put(Material.PACKED_ICE, new Integer[] {Random.between(1, 5), Random.between(50, 100)});

        return items;
    }

}