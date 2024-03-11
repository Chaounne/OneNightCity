package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
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

// TODO ÉCRIRE UNE CLASSE GÉNÉRALE POUR LES VILLAGEOIS (pourquoi c'est pas déjà le cas omg)
public class BeauThonyEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc){
        setInventory();
        Villager thony = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

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

        /** int i = 69
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

    public static void setInventory(){
        sampleInventory = new SampleInventory(18, "Beau Thony");
        //acacia_sapling (amount 1-10, price 50-150)
        int amount = (int) (Math.random() * 10) + 3;
        int price = (int) (Math.random() * 100) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.ACACIA_SAPLING).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //bamboo (amount 1-10, price 2-15)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 15) + 2;
        sampleInventory.addItem(new ItemBuilder(Material.BAMBOO).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //birch_sapling (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 3;
        price = (int) (Math.random() * 100) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.BIRCH_SAPLING).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //dark_oak_sapling (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 3;
        price = (int) (Math.random() * 100) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.DARK_OAK_SAPLING).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //spruce_sapling (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 3;
        price = (int) (Math.random() * 100) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.SPRUCE_SAPLING).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //jungle_sapling (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 3;
        price = (int) (Math.random() * 100) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.JUNGLE_SAPLING).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //oak_sapling (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 3;
        price = (int) (Math.random() * 100) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.OAK_SAPLING).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //flower_pot (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.FLOWER_POT).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //mangrove_propagule (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 3;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.MANGROVE_PROPAGULE).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //azalea (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.AZALEA).amount(amount).addLore(price + " Poudres").build(), 9, price);
        //azalea_leaves (1-10, 50-150)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.AZALEA_LEAVES).amount(amount).addLore(price + " Poudres").build(), 10, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}