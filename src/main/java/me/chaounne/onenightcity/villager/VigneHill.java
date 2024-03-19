package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class VigneHill extends Trader {

    private static Trader instance;

    public VigneHill(Location loc) {
        super(loc, "Vigne Hill", Villager.Type.SWAMP, Villager.Profession.SHEPHERD);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    protected LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.MUSIC_DISC_5, new Integer[] {Random.between(1, 1), Random.between(30000, 60000)});
        items.put(Material.DISC_FRAGMENT_5, new Integer[] {Random.between(1, 1), Random.between(3000, 6000)});
        items.put(Material.MUSIC_DISC_11, new Integer[] {Random.between(1, 1), Random.between(10000, 20000)});
        items.put(Material.MUSIC_DISC_13, new Integer[] {Random.between(1, 1), Random.between(7500, 15000)});
        items.put(Material.MUSIC_DISC_BLOCKS, new Integer[] {Random.between(1, 1), Random.between(10000, 20000)});
        items.put(Material.MUSIC_DISC_CAT, new Integer[] {Random.between(1, 1), Random.between(7500, 15000)});
        items.put(Material.MUSIC_DISC_CHIRP, new Integer[] {Random.between(1, 1), Random.between(10000, 20000)});
        items.put(Material.MUSIC_DISC_FAR, new Integer[] {Random.between(1, 1), Random.between(10000, 20000)});
        items.put(Material.MUSIC_DISC_MALL, new Integer[] {Random.between(1, 1), Random.between(10000, 20000)});
        items.put(Material.MUSIC_DISC_MELLOHI, new Integer[] {Random.between(1, 1), Random.between(10000, 20000)});
        items.put(Material.MUSIC_DISC_OTHERSIDE, new Integer[] {Random.between(1, 1), Random.between(25000, 50000)});
        items.put(Material.MUSIC_DISC_PIGSTEP, new Integer[] {Random.between(1, 1), Random.between(35000, 70000)});
        items.put(Material.MUSIC_DISC_STAL, new Integer[] {Random.between(1, 1), Random.between(10000, 20000)});
        items.put(Material.MUSIC_DISC_STRAD, new Integer[] {Random.between(1, 1), Random.between(10000, 20000)});
        items.put(Material.MUSIC_DISC_WAIT, new Integer[] {Random.between(1, 1), Random.between(10000, 20000)});
        items.put(Material.MUSIC_DISC_WARD, new Integer[] {Random.between(1, 1), Random.between(10000, 20000)});

        return items;
    }
}
