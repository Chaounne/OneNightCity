package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class SombreHeros extends Trader {

    private static Trader instance;

    public SombreHeros(Location loc) {
        super(loc, "Sombre Héros", Villager.Type.SWAMP, Villager.Profession.ARMORER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 70) + 20;
        items.put(new ItemBuilder(Material.SCULK).amount(amount).build(), price);

        amount = (int) (Math.random() * 2) + 1;
        price = (int) (Math.random() * 90) + 50;
        items.put(new ItemBuilder(Material.SCULK_VEIN).amount(amount).build(), price);

        amount = (int) (Math.random() * 2) + 1;
        price = (int) (Math.random() * 150) + 50;
        items.put(new ItemBuilder(Material.SCULK_CATALYST).amount(amount).build(), price);

        amount = (int) (Math.random() * 2) + 1;
        price = (int) (Math.random() * 150) + 50;
        items.put(new ItemBuilder(Material.SCULK_SENSOR).amount(amount).build(), price);

        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 10) + 1;
        items.put(new ItemBuilder(Material.COBWEB).amount(amount).build(), price);

        amount = 1;
        price = (int) (Math.random() * 5000) + 1000;
        items.put(new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).amount(amount).build(), price);

        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 8) + 1;
        items.put(new ItemBuilder(Material.DEEPSLATE_BRICKS).amount(amount).build(), price);

        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 200) + 50;
        items.put(new ItemBuilder(Material.CANDLE).amount(amount).build(), price);

        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 20) + 5;
        items.put(new ItemBuilder(Material.SOUL_LANTERN).amount(amount).build(), price);

        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 8) + 5;
        items.put(new ItemBuilder(Material.POLISHED_DEEPSLATE).amount(amount).build(), price);

        return items;
    }

}