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

public class HenryEntity {

    private static SampleInventory inventory;

    public static Villager getEntity(Location loc){
        setInventory();
        Villager henry = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        henry.setCustomName("Henry");
        henry.setCustomNameVisible(true);
        henry.setProfession(Villager.Profession.FARMER);
        henry.setAI(false);
       // henry.setInvulnerable(false);
        henry.setSilent(true);
        henry.setCollidable(false);
        henry.setVillagerExperience(5);
        henry.setVillagerLevel(5);
        henry.setAdult();
        henry.setCanPickupItems(false);
        henry.setRemoveWhenFarAway(false);

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
        trades.get(0).addIngredient(new ItemStack(Material.MELON, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.PUMPKIN, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.CARROT, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.POTATO, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.BEETROOT, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 20) + 5;
        trades.get(5).addIngredient(new ItemStack(Material.WHEAT, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.SUGAR_CANE, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.COCOA_BEANS, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 15) + 5;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.APPLE, price));

        henry.setRecipes(trades);

        //ce qu'henry propose
        //MerchantRecipe recipe = new MerchantRecipe(null, 0);
        //ce qu'il veut
        //recipe.addIngredient(null);

        return henry;
    }

    public static void setInventory(){
        inventory = new SampleInventory(9, "Henry");
        //entre 1 et 3 wheat
        int amount = (int) (Math.random() * 3) + 1;
        //entre 2 et 15 poudre
        int price = (int) (Math.random() * 15) + 2;
        inventory.addItem(new ItemBuilder(Material.WHEAT).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //add melon (amount 1-3, price 2-15)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 2;
        inventory.addItem(new ItemBuilder(Material.MELON).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //add pumpkin (amount 1-3, price 2-16)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 2;
        inventory.addItem(new ItemBuilder(Material.PUMPKIN).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //add carrot (amount 1-3, price 2-15)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 2;
        inventory.addItem(new ItemBuilder(Material.CARROT).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //add potato (amount 1-3, price 2-15)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 2;
        inventory.addItem(new ItemBuilder(Material.POTATO).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //add beetroot (amount 1-3, price 2-15)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 2;
        inventory.addItem(new ItemBuilder(Material.BEETROOT).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //add apple (amount 1-3, price 10-15)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 10;
        inventory.addItem(new ItemBuilder(Material.APPLE).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //sugar_cane (amount 1-5, price 2-15)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 15) + 2;
        inventory.addItem(new ItemBuilder(Material.SUGAR_CANE).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //cocoa_beans (amount 1-3, price 10-15)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 15) + 10;
        inventory.addItem(new ItemBuilder(Material.COCOA_BEANS).amount(amount).addLore(price + " Poudres").build(), 8, price);
    }

    public SampleInventory getInventory(){
        return inventory;
    }

    public static void openInventory(Player player){
        inventory.open(player);
    }

}