package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.villager.spawners.SpawnerItems;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Dream extends Trader {

    public Dream(Location loc) {
        super(loc, "Dream", Villager.Type.SAVANNA, Villager.Profession.CARTOGRAPHER);
    }

    @Override
    protected LinkedHashMap<ItemStack, Integer> getTrades() {
        List<MerchantRecipe> trades = new ArrayList<>();

        trades.add(new MerchantRecipe(SpawnerItems.getIronSpawner(), 1));
        trades.get(0).addIngredient(new ItemStack(Material.IRON_INGOT, 64));

        trades.add(new MerchantRecipe(SpawnerItems.getGoldSpawner(), 1));
        trades.get(1).addIngredient(new ItemStack(Material.GOLD_INGOT, 48));

        trades.add(new MerchantRecipe(SpawnerItems.getDiamondSpawner(), 1));
        trades.get(2).addIngredient(new ItemStack(Material.DIAMOND, 32));

        trades.add(new MerchantRecipe(SpawnerItems.getEmeraldSpawner(), 1));
        trades.get(3).addIngredient(new ItemStack(Material.EMERALD, 16));

        villager.setRecipes(trades);

        return null;
    }
}