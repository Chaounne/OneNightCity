package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class DrRaoult extends Trader {

    private static Trader instance;

    public DrRaoult(Location loc) {
        super(loc, "Dr. Raoult", Villager.Type.DESERT, Villager.Profession.CLERIC);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //SPIDER_EYE (amount 1-10, price 1-40)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 40) + 1;
        items.put(new ItemBuilder(Material.SPIDER_EYE).amount(amount).build(), price);
        //ROTTEN_FLESH (amount 1-10, price 1-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 1;
        items.put(new ItemBuilder(Material.ROTTEN_FLESH).amount(amount).build(), price);
        //BONE (amount 1-10, price 1-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 1;
        items.put(new ItemBuilder(Material.BONE).amount(amount).build(), price);
        //BONE_BLOCK (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        items.put(new ItemBuilder(Material.BONE_BLOCK).amount(amount).build(), price);
        //BONE_MEAL (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        items.put(new ItemBuilder(Material.BONE_MEAL).amount(amount).build(), price);
        //GUNPOWDER (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        items.put(new ItemBuilder(Material.GUNPOWDER).amount(amount).build(), price);
        //RABBIT_FOOT (amount 1, price 100-200)
        amount = 1;
        price = (int) (Math.random() * 200) + 100;
        items.put(new ItemBuilder(Material.RABBIT_FOOT).amount(amount).build(), price);
        //LEATHER (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        items.put(new ItemBuilder(Material.LEATHER).amount(amount).build(), price);
        //RABBIT_HIDE (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        items.put(new ItemBuilder(Material.RABBIT_HIDE).amount(amount).build(), price);
        //SLIME_BALL (amount 1-10, price 10-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 10;
        items.put(new ItemBuilder(Material.SLIME_BALL).amount(amount).build(), price);
        //SCUTE (amount 1, price 250-950)
        amount = 1;
        price = (int) (Math.random() * 950) + 250;
        items.put(new ItemBuilder(Material.SCUTE).amount(amount).build(), price);

        return items;
    }

}