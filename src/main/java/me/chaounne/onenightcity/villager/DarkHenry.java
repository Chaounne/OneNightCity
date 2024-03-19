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
        possibleItems.add(new ItemStack(Material.ENDER_EYE));
        possibleItems.add(new ItemStack(Material.END_CRYSTAL));
        possibleItems.add(new ItemStack(Material.DRAGON_HEAD));
        possibleItems.add(new ItemStack(Material.BLAZE_POWDER));
        possibleItems.add(new ItemStack(Material.MAGMA_CREAM));
        possibleItems.add(new ItemStack(Material.SHULKER_SHELL));
        possibleItems.add(new ItemStack(Material.NAME_TAG));
        possibleItems.add(new ItemStack(Material.HONEY_BOTTLE));
        possibleItems.add(new ItemStack(Material.GLOW_INK_SAC));

        MerchantRecipe recipe = new MerchantRecipe(PoudreItem.getSuperPoudre(5), 1);
        recipe.addIngredient(possibleItems.get((int) (Math.random() * possibleItems.size())));

        villager.setRecipe(0, recipe);

        return null;
    }

}