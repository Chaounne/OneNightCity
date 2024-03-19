package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class LucieAcier extends Trader {

    private static Trader instance;

    public LucieAcier(Location loc) {
        super(loc, "Lucie Acier", Villager.Type.JUNGLE, Villager.Profession.BUTCHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

       items.put(Material.NETHER_WART, new Integer[] {Random.between(1, 10), Random.between(10, 70)});
       items.put(Material.NETHER_WART_BLOCK, new Integer[] {Random.between(1, 10), Random.between(1, 25)});
       items.put(Material.WARPED_WART_BLOCK, new Integer[] {Random.between(1, 10), Random.between(1, 25)});
       items.put(Material.WARPED_FUNGUS, new Integer[] {Random.between(1, 10), Random.between(1, 20)});
       items.put(Material.WARPED_FUNGUS_ON_A_STICK, new Integer[] {Random.between(1, 1), Random.between(10, 1500)});
       items.put(Material.QUARTZ_BLOCK, new Integer[] {Random.between(1, 2), Random.between(20, 50)});
       items.put(Material.QUARTZ, new Integer[] {Random.between(1, 2), Random.between(10, 40)});
       items.put(Material.NETHER_BRICK, new Integer[] {Random.between(1, 10), Random.between(1, 10)});
       items.put(Material.NETHER_BRICK_FENCE, new Integer[] {Random.between(1, 10), Random.between(10, 40)});
       items.put(Material.NETHER_BRICK_SLAB, new Integer[] {Random.between(1, 10), Random.between(10, 40)});
       items.put(Material.NETHER_BRICK_STAIRS, new Integer[] {Random.between(1, 10), Random.between(10, 40)});
       items.put(Material.NETHERITE_SCRAP, new Integer[] {Random.between(1, 1), Random.between(500, 1500)});
       items.put(Material.NETHERITE_INGOT, new Integer[] {Random.between(1, 1), Random.between(3000, 9000)});

        return items;
    }

}