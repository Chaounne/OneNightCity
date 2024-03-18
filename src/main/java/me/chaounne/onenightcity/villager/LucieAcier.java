package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class LucieAcier extends Trader {

    private static Trader instance;

    public LucieAcier(Location loc) {
        super(loc, "Lucie Acier", Villager.Type.JUNGLE, Villager.Profession.BUTCHER, 18);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        //NETHER_WART (amount 1-10, price 10-40)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 40) + 10;
        inventory.addItem(new ItemBuilder(Material.NETHER_WART).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //NETHER_WART_BLOCK (amount 1-10, price 1-25)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 25) + 1;
        inventory.addItem(new ItemBuilder(Material.NETHER_WART_BLOCK).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //WARPED_WART_BLOCK (amount 1-10, price 1-25)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 25) + 1;
        inventory.addItem(new ItemBuilder(Material.WARPED_WART_BLOCK).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //WARPED_FUNGUS (amount 1-10, price 1-25)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 25) + 1;
        inventory.addItem(new ItemBuilder(Material.WARPED_FUNGUS).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //WARPED_FUNGUS_ON_A_STICK (amount 1, price 10-100)
        amount = 1;
        price = (int) (Math.random() * 100) + 10;
        inventory.addItem(new ItemBuilder(Material.WARPED_FUNGUS_ON_A_STICK).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //QUARTZ_BLOCK (amount 1-5, price 20-50)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 20;
        inventory.addItem(new ItemBuilder(Material.QUARTZ_BLOCK).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //QUARTZ (amount 1-5, price 10-40)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 40) + 10;
        inventory.addItem(new ItemBuilder(Material.QUARTZ).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //NETHER_BRICK (amount 2-3, price 15-20)
        amount = (int) (Math.random() * 2) + 2;
        price = (int) (Math.random() * 6) + 15;
        inventory.addItem(new ItemBuilder(Material.NETHER_BRICK).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //NETHER_BRICK_FENCE (amount 1-10, price 10-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 10;
        inventory.addItem(new ItemBuilder(Material.NETHER_BRICK_FENCE).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //NETHER_BRICK_SLAB (amount 1-10, price 10-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 10;
        inventory.addItem(new ItemBuilder(Material.NETHER_BRICK_SLAB).amount(amount).addLore(price + " Poudres").build(), 9, price);
        //NETHER_BRICK_STAIRS (amount 1-10, price 10-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 10;
        inventory.addItem(new ItemBuilder(Material.NETHER_BRICK_STAIRS).amount(amount).addLore(price + " Poudres").build(), 10, price);
        //NETHERITE_SCRAP (amount 1-3, price 100-600)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 600) + 100;
        inventory.addItem(new ItemBuilder(Material.NETHERITE_SCRAP).amount(amount).addLore(price + " Poudres").build(), 11, price);
        //NETHERITE_INGOT (amount 1-3, price 100-800)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 800) + 100;
        inventory.addItem(new ItemBuilder(Material.NETHERITE_INGOT).amount(amount).addLore(price + " Poudres").build(), 12, price);
    }

}