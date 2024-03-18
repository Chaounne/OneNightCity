package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class DurifSylvain extends Trader {

    private static Trader instance;

    public DurifSylvain(Location loc) {
        super(loc, "Civique Cosmique Durif", Villager.Type.SWAMP, Villager.Profession.LEATHERWORKER, 18);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        //CHORUS_FRUIT (amount 1-3, price 15-35)
        int amount = (int) (Math.random() * 3) + 1;
        int price = (int) (Math.random() * 21) + 15;
        inventory.addItem(new ItemBuilder(Material.CHORUS_FRUIT).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //CHORUS_FLOWER (amount 1-3, price 10-30)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 21) + 10;
        inventory.addItem(new ItemBuilder(Material.CHORUS_FLOWER).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //PURPUR_BLOCK (amount 1-3, price 50-80)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 31) + 50;
        inventory.addItem(new ItemBuilder(Material.PURPUR_BLOCK).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //PURPUR_PILLAR (amount 1-3, price 50-80)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 31) + 50;
        inventory.addItem(new ItemBuilder(Material.PURPUR_PILLAR).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //PURPUR_SLAB (amount 1-3, price 50-60)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 11) + 50;
        inventory.addItem(new ItemBuilder(Material.PURPUR_SLAB).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //PURPUR_STAIRS (amount 1-3, price 50-60)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 11) + 50;
        inventory.addItem(new ItemBuilder(Material.PURPUR_STAIRS).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //END_STONE_BRICKS (amount 5-10, price 10-50)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 41) + 10;
        inventory.addItem(new ItemBuilder(Material.END_STONE_BRICKS).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //END_STONE_BRICK_SLAB (amount 5-10, price 10-25)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 16) + 10;
        inventory.addItem(new ItemBuilder(Material.END_STONE_BRICK_SLAB).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //END_STONE_BRICK_STAIRS (amount 5-10, price 10-50)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 41) + 10;
        inventory.addItem(new ItemBuilder(Material.END_STONE_BRICK_STAIRS).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //END_STONE_BRICK_WALL (amount 5-10, price 10-50)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 41) + 10;
        inventory.addItem(new ItemBuilder(Material.END_STONE_BRICK_WALL).amount(amount).addLore(price + " Poudres").build(), 9, price);
        //END_CRYSTAL (amount 1, price 100-500)
        amount = 1;
        price = (int) (Math.random() * 401) + 100;
        inventory.addItem(new ItemBuilder(Material.END_CRYSTAL).amount(amount).addLore(price + " Poudres").build(), 10, price);
        //END_ROD (amount 1-5, price 5-110)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 106) + 5;
        inventory.addItem(new ItemBuilder(Material.END_ROD).amount(amount).addLore(price + " Poudres").build(), 11, price);
        //OBSIDIAN (amount 1-10, price 50-100)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 51) + 50;
        inventory.addItem(new ItemBuilder(Material.OBSIDIAN).amount(amount).addLore(price + " Poudres").build(), 12, price);
    }

}