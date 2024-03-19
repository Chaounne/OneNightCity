package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class SombreHeros extends Trader {

    private static Trader instance;

    public SombreHeros(Location loc) {
        super(loc, "Sombre Héros", Villager.Type.SWAMP, Villager.Profession.ARMORER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.SCULK, new Integer[] {Random.between(1, 5), Random.between(20, 150)});
        items.put(Material.SCULK_VEIN, new Integer[] {Random.between(1, 2), Random.between(50, 90)});
        items.put(Material.SCULK_CATALYST, new Integer[] {Random.between(1, 2), Random.between(50, 500)});
        items.put(Material.SCULK_SENSOR, new Integer[] {Random.between(1, 2), Random.between(50, 500)});
        items.put(Material.SCULK_SHRIEKER, new Integer[] {Random.between(1, 1), Random.between(5000, 10000)});
        items.put(Material.COBWEB, new Integer[] {Random.between(1, 5), Random.between(1, 10)});
        items.put(Material.ENCHANTED_GOLDEN_APPLE, new Integer[] {Random.between(1, 1), Random.between(10000, 25000)});
        items.put(Material.DEEPSLATE_BRICKS, new Integer[] {Random.between(1, 5), Random.between(1, 8)});
        items.put(Material.CANDLE, new Integer[] {Random.between(1, 3), Random.between(50, 500)});
        items.put(Material.SOUL_LANTERN, new Integer[] {Random.between(1, 3), Random.between(5, 20)});
        items.put(Material.POLISHED_DEEPSLATE, new Integer[] {Random.between(1, 5), Random.between(5, 20)});

        return items;
    }

}