package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class LesPierres extends Trader {

    private static Trader instance;

    public LesPierres(Location loc) {
        super(loc, "Les Pierres", Villager.Type.SWAMP, Villager.Profession.MASON);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

       items.put(Material.REDSTONE_BLOCK, new Integer[] {Random.between(1, 10), Random.between(20, 500)});
       items.put(Material.COMPARATOR, new Integer[] {Random.between(1, 10), Random.between(20, 500)});
       items.put(Material.TARGET, new Integer[] {Random.between(1, 10), Random.between(20, 250)});
       items.put(Material.HOPPER, new Integer[] {Random.between(1, 10), Random.between(20, 250)});
       items.put(Material.DISPENSER, new Integer[] {Random.between(1, 10), Random.between(20, 2000)});
       items.put(Material.DROPPER, new Integer[] {Random.between(1, 10), Random.between(20, 500)});
       items.put(Material.OBSERVER, new Integer[] {Random.between(1, 10), Random.between(20, 500)});
       items.put(Material.PISTON, new Integer[] {Random.between(1, 10), Random.between(20, 500)});
       items.put(Material.STICKY_PISTON, new Integer[] {Random.between(1, 10), Random.between(20, 700)});
       items.put(Material.TRIPWIRE_HOOK, new Integer[] {Random.between(4, 10), Random.between(20, 100)});

        return items;
    }

}