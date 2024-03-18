package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.villager.spawners.SpawnerItems;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class DreamEntity {

    public static Villager getEntity(Location loc) {
        Villager dream = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        dream.setCustomName("Dream");
        dream.setCustomNameVisible(true);
        dream.setVillagerType(Villager.Type.SAVANNA);
        dream.setProfession(Villager.Profession.CARTOGRAPHER);
        dream.setAI(false);
        dream.setInvulnerable(true);
        dream.setSilent(true);
        dream.setCollidable(false);
        dream.setVillagerExperience(5);
        dream.setVillagerLevel(5);
        dream.setAdult();
        dream.setCanPickupItems(false);
        dream.setRemoveWhenFarAway(false);

        List<MerchantRecipe> trades = new ArrayList<>();

        ItemStack[] items = new ItemStack[4];
        items[0] = new ItemStack(Material.IRON_INGOT, 64);
        items[1] = new ItemStack(Material.GOLD_INGOT, 48);
        items[2] = new ItemStack(Material.DIAMOND, 32);
        items[3] = new ItemStack(Material.EMERALD, 16);

        //trade 1
        trades.add(new MerchantRecipe(SpawnerItems.getIronSpawner(), 1));
        trades.get(0).addIngredient(items[0]);
        
        //trade 2
        trades.add(new MerchantRecipe(SpawnerItems.getGoldSpawner(), 1));
        trades.get(1).addIngredient(items[1]);
        
        //trade 3
        trades.add(new MerchantRecipe(SpawnerItems.getDiamondSpawner(), 1));
        trades.get(2).addIngredient(items[2]);

        //trade 4
        trades.add(new MerchantRecipe(SpawnerItems.getEmeraldSpawner(), 1));
        trades.get(3).addIngredient(items[3]);

        dream.setRecipes(trades);

        return dream;
    }
}