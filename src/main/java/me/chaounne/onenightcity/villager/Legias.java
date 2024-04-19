package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class Legias extends Trader {

    private static Trader instance;

    public Legias(Location loc) {
        super(loc, "Legias", Villager.Type.SWAMP, Villager.Profession.FLETCHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.ARROW, new Integer[] {Random.between(1, 5), Random.between(15, 50)});
        items.put(Material.BOW, new Integer[] {Random.between(1, 1), Random.between(500, 1500)});
        items.put(Material.SPECTRAL_ARROW, new Integer[] {Random.between(1, 5), Random.between(25, 150)});
        items.put(Material.CROSSBOW, new Integer[] {Random.between(1, 1), Random.between(1250, 2500)});
        items.put(Material.BIG_DRIPLEAF, new Integer[] {Random.between(1, 5), Random.between(15, 100)});
        items.put(Material.SMALL_DRIPLEAF, new Integer[] {Random.between(1, 5), Random.between(15, 100)});
        items.put(Material.SPORE_BLOSSOM, new Integer[] {Random.between(1, 5), Random.between(40, 250)});
        items.put(Material.VINE, new Integer[] {Random.between(1, 5), Random.between(15, 30)});
        items.put(Material.LILY_PAD, new Integer[] {Random.between(1, 5), Random.between(15, 100)});

        return items;
    }

}