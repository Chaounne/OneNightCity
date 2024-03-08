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

    private static Villager dream;

    public DreamEntity(){

    }

    public static Villager getEntity(Location loc){
        dream = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

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

        /**int i = 69
         * int alea = random 100
         * if alea = i
         * message everyone "Prix doublé pendant 5 minutes"
         * on double le prix des items donc au lieu de 2 melon pour 2 poudres => 2 melon pour 4 poudres
         * else
         * wait(1minutes)
         */
         List<MerchantRecipe> trades = new ArrayList<>();

         ItemStack[] items = new ItemStack[3];
        int amount = (int) (Math.random() * 30) + 30;
        if(amount > 64) amount = 64;
        items[0] = new ItemStack(Material.IRON_INGOT, amount);
        amount = (int) (Math.random() * 25) + 25;
        if(amount > 64) amount = 64;
        items[1] = new ItemStack(Material.GOLD_INGOT, amount);
        amount = (int) (Math.random() * 20) + 20;
        if(amount > 64) amount = 64;
        items[2] = new ItemStack(Material.DIAMOND, amount);

        //trade 1
        trades.add(new MerchantRecipe(SpawnerItems.getDiamondSpawner(), Integer.MAX_VALUE));
        trades.get(0).addIngredient(items[2]);

        //trade 2
        trades.add(new MerchantRecipe(SpawnerItems.getGoldSpawner(), Integer.MAX_VALUE));
        trades.get(1).addIngredient(items[1]);

        //trade 3
        trades.add(new MerchantRecipe(SpawnerItems.getIronSpawner(), Integer.MAX_VALUE));
        trades.get(2).addIngredient(items[0]);

        //trade 4
        trades.add(new MerchantRecipe(SpawnerItems.getEmeraldSpawner(), Integer.MAX_VALUE));
        trades.get(3).addIngredient(items[(int) (Math.random() * 3)]);

        dream.setRecipes(trades);

        return dream;
    }
}
