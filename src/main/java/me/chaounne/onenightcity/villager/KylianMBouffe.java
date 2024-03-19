package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class KylianMBouffe extends Trader {

    private static Trader instance;

    public KylianMBouffe(Location loc) {
        super(loc, "Kylian MBouffé", null, Villager.Profession.BUTCHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.COOKED_BEEF, new Integer[] {Random.between(1, 10), Random.between(1, 20)});
        items.put(Material.COOKED_CHICKEN, new Integer[] {Random.between(1, 10), Random.between(1, 20)});
        items.put(Material.COOKED_PORKCHOP, new Integer[] {Random.between(1, 10), Random.between(1, 20)});
        items.put(Material.COOKED_MUTTON, new Integer[] {Random.between(1, 10), Random.between(1, 20)});
        items.put(Material.COOKED_RABBIT, new Integer[] {Random.between(1, 5), Random.between(20, 100)});
        items.put(Material.RABBIT_STEW, new Integer[] {Random.between(1, 5), Random.between(200, 1000)});
        items.put(Material.PUMPKIN_PIE, new Integer[] {Random.between(1, 5), Random.between(20, 100)});
        items.put(Material.COOKIE, new Integer[] {Random.between(1, 5), Random.between(20, 100)});
        items.put(Material.SWEET_BERRIES, new Integer[] {Random.between(1, 10), Random.between(1, 25)});
        items.put(Material.DRIED_KELP, new Integer[] {Random.between(1, 10), Random.between(1, 20)});

        return items;
    }

}