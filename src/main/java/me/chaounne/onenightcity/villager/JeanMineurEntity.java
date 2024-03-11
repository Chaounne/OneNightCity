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

public class JeanMineurEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc){
        setInventory();
        Villager jean = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        jean.setCustomName("Jean Mineur");
        jean.setCustomNameVisible(true);
        jean.setVillagerType(Villager.Type.DESERT);
        jean.setProfession(Villager.Profession.LEATHERWORKER);
        jean.setAI(false);
        //henry3.setInvulnerable(true);
        jean.setSilent(true);
        jean.setCollidable(false);
        jean.setVillagerExperience(5);
        jean.setVillagerLevel(5);
        jean.setAdult();
        jean.setCanPickupItems(false);
        jean.setRemoveWhenFarAway(false);

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
        trades.get(0).addIngredient(new ItemStack(Material.POINTED_DRIPSTONE, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.DRIPSTONE_BLOCK, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 25) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.COAL, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.DEEPSLATE_COAL_ORE, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 150) + 100;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.IRON_ORE, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.DEEPSLATE_IRON_ORE, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 100) + 50;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.COPPER_ORE, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.DEEPSLATE_COPPER_ORE, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 150) + 100;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.GOLD_ORE, price));

        //trade 10
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(9).addIngredient(new ItemStack(Material.DEEPSLATE_GOLD_ORE, price));

        //trade 11
        // random amount of poudre
        amount = (int) (Math.random() * 150) + 100;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(10).addIngredient(new ItemStack(Material.REDSTONE_ORE, price));

        //trade 12
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(11).addIngredient(new ItemStack(Material.DEEPSLATE_REDSTONE_ORE, price));

        //trade 13
        // random amount of poudre
        amount = (int) (Math.random() * 800) + 600;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random()* 5) + 1;
        trades.get(12).addIngredient(new ItemStack(Material.EMERALD_ORE, price));

        //trade 14
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int)(Math.random() * 5) + 1;
        trades.get(13).addIngredient(new ItemStack(Material.DEEPSLATE_EMERALD_ORE, price));

        // lapis trades

        //trade 15
        // random amount of poudre
        amount = (int) (Math.random() * 200) + 100;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price =(int) (Math.random() * 5) + 1;
        trades.get(14).addIngredient(new ItemStack(Material.LAPIS_ORE, price));

        //trade 16
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price =(int) (Math.random() * 5) + 1;
        trades.get(15).addIngredient(new ItemStack(Material.DEEPSLATE_LAPIS_ORE, price));

        //trade 17
        // random amount of poudre
        amount = (int) (Math.random() * 400) + 200;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price =(int) (Math.random() * 5) + 1;
        trades.get(16).addIngredient(new ItemStack(Material.DIAMOND_ORE, price));

        //trade 18
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price =(int) (Math.random() * 5) + 1;
        trades.get(17).addIngredient(new ItemStack(Material.DEEPSLATE_DIAMOND_ORE, price));

        //trade 19
        // random amount of poudre
        amount = (int) (Math.random() * 175) + 125;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price =(int) (Math.random() * 5) + 1;
        trades.get(18).addIngredient(new ItemStack(Material.NETHER_GOLD_ORE, price));

        //trade 20
        // random amount of poudre
        amount = (int) (Math.random() * 1000) + 800;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price =(int) (Math.random() * 3) + 1;
        trades.get(19).addIngredient(new ItemStack(Material.ANCIENT_DEBRIS, price));

        jean.setRecipes(trades);

        return jean;
    }

    public static void setInventory(){
        sampleInventory = new SampleInventory(18, "Jean Mineur");
        //COAL (amount 1-15, price 10-50)
        int amount = (int) (Math.random() * 15) + 1;
        int price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.COAL).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //DEEPSLATE_COAL_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 1000) + 500;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_COAL_ORE).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //IRON_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.IRON_ORE).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //DEEPSLATE_IRON_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_IRON_ORE).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //COPPER_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.COPPER_ORE).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //DEEPSLATE_COPPER_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_COPPER_ORE).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //GOLD_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.GOLD_ORE).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //DEEPSLATE_GOLD_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_GOLD_ORE).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //REDSTONE_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 500) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.REDSTONE_ORE).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //DEEPSLATE_REDSTONE_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_REDSTONE_ORE).amount(amount).addLore(price + " Poudres").build(), 9, price);
        //EMERALD_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 0) + 1;
        price = (int) (Math.random() * 1000) + 500;
        sampleInventory.addItem(new ItemBuilder(Material.EMERALD_ORE).amount(amount).addLore(price + " Poudres").build(), 10, price);
        //DEEPSLATE_EMERALD_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 0) + 1;
        price = (int) (Math.random() * 1000) + 500;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_EMERALD_ORE).amount(amount).addLore(price + " Poudres").build(), 11, price);
        //LAPIS_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 500) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.LAPIS_ORE).amount(amount).addLore(price + " Poudres").build(), 12, price);
        //DEEPSLATE_LAPIS_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 500) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_LAPIS_ORE).amount(amount).addLore(price + " Poudres").build(), 13, price);
        //DIAMOND_ORE (amount 1-5, price 50-300)
        amount = (int) (Math.random() * 0) + 1;
        price = (int) (Math.random() * 1000) + 500;
        sampleInventory.addItem(new ItemBuilder(Material.DIAMOND_ORE).amount(amount).addLore(price + " Poudres").build(), 14, price);
        //DEEPSLATE_DIAMOND_ORE (amount 1-5, price 50-300)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_DIAMOND_ORE).amount(amount).addLore(price + " Poudres").build(), 15, price);
        //NETHER_GOLD_ORE (amount 1-5, price 50-150)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.NETHER_GOLD_ORE).amount(amount).addLore(price + " Poudres").build(), 16, price);
        //ANCIENT_DEBRIS (amount 1-3, price 100-500)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 1000) + 500;
        sampleInventory.addItem(new ItemBuilder(Material.ANCIENT_DEBRIS).amount(amount).addLore(price + " Poudres").build(), 17, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}