package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class BeauThony extends Trader {

    private static Trader instance;

    public BeauThony(Location loc) {
        super(loc, "Beau Thony", Villager.Type.JUNGLE, Villager.Profession.CARTOGRAPHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.ACACIA_SAPLING, new Integer[] {Random.between(3, 10), Random.between(50, 150)});
        items.put(Material.BAMBOO, new Integer[] {Random.between(5, 10), Random.between(5, 15)});
        items.put(Material.BIRCH_SAPLING, new Integer[] {Random.between(3, 12), Random.between(50, 150)});
        items.put(Material.DARK_OAK_SAPLING, new Integer[] {Random.between(3, 10), Random.between(50, 150)});
        items.put(Material.SPRUCE_SAPLING, new Integer[] {Random.between(3, 10), Random.between(50, 150)});
        items.put(Material.JUNGLE_SAPLING, new Integer[] {Random.between(3, 10), Random.between(50, 150)});
        items.put(Material.OAK_SAPLING, new Integer[] {Random.between(5, 10), Random.between(50, 150)});
        items.put(Material.FLOWER_POT, new Integer[] {Random.between(1, 10), Random.between(50, 150)});
        items.put(Material.MANGROVE_PROPAGULE, new Integer[] {Random.between(3, 10), Random.between(50, 150)});
        items.put(Material.AZALEA, new Integer[] {Random.between(1, 10), Random.between(50, 150)});
        items.put(Material.AZALEA_LEAVES, new Integer[] {Random.between(1, 10), Random.between(50, 150)});

        return items;
    }

}