package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class Ikikomori extends Trader {

    private static Trader instance;

    public Ikikomori(Location loc) {
        super(loc, "Ikikomori", Villager.Type.PLAINS, Villager.Profession.TOOLSMITH);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //MOSS_BLOCK (amount 1-15, price 2-35)
        int amount = (int) (Math.random() * 15) + 1;
        int price = (int) (Math.random() * 35) + 2;
        items.put(new ItemBuilder(Material.MOSS_BLOCK).amount(amount).build(), price);
        //MOSS_CARPET (amount 1-15, price 2-25)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 35) + 2;
        items.put(new ItemBuilder(Material.MOSS_CARPET).amount(amount).build(), price);
        //OCHRE_FROGLIGHT (amount 1-5, price 50-150)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 150) + 50;
        items.put(new ItemBuilder(Material.OCHRE_FROGLIGHT).amount(amount).build(), price);
        //PEARLESCENT_FROGLIGHT (amount 1-5, price 50-150)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 150) + 50;
        items.put(new ItemBuilder(Material.PEARLESCENT_FROGLIGHT).amount(amount).build(), price);
        //VERDANT_FROGLIGHT (amount 1-5, price 50-150)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 150) + 50;
        items.put(new ItemBuilder(Material.VERDANT_FROGLIGHT).amount(amount).build(), price);
        //AXOLOTL_BUCKET (amount 1, price 20-50)
        amount = 1;
        price = (int) (Math.random() * 50) + 20;
        items.put(new ItemBuilder(Material.AXOLOTL_BUCKET).amount(amount).build(), price);
        //MANGROVE_PLANKS (amount 1-20, price 5-10)
        amount = (int) (Math.random() * 20) + 1;
        price = (int) (Math.random() * 15) + 5;
        items.put(new ItemBuilder(Material.MANGROVE_PLANKS).amount(amount).build(), price);
        //MANGROVE_LEAVES (amount 1-10, price 4-10)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 15) + 4;
        items.put(new ItemBuilder(Material.MANGROVE_LEAVES).amount(amount).build(), price);

        return items;
    }

}