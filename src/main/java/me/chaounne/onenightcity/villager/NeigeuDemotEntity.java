package me.chaounne.onenightcity.villager;

import me.chaounne.fastinv.ItemBuilder;
import me.chaounne.onenightcity.game.PoudreItem;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class NeigeuDemotEntity {

    private static Villager neigeux;
    private static SampleInventory sampleInventory;

    public NeigeuDemotEntity(){

    }

    public static Villager getEntity(Location loc){
        setInventory();
        neigeux = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        neigeux.setCustomName("Négeux Demo");
        neigeux.setCustomNameVisible(true);
        neigeux.setVillagerType(Villager.Type.SNOW);
        neigeux.setProfession(Villager.Profession.MASON);
        neigeux.setAI(false);
        neigeux.setInvulnerable(true);
        neigeux.setSilent(true);
        neigeux.setCollidable(false);
        neigeux.setVillagerExperience(5);
        neigeux.setVillagerLevel(5);
        neigeux.setAdult();
        neigeux.setCanPickupItems(false);
        neigeux.setRemoveWhenFarAway(false);

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
        trades.get(0).addIngredient(new ItemStack(Material.SNOWBALL, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.POWDER_SNOW_BUCKET, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.SNOW, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.SNOW_BLOCK, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.CARVED_PUMPKIN, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.ICE, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 15) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.BLUE_ICE, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 15) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.PACKED_ICE, price));

        neigeux.setRecipes(trades);

        return neigeux;
    }

    public static void setInventory(){
        sampleInventory = new SampleInventory(9, "Négeux Demo");
        //SNOWBALL (amount 1-5, price 1-15)
        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 15) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.SNOWBALL).amount(amount).addLore("" + price + " Poudres").build(), 0, price);
        //POWDER_SNOW_BUCKET (amount 1, price 1-15)
        amount = 1;
        price = (int) (Math.random() * 15) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.POWDER_SNOW_BUCKET).amount(amount).addLore("" + price + " Poudres").build(), 1, price);
        //SNOW (amount 1-5, price 5-25)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 25) + 5;
        sampleInventory.addItem(new ItemBuilder(Material.SNOW).amount(amount).addLore("" + price + " Poudres").build(), 2, price);
        //SNOW_BLOCK (amount 1-5, price 5-30)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 60) + 4;
        sampleInventory.addItem(new ItemBuilder(Material.SNOW_BLOCK).amount(amount).addLore("" + price + " Poudres").build(), 3, price);
        //CARVED_PUMPKIN (amount 1-5, price 2-25)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 25) + 2;
        sampleInventory.addItem(new ItemBuilder(Material.CARVED_PUMPKIN).amount(amount).addLore("" + price + " Poudres").build(), 4, price);
        //ICE (amount 1-5, price 1-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.ICE).amount(amount).addLore("" + price + " Poudres").build(), 5, price);
        //BLUE_ICE (amount 1-5, price 1-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.BLUE_ICE).amount(amount).addLore("" + price + " Poudres").build(), 6, price);
        //PACKED_ICE (amount 1-5, price 1-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.PACKED_ICE).amount(amount).addLore("" + price + " Poudres").build(), 7, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}
