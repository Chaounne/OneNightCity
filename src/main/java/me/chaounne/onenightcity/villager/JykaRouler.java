package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class JykaRouler extends Trader {

    private static Trader instance;

    public JykaRouler(Location loc) {
        super(loc, "Jyka Rouler", Villager.Type.PLAINS, Villager.Profession.LIBRARIAN);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //BOOK (amount 1-5, price 10-50)
        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.BOOK).amount(amount).build(), price);
        //BOOKSHELF (amount 1-5, price 60-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 60;
        items.put(new ItemBuilder(Material.BOOKSHELF).amount(amount).build(), price);
        //LECTERN (amount 1-5, price 10-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 10;
        items.put(new ItemBuilder(Material.LECTERN).amount(amount).build(), price);
        //CROSSBOW (amount 1-5, price 10-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 10;
        items.put(new ItemBuilder(Material.CROSSBOW).amount(amount).build(), price);
        //INK_SAC (amount 1-5, price 10-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 10;
        items.put(new ItemBuilder(Material.INK_SAC).amount(amount).build(), price);
        //GLOW_INK_SAC (amount 1-5, price 10-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 10;
        items.put(new ItemBuilder(Material.GLOW_INK_SAC).amount(amount).build(), price);
        //WRITABLE_BOOK (amount 1-5, price 10-70)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 70) + 10;
        items.put(new ItemBuilder(Material.WRITABLE_BOOK).amount(amount).build(), price);
        //FEATHER (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        items.put(new ItemBuilder(Material.FEATHER).amount(amount).build(), price);

        return items;
    }

}