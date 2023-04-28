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

public class LesPierresEntity {

    private static Villager aypierre;

    public LesPierresEntity(){

    }

    public static Villager getEntity(Location loc){
        aypierre = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        aypierre.setCustomName("Les Pierres");
        aypierre.setCustomNameVisible(true);
        aypierre.setVillagerType(Villager.Type.SWAMP);
        aypierre.setProfession(Villager.Profession.MASON);
        aypierre.setAI(false);
        //henry3.setInvulnerable(true);
        aypierre.setSilent(true);
        aypierre.setCollidable(false);
        aypierre.setVillagerExperience(5);
        aypierre.setVillagerLevel(5);
        aypierre.setAdult();
        aypierre.setCanPickupItems(false);
        aypierre.setRemoveWhenFarAway(false);

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
        trades.get(0).addIngredient(new ItemStack(Material.REDSTONE_BLOCK, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 0));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.COMPARATOR, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 0));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.TARGET, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 0));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.HOPPER, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 0));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.DISPENSER, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 0));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.DROPPER, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 0));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.OBSERVER, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 0));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.PISTON, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 0));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.STICKY_PISTON, price));

        //trade 10
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 0));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(9).addIngredient(new ItemStack(Material.TRIPWIRE_HOOK, price));


        aypierre.setRecipes(trades);

        //ce qu'henry3 propose
        //MerchantRecipe recipe = new MerchantRecipe(null, 0);
        //ce qu'il veut
        //recipe.addIngredient(null);



        return aypierre;
    }
}