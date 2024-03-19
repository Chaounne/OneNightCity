package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

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
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //FISHING_ROD (amount 1, price 10-50)
        int amount = 1;
        int price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.FISHING_ROD).amount(amount).build(), price);
        //WATER_BUCKET (amount 1, price 20-40)
        price = (int) (Math.random() * 40) + 20;
        items.put(new ItemBuilder(Material.WATER_BUCKET).amount(amount).build(), price);
        //PUFFERFISH (amount 1, price 50-150)
        price = (int) (Math.random() * 150) + 50;
        items.put(new ItemBuilder(Material.PUFFERFISH).amount(amount).build(), price);
        //COOKED_COD (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.COOKED_COD).amount(amount).build(), price);
        //TROPICAL_FISH (amount 2-3, price 150-200)
        amount = (int) (Math.random() * 2) + 2;
        price = (int) (Math.random() * 51) + 150;
        items.put(new ItemBuilder(Material.TROPICAL_FISH).amount(amount).build(), price);
        //COD_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.COD_BUCKET).amount(amount).build(), price);
        //SALMON_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.SALMON_BUCKET).amount(amount).build(), price);
        //PUFFERFISH_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.PUFFERFISH_BUCKET).amount(amount).build(), price);
        //TROPICAL_FISH_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.TROPICAL_FISH_BUCKET).amount(amount).build(), price);

        return items;
    }

}