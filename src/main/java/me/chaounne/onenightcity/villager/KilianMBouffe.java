package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class KilianMBouffe extends Trader {

    private static Trader instance;

    private KilianMBouffe(Location loc) {
        super(loc, "Kylian MBouffé", null, Villager.Profession.BUTCHER, 18);
        instance = this;
    }

    public static void create(Location loc) {
        if (instance == null)
            new KilianMBouffe(loc);
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        //COOKED_BEEF (amount 1-10, price 1-20)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 20) + 1;
        inventory.addItem(new ItemBuilder(Material.COOKED_BEEF).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //COOKED_CHICKEN (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        inventory.addItem(new ItemBuilder(Material.COOKED_CHICKEN).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //COOKED_PORKCHOP (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        inventory.addItem(new ItemBuilder(Material.COOKED_PORKCHOP).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //COOKED_MUTTON (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        inventory.addItem(new ItemBuilder(Material.COOKED_MUTTON).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //COOKED_RABBIT (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        inventory.addItem(new ItemBuilder(Material.COOKED_RABBIT).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //RABBIT_STEW (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        inventory.addItem(new ItemBuilder(Material.RABBIT_STEW).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //PUMPKIN_PIE (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        inventory.addItem(new ItemBuilder(Material.PUMPKIN_PIE).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //COOKIE (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        inventory.addItem(new ItemBuilder(Material.COOKIE).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //SWEET_BERRIES (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 25) + 1;
        inventory.addItem(new ItemBuilder(Material.SWEET_BERRIES).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //DRIED_KELP (amount 1-10, price 1-10)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 10) + 1;
        inventory.addItem(new ItemBuilder(Material.DRIED_KELP).amount(amount).addLore(price + " Poudres").build(), 9, price);
    }

}