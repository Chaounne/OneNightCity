package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class CheepCheap extends Trader {

    private static Trader instance;

    private CheepCheap(Location loc) {
        super(loc, "Cheep Cheap", Villager.Type.TAIGA, Villager.Profession.SHEPHERD, 18);
        instance = this;
    }

    public static void create(Location loc) {
        if (instance == null)
            new CheepCheap(loc);
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        String[] woolColors = {
                "WHITE_WOOL", "RED_WOOL", "BLUE_WOOL", "GREEN_WOOL", "YELLOW_WOOL",
                "PURPLE_WOOL", "ORANGE_WOOL", "MAGENTA_WOOL", "LIGHT_BLUE_WOOL",
                "LIGHT_GRAY_WOOL", "GRAY_WOOL", "BLACK_WOOL", "BROWN_WOOL",
                "CYAN_WOOL", "LIME_WOOL"
        };

        for (int i = 0; i < woolColors.length; i++) {
            int price = (int) (Math.random() * 23) + 2;
            int amount = (int) (Math.random() * 9) + 1;

            Material woolMaterial = Material.getMaterial(woolColors[i]);
            if (woolMaterial != null) {
                inventory.addItem(
                        new ItemBuilder(woolMaterial)
                                .amount(amount)
                                .addLore(price + " Poudres")
                                .build(),
                        i,
                        price
                );
            }
        }
    }

}