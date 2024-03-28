package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.game.PoudreItem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DarkHenry extends Trader {

    public DarkHenry(Location loc) {
        super(loc, ChatColor.DARK_RED + "Dark Henry", null, Villager.Profession.WEAPONSMITH);
    }

    @Override
    public LinkedHashMap<Material, Integer[]> getTrades() {
        List<ItemStack> possibleItems = new ArrayList<>();
        possibleItems.add(new ItemStack(Material.ENCHANTING_TABLE));
        possibleItems.add(new ItemStack(Material.GHAST_TEAR));
        possibleItems.add(new ItemStack(Material.END_CRYSTAL));
        possibleItems.add(new ItemStack(Material.DRAGON_HEAD));
        possibleItems.add(new ItemStack(Material.HEART_OF_THE_SEA));
        possibleItems.add(new ItemStack(Material.SHULKER_SHELL));

        MerchantRecipe recipe = new MerchantRecipe(PoudreItem.getSuperPoudre(20), 1);
        recipe.addIngredient(possibleItems.get((int) (Math.random() * possibleItems.size())));

        villager.setRecipe(0, recipe);

        return null;
    }

}