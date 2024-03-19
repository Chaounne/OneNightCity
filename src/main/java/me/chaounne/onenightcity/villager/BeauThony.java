package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class BeauThony extends Trader {

    private static Trader instance;

    public BeauThony(Location loc) {
        super(loc, "Beau Thony", Villager.Type.JUNGLE, Villager.Profession.CARTOGRAPHER);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public LinkedHashMap<ItemStack, Integer> getTrades() {
        LinkedHashMap<ItemStack, Integer> items = new LinkedHashMap<>();

        items.put(new ItemBuilder(Material.ACACIA_SAPLING).amount(Random.between(3, 10)).build(), Random.between(50, 150));
        items.put(new ItemBuilder(Material.BAMBOO).amount(Random.between(5, 10)).build(), Random.between(5, 15));
        items.put(new ItemBuilder(Material.BIRCH_SAPLING).amount(Random.between(3, 12)).build(), Random.between(50, 150));
        items.put(new ItemBuilder(Material.DARK_OAK_SAPLING).amount(Random.between(3, 10)).build(), Random.between(50, 150));
        items.put(new ItemBuilder(Material.SPRUCE_SAPLING).amount(Random.between(3, 10)).build(), Random.between(50, 150));
        items.put(new ItemBuilder(Material.JUNGLE_SAPLING).amount(Random.between(3, 10)).build(), Random.between(50, 150));
        items.put(new ItemBuilder(Material.OAK_SAPLING).amount(Random.between(5, 10)).build(), Random.between(50, 150));
        items.put(new ItemBuilder(Material.FLOWER_POT).amount(Random.between(1, 10)).build(), Random.between(50, 150));
        items.put(new ItemBuilder(Material.MANGROVE_PROPAGULE).amount(Random.between(3, 10)).build(), Random.between(50, 150));
        items.put(new ItemBuilder(Material.AZALEA).amount(Random.between(1, 10)).build(), Random.between(50, 150));
        items.put(new ItemBuilder(Material.AZALEA_LEAVES).amount(Random.between(1, 10)).build(), Random.between(50, 150));

        return items;
    }

}