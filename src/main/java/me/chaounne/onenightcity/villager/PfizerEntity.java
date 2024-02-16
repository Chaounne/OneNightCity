package me.chaounne.onenightcity.villager;

import me.chaounne.fastinv.ItemBuilder;
import me.chaounne.onenightcity.game.PoudreItem;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class PfizerEntity {

    private static Villager dose;
    private static SampleInventory sampleInventory;

    public PfizerEntity(){

    }

    public static Villager getEntity(Location loc){
        setInventory();
        dose = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        dose.setCustomName("Dr. Raoult");
        dose.setCustomNameVisible(true);
        dose.setVillagerType(Villager.Type.DESERT);
        dose.setProfession(Villager.Profession.CLERIC);
        dose.setAI(false);
        dose.setInvulnerable(true);
        dose.setSilent(true);
        dose.setCollidable(false);
        dose.setVillagerExperience(5);
        dose.setVillagerLevel(5);
        dose.setAdult();
        dose.setCanPickupItems(false);
        dose.setRemoveWhenFarAway(false);

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
        int amount = (int) (Math.random() * 80) + 20;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        int price = (int) (Math.random() * 5) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.SPIDER_EYE, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.ROTTEN_FLESH, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.BONE, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 15) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.BONE_BLOCK, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.BONE_MEAL, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.GUNPOWDER, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.RABBIT_FOOT, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 5;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.LEATHER, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.RABBIT_HIDE, price));

        //trade 10
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(9).addIngredient(new ItemStack(Material.SLIME_BALL, price));

        //trade 11
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(10).addIngredient(new ItemStack(Material.SCUTE, price));


        dose.setRecipes(trades);

        return dose;
    }

    public static void setInventory(){
        sampleInventory = new SampleInventory(18, "Dr. Raoult");
        //SPIDER_EYE (amount 1-10, price 1-40)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 40) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.SPIDER_EYE).amount(amount).addLore("" + price + " Poudres").build(), 0, price);
        //ROTTEN_FLESH (amount 1-10, price 1-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.ROTTEN_FLESH).amount(amount).addLore("" + price + " Poudres").build(), 1, price);
        //BONE (amount 1-10, price 1-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.BONE).amount(amount).addLore("" + price + " Poudres").build(), 2, price);
        //BONE_BLOCK (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.BONE_BLOCK).amount(amount).addLore("" + price + " Poudres").build(), 3, price);
        //BONE_MEAL (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.BONE_MEAL).amount(amount).addLore("" + price + " Poudres").build(), 4, price);
        //GUNPOWDER (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.GUNPOWDER).amount(amount).addLore("" + price + " Poudres").build(), 5, price);
        //RABBIT_FOOT (amount 1, price 100-200)
        amount = 1;
        price = (int) (Math.random() * 200) + 100;
        sampleInventory.addItem(new ItemBuilder(Material.RABBIT_FOOT).amount(amount).addLore("" + price + " Poudres").build(), 6, price);
        //LEATHER (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.LEATHER).amount(amount).addLore("" + price + " Poudres").build(), 7, price);
        //RABBIT_HIDE (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.RABBIT_HIDE).amount(amount).addLore("" + price + " Poudres").build(), 8, price);
        //SLIME_BALL (amount 1-10, price 10-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.SLIME_BALL).amount(amount).addLore("" + price + " Poudres").build(), 9, price);
        //SCUTE (amount 1, price 250-950)
        amount = 1;
        price = (int) (Math.random() * 950) + 250;
        sampleInventory.addItem(new ItemBuilder(Material.SCUTE).amount(amount).addLore("" + price + " Poudres").build(), 10, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}
