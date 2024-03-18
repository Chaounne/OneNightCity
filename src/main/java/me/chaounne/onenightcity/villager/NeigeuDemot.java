package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class NeigeuDemot extends Trader {

    private static Trader instance;

    public NeigeuDemot(Location loc) {
        super(loc, "Négeux Demo", Villager.Type.SNOW, Villager.Profession.MASON, 9);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        //SNOWBALL (amount 1-5, price 1-15)
        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 15) + 1;
        inventory.addItem(new ItemBuilder(Material.SNOWBALL).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //POWDER_SNOW_BUCKET (amount 1, price 1-15)
        amount = 1;
        price = (int) (Math.random() * 15) + 1;
        inventory.addItem(new ItemBuilder(Material.POWDER_SNOW_BUCKET).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //SNOW (amount 1-5, price 5-25)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 25) + 5;
        inventory.addItem(new ItemBuilder(Material.SNOW).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //SNOW_BLOCK (amount 1-5, price 5-30)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 60) + 4;
        inventory.addItem(new ItemBuilder(Material.SNOW_BLOCK).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //CARVED_PUMPKIN (amount 1-5, price 2-25)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 25) + 2;
        inventory.addItem(new ItemBuilder(Material.CARVED_PUMPKIN).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //ICE (amount 1-5, price 1-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 1;
        inventory.addItem(new ItemBuilder(Material.ICE).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //BLUE_ICE (amount 1-5, price 1-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 1;
        inventory.addItem(new ItemBuilder(Material.BLUE_ICE).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //PACKED_ICE (amount 1-5, price 1-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 1;
        inventory.addItem(new ItemBuilder(Material.PACKED_ICE).amount(amount).addLore(price + " Poudres").build(), 7, price);
    }

}