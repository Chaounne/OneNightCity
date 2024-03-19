package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class KylianMBouffe extends Trader {

    private static Trader instance;

    public KylianMBouffe(Location loc) {
        super(loc, "Kylian MBouffé", null, Villager.Profession.BUTCHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //COOKED_BEEF (amount 1-10, price 1-20)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 20) + 1;
        items.put(new ItemBuilder(Material.COOKED_BEEF).amount(amount).build(), price);
        //COOKED_CHICKEN (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        items.put(new ItemBuilder(Material.COOKED_CHICKEN).amount(amount).build(), price);
        //COOKED_PORKCHOP (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        items.put(new ItemBuilder(Material.COOKED_PORKCHOP).amount(amount).build(), price);
        //COOKED_MUTTON (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        items.put(new ItemBuilder(Material.COOKED_MUTTON).amount(amount).build(), price);
        //COOKED_RABBIT (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        items.put(new ItemBuilder(Material.COOKED_RABBIT).amount(amount).build(), price);
        //RABBIT_STEW (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        items.put(new ItemBuilder(Material.RABBIT_STEW).amount(amount).build(), price);
        //PUMPKIN_PIE (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        items.put(new ItemBuilder(Material.PUMPKIN_PIE).amount(amount).build(), price);
        //COOKIE (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        items.put(new ItemBuilder(Material.COOKIE).amount(amount).build(), price);
        //SWEET_BERRIES (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 25) + 1;
        items.put(new ItemBuilder(Material.SWEET_BERRIES).amount(amount).build(), price);
        //DRIED_KELP (amount 1-10, price 1-10)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 10) + 1;
        items.put(new ItemBuilder(Material.DRIED_KELP).amount(amount).build(), price);

        return items;
    }

}