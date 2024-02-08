package me.chaounne.onenightcity.villager;


import me.chaounne.onenightcity.game.PoudreItem;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class DarkHenryEntity {

    public static Villager henry2;

    public DarkHenryEntity() {

    }

    public static void removeEntity() {
        if (henry2 != null) {
            henry2.remove();
            henry2 = null;
        }
    }

    public static Villager getEntity(Location loc) {
        henry2 = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        henry2.setCustomName(ChatColor.DARK_RED + "DARKHenry");
        henry2.setCustomNameVisible(true);
        henry2.setProfession(Villager.Profession.WEAPONSMITH);
        henry2.setAI(false);
        henry2.setSilent(true);
        henry2.setCollidable(false);
        henry2.setVillagerExperience(5);
        henry2.setVillagerLevel(5);
       // henry2.setInvulnerable(true);
        henry2.setAdult();
        henry2.setCanPickupItems(false);
        henry2.setRemoveWhenFarAway(false);

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
        henry2.setRecipes(trades);

        return henry2;
    }



}

