package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class JustinPuech extends Trader {

    private static Trader instance;

    public JustinPuech(Location loc) {
        super(loc, "Justin Puech", Villager.Type.SNOW, Villager.Profession.FISHERMAN);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.FISHING_ROD, new Integer[] {Random.between(1, 1), Random.between(800, 2000)});
        items.put(Material.PUFFERFISH, new Integer[] {Random.between(1, 1), Random.between(100, 350)});
        items.put(Material.COOKED_COD, new Integer[] {Random.between(1, 3), Random.between(10, 50)});
        items.put(Material.TROPICAL_FISH, new Integer[] {Random.between(1, 1), Random.between(50, 150)});
        items.put(Material.COD_BUCKET, new Integer[] {Random.between(1, 3), Random.between(800, 1200)});
        items.put(Material.SALMON_BUCKET, new Integer[] {Random.between(1, 3), Random.between(800, 1200)});
        items.put(Material.PUFFERFISH_BUCKET, new Integer[] {Random.between(1, 3), Random.between(800, 2000)});
        items.put(Material.TROPICAL_FISH_BUCKET, new Integer[] {Random.between(1, 3), Random.between(800, 2000)});
        items.put(Material.CARROT_ON_A_STICK, new Integer[] {Random.between(1, 1), Random.between(750, 1500)});
        items.put(Material.AXOLOTL_BUCKET, new Integer[] {Random.between(1, 1), Random.between(1500, 4000)});
        items.put(Material.TADPOLE_BUCKET, new Integer[] {Random.between(1, 1), Random.between(500, 1000)});

        return items;
    }

}