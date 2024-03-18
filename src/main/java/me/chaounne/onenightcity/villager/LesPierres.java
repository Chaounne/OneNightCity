package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class LesPierres extends Trader {

    private static Trader instance;

    public LesPierres(Location loc) {
        super(loc, "Les Pierres", Villager.Type.SWAMP, Villager.Profession.MASON, 18);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        //REDSTONE_BLOCK (amount 1-10, price 20-250)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 250) + 20;
        inventory.addItem(new ItemBuilder(Material.REDSTONE_BLOCK).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //COMPARATOR (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        inventory.addItem(new ItemBuilder(Material.COMPARATOR).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //TARGET (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        inventory.addItem(new ItemBuilder(Material.TARGET).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //HOPPER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        inventory.addItem(new ItemBuilder(Material.HOPPER).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //DISPENSER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        inventory.addItem(new ItemBuilder(Material.DISPENSER).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //DROPPER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        inventory.addItem(new ItemBuilder(Material.DROPPER).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //OBSERVER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        inventory.addItem(new ItemBuilder(Material.OBSERVER).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //PISTON (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        inventory.addItem(new ItemBuilder(Material.PISTON).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //STICKY_PISTON (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 300) + 30;
        inventory.addItem(new ItemBuilder(Material.STICKY_PISTON).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //TRIPWIRE_HOOK (amount 4-10, price 10-100)
        amount = (int) (Math.random() * 7) + 4;
        price = (int) (Math.random() * 100) + 10;
        inventory.addItem(new ItemBuilder(Material.TRIPWIRE_HOOK).amount(amount).addLore(price + " Poudres").build(), 9, price);
    }

}