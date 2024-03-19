package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class HutilItaire extends Trader {

    private static Trader instance;

    public HutilItaire(Location loc) {
        super(loc, "Hutil Itaire", Villager.Type.PLAINS, Villager.Profession.CARTOGRAPHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //CRAFTING_TABLE (amount 1-3, price 2-15)
        int amount = (int) (Math.random() * 3) + 1;
        int price = (int) (Math.random() * 15) + 2;
        items.put(new ItemBuilder(Material.CRAFTING_TABLE).amount(amount).build(), price);
        //STONECUTTER (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        items.put(new ItemBuilder(Material.STONECUTTER).amount(amount).build(), price);
        //CARTOGRAPHY_TABLE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 31) + 20;
        items.put(new ItemBuilder(Material.CARTOGRAPHY_TABLE).amount(amount).build(), price);
        //FLETCHING_TABLE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        items.put(new ItemBuilder(Material.FLETCHING_TABLE).amount(amount).build(), price);
        //SMITHING_TABLE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        items.put(new ItemBuilder(Material.SMITHING_TABLE).amount(amount).build(), price);
        //GRINDSTONE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        items.put(new ItemBuilder(Material.GRINDSTONE).amount(amount).build(), price);
        //LOOM (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        items.put(new ItemBuilder(Material.LOOM).amount(amount).build(), price);
        //FURNACE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        items.put(new ItemBuilder(Material.FURNACE).amount(amount).build(), price);
        //SMOKER (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        items.put(new ItemBuilder(Material.SMOKER).amount(amount).build(), price);
        //BLAST_FURNACE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        items.put(new ItemBuilder(Material.BLAST_FURNACE).amount(amount).build(), price);
        //NOTE_BLOCK (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        items.put(new ItemBuilder(Material.NOTE_BLOCK).amount(amount).build(), price);
        //JUKEBOX (amount 1-3, price 70-150)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 150) + 70;
        items.put(new ItemBuilder(Material.JUKEBOX).amount(amount).build(), price);

        return items;
    }

}