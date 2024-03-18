package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class Jeaneau extends Trader {

    private static Trader instance;

    public Jeaneau(Location loc) {
        super(loc, "Jeaneau", Villager.Type.PLAINS, Villager.Profession.FISHERMAN, 9);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        //PRISMARINE (amount 1-15, price 10-50)
        int amount = (int) (Math.random() * 15) + 1;
        int price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.PRISMARINE).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //PRISMARINE_SHARD (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.PRISMARINE_SHARD).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //PRISMARINE_BRICKS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.PRISMARINE_BRICKS).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //PRISMARINE_CRYSTALS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.PRISMARINE_CRYSTALS).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //PRISMARINE_BRICK_SLAB (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.PRISMARINE_BRICK_SLAB).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //PRISMARINE_BRICK_STAIRS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.PRISMARINE_BRICK_STAIRS).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //PRISMARINE_SLAB (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.PRISMARINE_SLAB).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //PRISMARINE_STAIRS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.PRISMARINE_STAIRS).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //PRISMARINE_WALL (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.PRISMARINE_WALL).amount(amount).addLore(price + " Poudres").build(), 8, price);
    }

}