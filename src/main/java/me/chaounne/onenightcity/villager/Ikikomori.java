package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class Ikikomori extends Trader {

    private static Trader instance;

    public Ikikomori(Location loc) {
        super(loc, "Ikikomori", Villager.Type.PLAINS, Villager.Profession.TOOLSMITH);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.MOSS_BLOCK, new Integer[] {Random.between(1, 5), Random.between(10, 45)});
        items.put(Material.MOSS_CARPET, new Integer[] {Random.between(1, 5), Random.between(10, 30)});
        items.put(Material.OCHRE_FROGLIGHT, new Integer[] {Random.between(1, 1), Random.between(50, 1000)});
        items.put(Material.PEARLESCENT_FROGLIGHT, new Integer[] {Random.between(1, 1), Random.between(50, 1000)});
        items.put(Material.VERDANT_FROGLIGHT, new Integer[] {Random.between(1, 1), Random.between(50, 1000)});
        items.put(Material.AXOLOTL_BUCKET, new Integer[] {Random.between(1, 1), Random.between(20, 500)});
        items.put(Material.MANGROVE_PLANKS, new Integer[] {Random.between(1, 5), Random.between(10, 40)});
        items.put(Material.MANGROVE_LEAVES, new Integer[] {Random.between(1, 5), Random.between(10, 40)});
        items.put(Material.POINTED_DRIPSTONE, new Integer[] {Random.between(1, 5), Random.between(25, 75)});

        return items;
    }

}