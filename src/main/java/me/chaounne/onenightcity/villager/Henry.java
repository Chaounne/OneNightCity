package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class Henry extends Trader {

    private static Trader instance;

    public Henry(Location loc) {
        super(loc, "Henry", null, Villager.Profession.FARMER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //entre 1 et 3 wheat
        int amount = (int) (Math.random() * 3) + 1;
        //entre 2 et 15 poudre
        int price = (int) (Math.random() * 15) + 2;
        items.put(new ItemBuilder(Material.WHEAT).amount(amount).build(), price);
        //add melon (amount 1-3, price 2-15)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 2;
        items.put(new ItemBuilder(Material.MELON).amount(amount).build(), price);
        //add pumpkin (amount 1-3, price 2-16)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 2;
        items.put(new ItemBuilder(Material.PUMPKIN).amount(amount).build(), price);
        //add carrot (amount 1-3, price 2-15)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 2;
        items.put(new ItemBuilder(Material.CARROT).amount(amount).build(), price);
        //add potato (amount 1-3, price 2-15)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 2;
        items.put(new ItemBuilder(Material.POTATO).amount(amount).build(), price);
        //add beetroot (amount 1-3, price 2-15)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 2;
        items.put(new ItemBuilder(Material.BEETROOT).amount(amount).build(), price);
        //add apple (amount 1-3, price 10-15)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 10;
        items.put(new ItemBuilder(Material.APPLE).amount(amount).build(), price);
        //sugar_cane (amount 1-5, price 2-15)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 15) + 2;
        items.put(new ItemBuilder(Material.SUGAR_CANE).amount(amount).build(), price);
        //cocoa_beans (amount 1-3, price 10-15)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 10;
        items.put(new ItemBuilder(Material.COCOA_BEANS).amount(amount).build(), price);

        return items;
    }

}