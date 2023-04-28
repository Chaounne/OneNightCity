package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.game.PoudreItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class HenryEntity1 {

    private static Villager henry1;

    public HenryEntity1(){

    }

    public static Villager getEntity(Location loc){
        henry1 = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        henry1.setCustomName("Henry1");
        henry1.setCustomNameVisible(true);
        henry1.setProfession(Villager.Profession.ARMORER);
        henry1.setAI(false);
        henry1.setInvulnerable(true);
        henry1.setSilent(true);
        henry1.setCollidable(false);
        henry1.setVillagerExperience(5);
        henry1.setVillagerLevel(5);
        henry1.setAdult();
        henry1.setCanPickupItems(false);
        henry1.setRemoveWhenFarAway(false);

        /**int i = 69
         * int alea = random 100
         * if alea = i
         * message everyone "Prix doublé pendant 5 minutes"
         * on double le prix des items donc au lieu de 2 melon pour 2 poudres => 2 melon pour 4 poudres
         * else
         * wait(1minutes)
         */
         List<MerchantRecipe> trades = new ArrayList<>();
        // random amount of poudre
        int amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 0));
        // random price
        int price = (int) (Math.random() * 5) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.DIRT, price));


        henry1.setRecipes(trades);

        //ce qu'henry propose
       // MerchantRecipe recipe = new MerchantRecipe(null, 0);
        //ce qu'il veut
      //  recipe.addIngredient(null);



        return henry1;
    }
}
