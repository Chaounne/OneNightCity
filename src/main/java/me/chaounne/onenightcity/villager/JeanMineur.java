package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class JeanMineur extends Trader {

    private static Trader instance;

    public JeanMineur(Location loc) {
        super(loc, "Jean Mineur", Villager.Type.DESERT, Villager.Profession.LEATHERWORKER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.DEEPSLATE_COAL_ORE, new Integer[] {Random.between(1, 5), Random.between(500, 2000)});
        items.put(Material.IRON_ORE, new Integer[] {Random.between(1, 5), Random.between(10, 50)});
        items.put(Material.DEEPSLATE_IRON_ORE, new Integer[] {Random.between(1, 5), Random.between(20, 100)});
        items.put(Material.COPPER_ORE, new Integer[] {Random.between(1, 5), Random.between(10, 50)});
        items.put(Material.DEEPSLATE_COPPER_ORE, new Integer[] {Random.between(1, 5), Random.between(10, 50)});
        items.put(Material.GOLD_ORE, new Integer[] {Random.between(1, 5), Random.between(20, 100)});
        items.put(Material.DEEPSLATE_GOLD_ORE, new Integer[] {Random.between(1, 5), Random.between(20, 100)});
        items.put(Material.REDSTONE_ORE, new Integer[] {Random.between(1, 5), Random.between(50, 500)});
        items.put(Material.DEEPSLATE_REDSTONE_ORE, new Integer[] {Random.between(1, 5), Random.between(10, 100)});
        items.put(Material.EMERALD_ORE, new Integer[] {Random.between(1, 5), Random.between(500, 2000)});
        items.put(Material.DEEPSLATE_EMERALD_ORE, new Integer[] {Random.between(1, 5), Random.between(1000, 10000)});
        items.put(Material.LAPIS_ORE, new Integer[] {Random.between(1, 5), Random.between(50, 500)});
        items.put(Material.DEEPSLATE_LAPIS_ORE, new Integer[] {Random.between(1, 5), Random.between(50, 500)});
        items.put(Material.DIAMOND_ORE, new Integer[] {Random.between(1, 5), Random.between(500, 1000)});
        items.put(Material.DEEPSLATE_DIAMOND_ORE, new Integer[] {Random.between(1, 5), Random.between(50, 200)});
        items.put(Material.NETHER_GOLD_ORE, new Integer[] {Random.between(1, 5), Random.between(10, 50)});

        return items;
    }

}