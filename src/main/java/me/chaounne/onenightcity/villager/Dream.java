package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.game.Generator;
import me.chaounne.onenightcity.game.PoudreItem;
import me.chaounne.onenightcity.game.PlayerTracker;
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
    public LinkedHashMap<Material, Integer[]> getTrades() {
        List<MerchantRecipe> trades = new ArrayList<>();

        trades.add(new MerchantRecipe(Generator.getIronGenerator(), 1));
        trades.get(0).addIngredient(new ItemStack(Material.IRON_INGOT, 64));

        trades.add(new MerchantRecipe(Generator.getGoldGenerator(), 1));
        trades.get(1).addIngredient(new ItemStack(Material.GOLD_INGOT, 48));

        trades.add(new MerchantRecipe(Generator.getDiamondGenerator(), 1));
        trades.get(2).addIngredient(new ItemStack(Material.DIAMOND, 32));

        trades.add(new MerchantRecipe(Generator.getEmeraldGenerator(), 1));
        trades.get(3).addIngredient(new ItemStack(Material.EMERALD, 16));

        trades.add(new MerchantRecipe(PlayerTracker.getItem(), 1));
        trades.get(4).addIngredient(PoudreItem.getSuperPoudre(15));

        villager.setRecipes(trades);

        return null;
    }
}