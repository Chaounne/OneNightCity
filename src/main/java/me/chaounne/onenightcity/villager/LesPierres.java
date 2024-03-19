package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

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
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //REDSTONE_BLOCK (amount 1-10, price 20-250)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 250) + 20;
        items.put(new ItemBuilder(Material.REDSTONE_BLOCK).amount(amount).build(), price);
        //COMPARATOR (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        items.put(new ItemBuilder(Material.COMPARATOR).amount(amount).build(), price);
        //TARGET (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        items.put(new ItemBuilder(Material.TARGET).amount(amount).build(), price);
        //HOPPER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        items.put(new ItemBuilder(Material.HOPPER).amount(amount).build(), price);
        //DISPENSER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        items.put(new ItemBuilder(Material.DISPENSER).amount(amount).build(), price);
        //DROPPER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        items.put(new ItemBuilder(Material.DROPPER).amount(amount).build(), price);
        //OBSERVER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        items.put(new ItemBuilder(Material.OBSERVER).amount(amount).build(), price);
        //PISTON (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        items.put(new ItemBuilder(Material.PISTON).amount(amount).build(), price);
        //STICKY_PISTON (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 300) + 30;
        items.put(new ItemBuilder(Material.STICKY_PISTON).amount(amount).build(), price);
        //TRIPWIRE_HOOK (amount 4-10, price 10-100)
        amount = (int) (Math.random() * 7) + 4;
        price = (int) (Math.random() * 100) + 10;
        items.put(new ItemBuilder(Material.TRIPWIRE_HOOK).amount(amount).build(), price);

        return items;
    }

}