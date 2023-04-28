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

public class HenryEntity {

    private static Villager henry;

    public HenryEntity(){

    }

    public static Villager getEntity(Location loc){
        henry = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        henry.setCustomName("Henry");
        henry.setCustomNameVisible(true);
        henry.setProfession(Villager.Profession.FARMER);
        henry.setAI(false);
        //henry.setInvulnerable(true);
        henry.setSilent(true);
        henry.setCollidable(false);
        henry.setVillagerExperience(5);
        henry.setVillagerLevel(5);
        henry.setAdult();
        henry.setCanPickupItems(false);
        henry.setRemoveWhenFarAway(false);

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
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        int price = (int) (Math.random() * 5) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.MELON, price));


        henry.setRecipes(trades);

        //ce qu'henry propose
        //MerchantRecipe recipe = new MerchantRecipe(null, 0);
        //ce qu'il veut
        //recipe.addIngredient(null);



        return henry;
    }
}
