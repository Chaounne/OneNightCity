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

public class CheepCheapEntity {

    private static Villager cheap;

    public CheepCheapEntity(){

    }

    public static Villager getEntity(Location loc){
        cheap = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        cheap.setCustomName("Cheep Cheap");
        cheap.setCustomNameVisible(true);
        cheap.setVillagerType(Villager.Type.TAIGA);
        cheap.setProfession(Villager.Profession.SHEPHERD);
        cheap.setAI(false);
        //henry3.setInvulnerable(true);
        cheap.setSilent(true);
        cheap.setCollidable(false);
        cheap.setVillagerExperience(5);
        cheap.setVillagerLevel(5);
        cheap.setAdult();
        cheap.setCanPickupItems(false);
        cheap.setRemoveWhenFarAway(false);

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
        int price = (int) (Math.random() * 10) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.WHITE_WOOL, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.RED_WOOL, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.BLUE_WOOL, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.GREEN_WOOL, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.YELLOW_WOOL, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.PURPLE_WOOL, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.ORANGE_WOOL, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.MAGENTA_WOOL, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.LIGHT_BLUE_WOOL, price));

        //trade 10
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(9).addIngredient(new ItemStack(Material.LIGHT_GRAY_WOOL, price));

        //trade 11
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(10).addIngredient(new ItemStack(Material.GRAY_WOOL, price));

        //trade 12
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(11).addIngredient(new ItemStack(Material.BLACK_WOOL, price));

        //trade 13
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(12).addIngredient(new ItemStack(Material.BROWN_WOOL, price));

        //trade 14
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() *10) + 1;
        trades.get(13).addIngredient(new ItemStack(Material.CYAN_WOOL, price));

        //trade 15
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random()*10) + 1;
        trades.get(14).addIngredient(new ItemStack(Material.LIME_WOOL, price));

        cheap.setRecipes(trades);

        //ce qu'henry3 propose
        //MerchantRecipe recipe = new MerchantRecipe(null, 0);
        //ce qu'il veut
        //recipe.addIngredient(null);



        return cheap;
    }
}
