package me.chaounne.onenightcity.villager;


import me.chaounne.onenightcity.game.PoudreItem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class DarkHenry {

    public static void create(Location loc) {
        Villager henry = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        henry.setCustomName(ChatColor.DARK_RED + "DARKHenry");
        henry.setCustomNameVisible(true);
        henry.setProfession(Villager.Profession.WEAPONSMITH);
        henry.setAI(false);
        henry.setSilent(true);
        henry.setVillagerLevel(5);
        henry.setCanPickupItems(false);
        henry.setRemoveWhenFarAway(false);

        List<ItemStack> items = new ArrayList<>();
        items.add(new ItemStack(Material.ENCHANTING_TABLE));
        items.add(new ItemStack(Material.LEGACY_EYE_OF_ENDER));
        items.add(new ItemStack(Material.END_CRYSTAL));
        items.add(new ItemStack(Material.DRAGON_HEAD));
        items.add(new ItemStack(Material.BLAZE_POWDER));
        items.add(new ItemStack(Material.MAGMA_CREAM));
        items.add(new ItemStack(Material.SHULKER_SHELL));
        items.add(new ItemStack(Material.NAME_TAG));
        items.add(new ItemStack(Material.HONEY_BOTTLE));
        items.add(new ItemStack(Material.GLOW_INK_SAC));

        List<MerchantRecipe> trades = new ArrayList<>();
        ItemStack tradeItem = items.get((int) (Math.random() * items.size()));
        trades.add(new MerchantRecipe(PoudreItem.getSuperPoudre(5), 1));
        trades.get(0).addIngredient(tradeItem);
        henry.setRecipes(trades);

    }

}