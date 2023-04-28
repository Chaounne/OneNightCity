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

public class KilianMBoufféEntity {

    private static Villager MBouffe;

    public KilianMBoufféEntity(){

    }

    public static Villager getEntity(Location loc){
        MBouffe = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        MBouffe.setCustomName("MBouffe");
        MBouffe.setCustomNameVisible(true);
        MBouffe.setProfession(Villager.Profession.FARMER);
        MBouffe.setAI(false);
        MBouffe.setInvulnerable(true);
        MBouffe.setSilent(true);
        MBouffe.setCollidable(false);
        MBouffe.setVillagerExperience(5);
        MBouffe.setVillagerLevel(5);
        MBouffe.setAdult();
        MBouffe.setCanPickupItems(false);
        MBouffe.setRemoveWhenFarAway(false);

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
        trades.get(0).addIngredient(new ItemStack(Material.COOKED_BEEF, price));


        MBouffe.setRecipes(trades);

        //ce qu'MBouffe propose
        //MerchantRecipe recipe = new MerchantRecipe(null, 0);
        //ce qu'il veut
        //recipe.addIngredient(null);



        return MBouffe;
    }
}
