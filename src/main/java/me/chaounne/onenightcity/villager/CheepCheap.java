package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public class CheepCheap extends Trader {

    private static Trader instance;

    public CheepCheap(Location loc) {
        super(loc, "Cheep Cheap", Villager.Type.TAIGA, Villager.Profession.SHEPHERD);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        LinkedHashMap<Material, Integer[]> items = new LinkedHashMap<>();

        String[] woolColors = {
                "WHITE_WOOL", "RED_WOOL", "BLUE_WOOL", "GREEN_WOOL", "YELLOW_WOOL",
                "PURPLE_WOOL", "ORANGE_WOOL", "MAGENTA_WOOL", "LIGHT_BLUE_WOOL",
                "LIGHT_GRAY_WOOL", "GRAY_WOOL", "BLACK_WOOL", "BROWN_WOOL",
                "CYAN_WOOL", "LIME_WOOL"
        };

        for (String woolColor : woolColors) {
            Material woolMaterial = Material.getMaterial(woolColor);
            items.put(woolMaterial, new Integer[] {Random.between(1, 5), Random.between(50, 150)});
        }

        return items;
    }

}