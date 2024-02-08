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

public class BeauThonyEntity {

    private static Villager thony;

    public BeauThonyEntity(){

    }

    public static Villager getEntity(Location loc){
        thony = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        thony.setCustomName("Beau Thony");
        thony.setCustomNameVisible(true);
        thony.setVillagerType(Villager.Type.JUNGLE);
        thony.setProfession(Villager.Profession.CARTOGRAPHER);
        thony.setAI(false);
        thony.setInvulnerable(true);
        thony.setSilent(true);
        thony.setCollidable(false);
        thony.setVillagerExperience(5);
        thony.setVillagerLevel(5);
        thony.setAdult();
        thony.setCanPickupItems(false);
        thony.setRemoveWhenFarAway(false);

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
        int amount = (int) (Math.random() * 21) + 20;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        int price = (int) (Math.random()  * 5) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.ACACIA_SAPLING, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.BAMBOO, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 21) + 20;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random()  * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.BIRCH_SAPLING, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 21) + 20;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random()  * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.DARK_OAK_SAPLING, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random()* 21) + 20;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random()  * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.SPRUCE_SAPLING, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 21) + 20;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random()  * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.JUNGLE_SAPLING, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random()* 21) + 20;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.OAK_SAPLING, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random()  * 25) + 40; //MAX VALUE 64
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 2) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.FLOWER_POT, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random()* 21) + 20;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random()* 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.MANGROVE_PROPAGULE, price));

        //trade 10
        // random amount of poudre
        amount = (int) (Math.random() * 21) + 20;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(9).addIngredient(new ItemStack(Material.AZALEA, price));

        //trade 11
        // random amount of poudre
        amount = (int) (Math.random() * 21) + 20;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(10).addIngredient(new ItemStack(Material.AZALEA_LEAVES, price));

        thony.setRecipes(trades);

        return thony;
    }
}
