package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class ClodoFrancis extends Trader {

    private static Trader instance;

    private ClodoFrancis(Location loc) {
        super(loc, "Francis Clodo", Villager.Type.SWAMP, Villager.Profession.BUTCHER, 9);
        instance = this;
    }

    public static void create(Location loc) {
        if (instance == null)
            new ClodoFrancis(loc);
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        //GLASS_BOTTLE (amount 2-10, price 6-25)
        int amount = (int) (Math.random() * 10) + 2;
        int price = (int) (Math.random() * 30) + 6;
        inventory.addItem(new ItemBuilder(Material.GLASS_BOTTLE).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //HONEY_BOTTLE (amount 2-10, price 15-25)
        amount = (int) (Math.random() * 10) + 2;
        price = (int) (Math.random() * 30) + 15;
        inventory.addItem(new ItemBuilder(Material.HONEY_BOTTLE).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //MILK_BUCKET (amount 2-5, price 20-40)
        amount = (int) (Math.random() * 5) + 2;
        price = (int) (Math.random() * 20) + 40;
        inventory.addItem(new ItemBuilder(Material.MILK_BUCKET).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //BEETROOT_SOUP (amount 1, price 15-35)
        amount = 1;
        price = (int) (Math.random() * 45) + 15;
        inventory.addItem(new ItemBuilder(Material.BEETROOT_SOUP).amount(amount).addLore( price + " Poudres").build(), 3, price);
        //MUSHROOM_STEW (amount 1, price 15-35)
        price = (int) (Math.random() * 35) + 15;
        inventory.addItem(new ItemBuilder(Material.MUSHROOM_STEW).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //POTION (amount 2-10, price 6-25)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 45) + 6;
        inventory.addItem(new ItemBuilder(Material.POTION).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //ENCHANTING_TABLE (amount 1-3, price 150-500)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 500) + 150;
        inventory.addItem(new ItemBuilder(Material.ENCHANTING_TABLE).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //WRITABLE_BOOK (amount 1-3, price 15-35)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 55) + 15;
        inventory.addItem(new ItemBuilder(Material.WRITABLE_BOOK).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //SPLASH_POTION (amount 1-3, price 50-150)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 150) + 50;
        inventory.addItem(new ItemBuilder(Material.SPLASH_POTION).amount(amount).addLore(price + " Poudres").build(), 8, price);
    }

}