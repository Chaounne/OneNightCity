package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class FrancisClodo extends Trader {

    private static Trader instance;

    public FrancisClodo(Location loc) {
        super(loc, "Francis Clodo", Villager.Type.SWAMP, Villager.Profession.BUTCHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        items.put(new ItemBuilder(Material.GLASS_BOTTLE).amount(Random.between(1, 10)).build(), Random.between(5, 20));
        items.put(new ItemBuilder(Material.HONEY_BOTTLE).amount(Random.between(1, 5)).build(), Random.between(15, 30));
        items.put(new ItemBuilder(Material.MILK_BUCKET).amount(Random.between(1, 1)).build(), Random.between(20, 250));
        items.put(new ItemBuilder(Material.BEETROOT_SOUP).amount(Random.between(1, 1)).build(), Random.between(50, 200));
        items.put(new ItemBuilder(Material.MUSHROOM_STEW).amount(Random.between(1, 1)).build(), Random.between(15, 35));
        items.put(new ItemBuilder(Material.ENCHANTING_TABLE).amount(Random.between(1, 2)).build(), Random.between(750, 1500));
        items.put(new ItemBuilder(Material.GHAST_TEAR).amount(Random.between(1, 1)).build(), Random.between(2000, 4000));
        items.put(new ItemBuilder(Material.HEART_OF_THE_SEA).amount(Random.between(1, 1)).build(), Random.between(5000, 10000));
        items.put(new ItemBuilder(Material.NAME_TAG).amount(Random.between(1, 1)).build(), Random.between(3000, 5000));
        items.put(new ItemBuilder(Material.SADDLE).amount(Random.between(1, 1)).build(), Random.between(2000, 4000));
        items.put(new ItemBuilder(Material.DIAMOND_HORSE_ARMOR).amount(Random.between(1, 1)).build(), Random.between(2000, 4000));
        items.put(new ItemBuilder(Material.GOLDEN_HORSE_ARMOR).amount(Random.between(1, 1)).build(), Random.between(1500, 3000));
        items.put(new ItemBuilder(Material.IRON_HORSE_ARMOR).amount(Random.between(1, 1)).build(), Random.between(1000, 2000));
        items.put(new ItemBuilder(Material.LEATHER_HORSE_ARMOR).amount(Random.between(1, 1)).build(), Random.between(1000, 1500));
        items.put(new ItemBuilder(Material.CHAINMAIL_HELMET).amount(Random.between(1, 1)).build(), Random.between(1500, 3000));
        items.put(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).amount(Random.between(1, 1)).build(), Random.between(1500, 3000));
        items.put(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).amount(Random.between(1, 1)).build(), Random.between(1500, 3000));
        items.put(new ItemBuilder(Material.CHAINMAIL_BOOTS).amount(Random.between(1, 1)).build(), Random.between(1500, 3000));

        return items;
    }

}