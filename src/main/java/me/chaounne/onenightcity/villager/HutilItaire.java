package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class HutilItaire extends Trader {

    private static Trader instance;

    public HutilItaire(Location loc) {
        super(loc, "Hutil Itaire", Villager.Type.PLAINS, Villager.Profession.CARTOGRAPHER, 18);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        //CRAFTING_TABLE (amount 1-3, price 2-15)
        int amount = (int) (Math.random() * 3) + 1;
        int price = (int) (Math.random() * 15) + 2;
        inventory.addItem(new ItemBuilder(Material.CRAFTING_TABLE).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //STONECUTTER (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        inventory.addItem(new ItemBuilder(Material.STONECUTTER).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //CARTOGRAPHY_TABLE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 31) + 20;
        inventory.addItem(new ItemBuilder(Material.CARTOGRAPHY_TABLE).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //FLETCHING_TABLE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        inventory.addItem(new ItemBuilder(Material.FLETCHING_TABLE).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //SMITHING_TABLE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        inventory.addItem(new ItemBuilder(Material.SMITHING_TABLE).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //GRINDSTONE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        inventory.addItem(new ItemBuilder(Material.GRINDSTONE).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //LOOM (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        inventory.addItem(new ItemBuilder(Material.LOOM).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //FURNACE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        inventory.addItem(new ItemBuilder(Material.FURNACE).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //SMOKER (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        inventory.addItem(new ItemBuilder(Material.SMOKER).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //BLAST_FURNACE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        inventory.addItem(new ItemBuilder(Material.BLAST_FURNACE).amount(amount).addLore(price + " Poudres").build(), 9, price);
        //NOTE_BLOCK (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        inventory.addItem(new ItemBuilder(Material.NOTE_BLOCK).amount(amount).addLore(price + " Poudres").build(), 10, price);
        //JUKEBOX (amount 1-3, price 70-150)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 150) + 70;
        inventory.addItem(new ItemBuilder(Material.JUKEBOX).amount(amount).addLore(price + " Poudres").build(), 11, price);
    }

}