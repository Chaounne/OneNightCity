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

public class ClodoFrancisEntity {

    private static Villager potter;

    public ClodoFrancisEntity(){

    }

    public static Villager getEntity(Location loc){
        potter = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        potter.setCustomName("Francis Clodo");
        potter.setCustomNameVisible(true);
        potter.setVillagerType(Villager.Type.SWAMP);
        potter.setProfession(Villager.Profession.BUTCHER);
        potter.setAI(false);
        potter.setInvulnerable(true);
        potter.setSilent(true);
        potter.setCollidable(false);
        potter.setVillagerExperience(5);
        potter.setVillagerLevel(5);
        potter.setAdult();
        potter.setCanPickupItems(false);
        potter.setRemoveWhenFarAway(false);

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
        int amount = (int) (Math.random() * 15) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        int price = (int) (Math.random() * 5) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.GLASS_BOTTLE, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 15) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.HONEY_BOTTLE, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 15) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.MILK_BUCKET, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        trades.get(3).addIngredient(new ItemStack(Material.BEETROOT_SOUP, 1));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.MUSHROOM_STEW, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.POTION, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 5;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 2) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.ENCHANTING_TABLE, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 15) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.WRITABLE_BOOK, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.SPLASH_POTION, price));

        potter.setRecipes(trades);

        return potter;
    }
}
