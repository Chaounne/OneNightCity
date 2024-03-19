package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class NegeuxDemo extends Trader {

    private static Trader instance;

    public NegeuxDemo(Location loc) {
        super(loc, "Négeux Demo", Villager.Type.SNOW, Villager.Profession.MASON);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //SNOWBALL (amount 1-5, price 1-15)
        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 15) + 1;
        items.put(new ItemBuilder(Material.SNOWBALL).amount(amount).build(), price);
        //POWDER_SNOW_BUCKET (amount 1, price 1-15)
        amount = 1;
        price = (int) (Math.random() * 15) + 1;
        items.put(new ItemBuilder(Material.POWDER_SNOW_BUCKET).amount(amount).build(), price);
        //SNOW (amount 1-5, price 5-25)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 25) + 5;
        items.put(new ItemBuilder(Material.SNOW).amount(amount).build(), price);
        //SNOW_BLOCK (amount 1-5, price 5-30)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 60) + 4;
        items.put(new ItemBuilder(Material.SNOW_BLOCK).amount(amount).build(), price);
        //CARVED_PUMPKIN (amount 1-5, price 2-25)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 25) + 2;
        items.put(new ItemBuilder(Material.CARVED_PUMPKIN).amount(amount).build(), price);
        //ICE (amount 1-5, price 1-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 1;
        items.put(new ItemBuilder(Material.ICE).amount(amount).build(), price);
        //BLUE_ICE (amount 1-5, price 1-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 1;
        items.put(new ItemBuilder(Material.BLUE_ICE).amount(amount).build(), price);
        //PACKED_ICE (amount 1-5, price 1-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 1;
        items.put(new ItemBuilder(Material.PACKED_ICE).amount(amount).build(), price);

        return items;
    }

}