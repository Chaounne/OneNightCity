package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class JeanMineur extends Trader {

    private static Trader instance;

    public JeanMineur(Location loc) {
        super(loc, "Jean Mineur", Villager.Type.DESERT, Villager.Profession.LEATHERWORKER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //COAL (amount 1-15, price 10-50)
        int amount = (int) (Math.random() * 15) + 1;
        int price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.COAL).amount(amount).build(), price);
        //DEEPSLATE_COAL_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 1000) + 500;
        items.put(new ItemBuilder(Material.DEEPSLATE_COAL_ORE).amount(amount).build(), price);
        //IRON_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.IRON_ORE).amount(amount).build(), price);
        //DEEPSLATE_IRON_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        items.put(new ItemBuilder(Material.DEEPSLATE_IRON_ORE).amount(amount).build(), price);
        //COPPER_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.COPPER_ORE).amount(amount).build(), price);
        //DEEPSLATE_COPPER_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.DEEPSLATE_COPPER_ORE).amount(amount).build(), price);
        //GOLD_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        items.put(new ItemBuilder(Material.GOLD_ORE).amount(amount).build(), price);
        //DEEPSLATE_GOLD_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        items.put(new ItemBuilder(Material.DEEPSLATE_GOLD_ORE).amount(amount).build(), price);
        //REDSTONE_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 500) + 50;
        items.put(new ItemBuilder(Material.REDSTONE_ORE).amount(amount).build(), price);
        //DEEPSLATE_REDSTONE_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 10;
        items.put(new ItemBuilder(Material.DEEPSLATE_REDSTONE_ORE).amount(amount).build(), price);
        //EMERALD_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 0) + 1;
        price = (int) (Math.random() * 1000) + 500;
        items.put(new ItemBuilder(Material.EMERALD_ORE).amount(amount).build(), price);
        //DEEPSLATE_EMERALD_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 0) + 1;
        price = (int) (Math.random() * 1000) + 500;
        items.put(new ItemBuilder(Material.DEEPSLATE_EMERALD_ORE).amount(amount).build(), price);
        //LAPIS_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 500) + 50;
        items.put(new ItemBuilder(Material.LAPIS_ORE).amount(amount).build(), price);
        //DEEPSLATE_LAPIS_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 500) + 50;
        items.put(new ItemBuilder(Material.DEEPSLATE_LAPIS_ORE).amount(amount).build(), price);
        //DIAMOND_ORE (amount 1-5, price 50-300)
        amount = (int) (Math.random() * 0) + 1;
        price = (int) (Math.random() * 1000) + 500;
        items.put(new ItemBuilder(Material.DIAMOND_ORE).amount(amount).build(), price);
        //DEEPSLATE_DIAMOND_ORE (amount 1-5, price 50-300)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        items.put(new ItemBuilder(Material.DEEPSLATE_DIAMOND_ORE).amount(amount).build(), price);
        //NETHER_GOLD_ORE (amount 1-5, price 50-150)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 10;
        items.put(new ItemBuilder(Material.NETHER_GOLD_ORE).amount(amount).build(), price);
        //ANCIENT_DEBRIS (amount 1-3, price 100-500)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 1000) + 500;
        items.put(new ItemBuilder(Material.ANCIENT_DEBRIS).amount(amount).build(), price);

        return items;
    }

}