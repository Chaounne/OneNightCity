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

public class HenryEntity3 {

    private static Villager henry3;

    public HenryEntity3(){

    }

    public static Villager getEntity(Location loc){
        henry3 = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        henry3.setCustomName("Henry33");
        henry3.setCustomNameVisible(true);
        henry3.setProfession(Villager.Profession.FARMER);
        henry3.setAI(false);
        //henry3.setInvulnerable(true);
        henry3.setSilent(true);
        henry3.setCollidable(false);
        henry3.setVillagerExperience(5);
        henry3.setVillagerLevel(5);
        henry3.setAdult();
        henry3.setCanPickupItems(false);
        henry3.setRemoveWhenFarAway(false);

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
        trades.get(0).addIngredient(new ItemStack(Material.TNT_MINECART, price));


        henry3.setRecipes(trades);

        //ce qu'henry3 propose
        //MerchantRecipe recipe = new MerchantRecipe(null, 0);
        //ce qu'il veut
        //recipe.addIngredient(null);



        return henry3;
    }
}
