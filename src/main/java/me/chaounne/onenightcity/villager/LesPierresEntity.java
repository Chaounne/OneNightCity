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

public class LesPierresEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc){
        setInventory();
        Villager aypierre = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

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
        int amount = (int) (Math.random() * 25) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        int price = (int) (Math.random() * 5) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.REDSTONE_BLOCK, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 3) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.COMPARATOR, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.TARGET, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.HOPPER, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.DISPENSER, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.DROPPER, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.OBSERVER, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.PISTON, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.STICKY_PISTON, price));

        //trade 10
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
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

    public static void setInventory(){
        sampleInventory = new SampleInventory(18, "Les Pierres");
        //REDSTONE_BLOCK (amount 1-10, price 20-250)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.REDSTONE_BLOCK).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //COMPARATOR (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.COMPARATOR).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //TARGET (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.TARGET).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //HOPPER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.HOPPER).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //DISPENSER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.DISPENSER).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //DROPPER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.DROPPER).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //OBSERVER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.OBSERVER).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //PISTON (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.PISTON).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //STICKY_PISTON (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 300) + 30;
        sampleInventory.addItem(new ItemBuilder(Material.STICKY_PISTON).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //TRIPWIRE_HOOK (amount 4-10, price 10-100)
        amount = (int) (Math.random() * 7) + 4;
        price = (int) (Math.random() * 100) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.TRIPWIRE_HOOK).amount(amount).addLore(price + " Poudres").build(), 9, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}