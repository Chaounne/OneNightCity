package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class Legias extends Trader {

    private static Trader instance;

    public Legias(Location loc) {
        super(loc, "Legias", Villager.Type.SWAMP, Villager.Profession.FLETCHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //ARROW (amount 4-16, price 20-30)
        int amount = (int) (Math.random() * 13) + 4;
        int price = (int) (Math.random() * 30) + 20;
        items.put(new ItemBuilder(Material.ARROW).amount(amount).build(), price);
        //BOW (amount 1, price 10-200)
        amount = 1;
        price = (int) (Math.random() * 200) + 10;
        items.put(new ItemBuilder(Material.BOW).amount(amount).build(), price);
        //SPECTRAL_ARROW (amount 4-16, price 10-150)
        amount = (int) (Math.random() * 13) + 4;
        price = (int) (Math.random() * 150) + 10;
        items.put(new ItemBuilder(Material.SPECTRAL_ARROW).amount(amount).build(), price);
        //CROSSBOW (amount 1, price 40-250)
        amount = 1;
        price = (int) (Math.random() * 250) + 40;
        items.put(new ItemBuilder(Material.CROSSBOW).amount(amount).build(), price);
        //BIG_DRIPLEAF (amount 1-10, price 15-30)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 30) + 15;
        items.put(new ItemBuilder(Material.BIG_DRIPLEAF).amount(amount).build(), price);
        //SMALL_DRIPLEAF (amount 1-10, price 15-30)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 30) + 15;
        items.put(new ItemBuilder(Material.SMALL_DRIPLEAF).amount(amount).build(), price);
        //SPORE_BLOSSOM (amount 1-5, price 40-250)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 250) + 40;
        items.put(new ItemBuilder(Material.SPORE_BLOSSOM).amount(amount).build(), price);
        //VINE (amount 1-10, price 15-30)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 30) + 15;
        items.put(new ItemBuilder(Material.VINE).amount(amount).build(), price);
        //LILY_PAD (amount 1-10, price 15-30)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 30) + 15;
        items.put(new ItemBuilder(Material.LILY_PAD).amount(amount).build(), price);

        return items;
    }

}