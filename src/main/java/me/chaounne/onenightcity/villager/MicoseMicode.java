package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class MicoseMicode extends Trader {

    private static Trader instance;

    public MicoseMicode(Location loc) {
        super(loc, "Micose Micode", Villager.Type.SWAMP, Villager.Profession.FLETCHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        //MYCELIUM (amount 1-5, price 50-200)
        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 200) + 50;
        items.put(new ItemBuilder(Material.MYCELIUM).amount(amount).build(), price);
        //RED_MUSHROOM_BLOCK (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        items.put(new ItemBuilder(Material.RED_MUSHROOM_BLOCK).amount(amount).build(), price);
        //BROWN_MUSHROOM_BLOCK (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        items.put(new ItemBuilder(Material.BROWN_MUSHROOM_BLOCK).amount(amount).build(), price);
        //MUSHROOM_STEM (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        items.put(new ItemBuilder(Material.MUSHROOM_STEM).amount(amount).build(), price);
        //SHROOMLIGHT (amount 1-5, price 20-50)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 20;
        items.put(new ItemBuilder(Material.SHROOMLIGHT).amount(amount).build(), price);

        return items;
    }

}
