package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class Pfizer extends Trader {

    private static Trader instance;

    private Pfizer(Location loc) {
        super(loc, "Dr. Raoult", Villager.Type.DESERT, Villager.Profession.CLERIC, 18);
        instance = this;
    }

    public static void create(Location loc) {
        if (instance == null)
            new Pfizer(loc);
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        //SPIDER_EYE (amount 1-10, price 1-40)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 40) + 1;
        inventory.addItem(new ItemBuilder(Material.SPIDER_EYE).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //ROTTEN_FLESH (amount 1-10, price 1-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 1;
        inventory.addItem(new ItemBuilder(Material.ROTTEN_FLESH).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //BONE (amount 1-10, price 1-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 1;
        inventory.addItem(new ItemBuilder(Material.BONE).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //BONE_BLOCK (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        inventory.addItem(new ItemBuilder(Material.BONE_BLOCK).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //BONE_MEAL (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        inventory.addItem(new ItemBuilder(Material.BONE_MEAL).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //GUNPOWDER (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        inventory.addItem(new ItemBuilder(Material.GUNPOWDER).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //RABBIT_FOOT (amount 1, price 100-200)
        amount = 1;
        price = (int) (Math.random() * 200) + 100;
        inventory.addItem(new ItemBuilder(Material.RABBIT_FOOT).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //LEATHER (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        inventory.addItem(new ItemBuilder(Material.LEATHER).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //RABBIT_HIDE (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        inventory.addItem(new ItemBuilder(Material.RABBIT_HIDE).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //SLIME_BALL (amount 1-10, price 10-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 10;
        inventory.addItem(new ItemBuilder(Material.SLIME_BALL).amount(amount).addLore(price + " Poudres").build(), 9, price);
        //SCUTE (amount 1, price 250-950)
        amount = 1;
        price = (int) (Math.random() * 950) + 250;
        inventory.addItem(new ItemBuilder(Material.SCUTE).amount(amount).addLore(price + " Poudres").build(), 10, price);
    }

}