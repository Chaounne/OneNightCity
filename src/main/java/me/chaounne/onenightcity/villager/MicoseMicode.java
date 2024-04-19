package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class MicoseMicode extends Trader {

    private static Trader instance;

    public MicoseMicode(Location loc) {
        super(loc, "Micose Micode", Villager.Type.SWAMP, Villager.Profession.FLETCHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        items.put(Material.MYCELIUM, new Integer[] {Random.between(1, 5), Random.between(50, 500)});
        items.put(Material.RED_MUSHROOM_BLOCK, new Integer[] {Random.between(1, 5), Random.between(50, 500)});
        items.put(Material.BROWN_MUSHROOM_BLOCK, new Integer[] {Random.between(1, 5), Random.between(50, 500)});
        items.put(Material.MUSHROOM_STEM, new Integer[] {Random.between(1, 5), Random.between(50, 500)});
        items.put(Material.SHROOMLIGHT, new Integer[] {Random.between(1, 5), Random.between(20, 50)});
        items.put(Material.MUSHROOM_STEW, new Integer[] {Random.between(1, 1), Random.between(250, 500)});
        items.put(Material.AMETHYST_BLOCK, new Integer[] {Random.between(1, 1), Random.between(150, 350)});
        items.put(Material.AMETHYST_SHARD, new Integer[] {Random.between(1, 1), Random.between(350, 700)});
        items.put(Material.SPYGLASS, new Integer[] {Random.between(1, 1), Random.between(250, 500)});
        items.put(Material.TINTED_GLASS, new Integer[] {Random.between(1, 1), Random.between(200, 400)});
        items.put(Material.RED_SAND, new Integer[] {Random.between(1, 1), Random.between(20, 40)});

        return items;
    }

}
