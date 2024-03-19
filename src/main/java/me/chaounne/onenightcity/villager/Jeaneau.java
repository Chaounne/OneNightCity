package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class Jeaneau extends Trader {

    private static Trader instance;

    public Jeaneau(Location loc) {
        super(loc, "Jeaneau", Villager.Type.PLAINS, Villager.Profession.FISHERMAN);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //PRISMARINE (amount 1-15, price 10-50)
        int amount = (int) (Math.random() * 15) + 1;
        int price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.PRISMARINE).amount(amount).build(), price);
        //PRISMARINE_SHARD (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.PRISMARINE_SHARD).amount(amount).build(), price);
        //PRISMARINE_BRICKS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.PRISMARINE_BRICKS).amount(amount).build(), price);
        //PRISMARINE_CRYSTALS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.PRISMARINE_CRYSTALS).amount(amount).build(), price);
        //PRISMARINE_BRICK_SLAB (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.PRISMARINE_BRICK_SLAB).amount(amount).build(), price);
        //PRISMARINE_BRICK_STAIRS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.PRISMARINE_BRICK_STAIRS).amount(amount).build(), price);
        //PRISMARINE_SLAB (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.PRISMARINE_SLAB).amount(amount).build(), price);
        //PRISMARINE_STAIRS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.PRISMARINE_STAIRS).amount(amount).build(), price);
        //PRISMARINE_WALL (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.PRISMARINE_WALL).amount(amount).build(), price);

        return items;
    }

}