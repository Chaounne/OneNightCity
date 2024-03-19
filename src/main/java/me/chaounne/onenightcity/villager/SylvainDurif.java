package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class SylvainDurif extends Trader {

    private static Trader instance;

    public SylvainDurif(Location loc) {
        super(loc, "Civique Cosmique Durif", Villager.Type.SWAMP, Villager.Profession.LEATHERWORKER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //CHORUS_FRUIT (amount 1-3, price 15-35)
        int amount = (int) (Math.random() * 3) + 1;
        int price = (int) (Math.random() * 21) + 15;
        items.put(new ItemBuilder(Material.CHORUS_FRUIT).amount(amount).build(), price);
        //CHORUS_FLOWER (amount 1-3, price 10-30)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 21) + 10;
        items.put(new ItemBuilder(Material.CHORUS_FLOWER).amount(amount).build(), price);
        //PURPUR_BLOCK (amount 1-3, price 50-80)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 31) + 50;
        items.put(new ItemBuilder(Material.PURPUR_BLOCK).amount(amount).build(), price);
        //PURPUR_PILLAR (amount 1-3, price 50-80)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 31) + 50;
        items.put(new ItemBuilder(Material.PURPUR_PILLAR).amount(amount).build(), price);
        //PURPUR_SLAB (amount 1-3, price 50-60)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 11) + 50;
        items.put(new ItemBuilder(Material.PURPUR_SLAB).amount(amount).build(), price);
        //PURPUR_STAIRS (amount 1-3, price 50-60)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 11) + 50;
        items.put(new ItemBuilder(Material.PURPUR_STAIRS).amount(amount).build(), price);
        //END_STONE_BRICKS (amount 5-10, price 10-50)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 41) + 10;
        items.put(new ItemBuilder(Material.END_STONE_BRICKS).amount(amount).build(), price);
        //END_STONE_BRICK_SLAB (amount 5-10, price 10-25)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 16) + 10;
        items.put(new ItemBuilder(Material.END_STONE_BRICK_SLAB).amount(amount).build(), price);
        //END_STONE_BRICK_STAIRS (amount 5-10, price 10-50)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 41) + 10;
        items.put(new ItemBuilder(Material.END_STONE_BRICK_STAIRS).amount(amount).build(), price);
        //END_STONE_BRICK_WALL (amount 5-10, price 10-50)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 41) + 10;
        items.put(new ItemBuilder(Material.END_STONE_BRICK_WALL).amount(amount).build(), price);
        //END_CRYSTAL (amount 1, price 100-500)
        amount = 1;
        price = (int) (Math.random() * 401) + 100;
        items.put(new ItemBuilder(Material.END_CRYSTAL).amount(amount).build(),  price);
        //END_ROD (amount 1-5, price 5-110)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 106) + 5;
        items.put(new ItemBuilder(Material.END_ROD).amount(amount).build(),  price);
        //OBSIDIAN (amount 1-10, price 50-100)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 51) + 50;
        items.put(new ItemBuilder(Material.OBSIDIAN).amount(amount).build(),  price);

        return items;
    }

}