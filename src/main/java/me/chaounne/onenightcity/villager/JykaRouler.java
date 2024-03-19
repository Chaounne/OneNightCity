package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class JykaRouler extends Trader {

    private static Trader instance;

    public JykaRouler(Location loc) {
        super(loc, "Jyka Rouler", Villager.Type.PLAINS, Villager.Profession.LIBRARIAN);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.BOOK, new Integer[] {Random.between(1, 5), Random.between(10, 50)});
        items.put(Material.BOOKSHELF, new Integer[] {Random.between(1, 5), Random.between(60, 200)});
        items.put(Material.LECTERN, new Integer[] {Random.between(1, 5), Random.between(10, 700)});
        items.put(Material.CROSSBOW, new Integer[] {Random.between(1, 5), Random.between(10, 700)});
        items.put(Material.INK_SAC, new Integer[] {Random.between(1, 5), Random.between(10, 300)});
        items.put(Material.GLOW_INK_SAC, new Integer[] {Random.between(1, 5), Random.between(10, 1000)});
        items.put(Material.WRITABLE_BOOK, new Integer[] {Random.between(1, 5), Random.between(10, 200)});
        items.put(Material.FEATHER, new Integer[] {Random.between(1, 10), Random.between(1, 20)});

        return items;
    }

}