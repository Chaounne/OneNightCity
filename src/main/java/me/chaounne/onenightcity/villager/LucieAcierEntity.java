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

public class LucieAcierEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc){
        setInventory();
        Villager lucyfer = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        lucyfer.setCustomName("Lucie Acier");
        lucyfer.setCustomNameVisible(true);
        lucyfer.setVillagerType(Villager.Type.JUNGLE);
        lucyfer.setProfession(Villager.Profession.BUTCHER);
        lucyfer.setAI(false);
        lucyfer.setInvulnerable(true);
        lucyfer.setSilent(true);
        lucyfer.setCollidable(false);
        lucyfer.setVillagerExperience(5);
        lucyfer.setVillagerLevel(5);
        lucyfer.setAdult();
        lucyfer.setCanPickupItems(false);
        lucyfer.setRemoveWhenFarAway(false);

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
        trades.get(0).addIngredient(new ItemStack(Material.NETHER_WART, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.NETHER_WART_BLOCK, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.WARPED_WART_BLOCK, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.WARPED_FUNGUS, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.WARPED_FUNGUS_ON_A_STICK, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.QUARTZ_BLOCK, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 5;
        trades.get(6).addIngredient(new ItemStack(Material.QUARTZ, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 3;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 3;
        trades.get(7).addIngredient(new ItemStack(Material.NETHER_BRICK, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.NETHER_BRICK_FENCE, price));

        //trade 10
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(9).addIngredient(new ItemStack(Material.NETHER_BRICK_SLAB, price));

        //trade 11
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(10).addIngredient(new ItemStack(Material.NETHER_BRICK_STAIRS, price));

        //trade 12
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(11).addIngredient(new ItemStack(Material.NETHERITE_SCRAP, price));

        //trade 13
        // random amount of poudre
        amount = (int) (Math.random() * 900) + 800;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 3) + 1;
        trades.get(12).addIngredient(new ItemStack(Material.NETHERITE_INGOT, price));

        lucyfer.setRecipes(trades);

        return lucyfer;
    }

    public static void setInventory(){
        sampleInventory = new SampleInventory(18, "Lucie Acier");
        //NETHER_WART (amount 1-10, price 10-40)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 40) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.NETHER_WART).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //NETHER_WART_BLOCK (amount 1-10, price 1-25)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 25) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.NETHER_WART_BLOCK).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //WARPED_WART_BLOCK (amount 1-10, price 1-25)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 25) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.WARPED_WART_BLOCK).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //WARPED_FUNGUS (amount 1-10, price 1-25)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 25) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.WARPED_FUNGUS).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //WARPED_FUNGUS_ON_A_STICK (amount 1, price 10-100)
        amount = 1;
        price = (int) (Math.random() * 100) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.WARPED_FUNGUS_ON_A_STICK).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //QUARTZ_BLOCK (amount 1-5, price 20-50)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.QUARTZ_BLOCK).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //QUARTZ (amount 1-5, price 10-40)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 40) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.QUARTZ).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //NETHER_BRICK (amount 2-3, price 15-20)
        amount = (int) (Math.random() * 2) + 2;
        price = (int) (Math.random() * 6) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.NETHER_BRICK).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //NETHER_BRICK_FENCE (amount 1-10, price 10-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.NETHER_BRICK_FENCE).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //NETHER_BRICK_SLAB (amount 1-10, price 10-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.NETHER_BRICK_SLAB).amount(amount).addLore(price + " Poudres").build(), 9, price);
        //NETHER_BRICK_STAIRS (amount 1-10, price 10-40)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 40) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.NETHER_BRICK_STAIRS).amount(amount).addLore(price + " Poudres").build(), 10, price);
        //NETHERITE_SCRAP (amount 1-3, price 100-600)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 600) + 100;
        sampleInventory.addItem(new ItemBuilder(Material.NETHERITE_SCRAP).amount(amount).addLore(price + " Poudres").build(), 11, price);
        //NETHERITE_INGOT (amount 1-3, price 100-800)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 800) + 100;
        sampleInventory.addItem(new ItemBuilder(Material.NETHERITE_INGOT).amount(amount).addLore(price + " Poudres").build(), 12, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}