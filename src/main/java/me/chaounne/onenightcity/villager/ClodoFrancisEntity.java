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

public class ClodoFrancisEntity {

    private static Villager potter;
    private static SampleInventory sampleInventory;

    public ClodoFrancisEntity(){

    }

    public static Villager getEntity(Location loc){
        setInventory();
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
        int amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        int price = (int) (Math.random() * 20) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.GLASS_BOTTLE, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        price = (int) (Math.random() * 50) + 20;
        trades.get(1).addIngredient(new ItemStack(Material.HONEY_BOTTLE, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 50) + 20;
        trades.get(2).addIngredient(new ItemStack(Material.MILK_BUCKET, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));

        price = (int) (Math.random() * 50) + 20;
        trades.get(3).addIngredient(new ItemStack(Material.BEETROOT_SOUP, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 50) + 20;
        trades.get(4).addIngredient(new ItemStack(Material.MUSHROOM_STEW, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 50) + 20;
        trades.get(5).addIngredient(new ItemStack(Material.POTION, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 25) + 40;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 2) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.ENCHANTING_TABLE, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 25) + 40;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 2) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.WRITABLE_BOOK, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random()* 25) + 40;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 4) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.SPLASH_POTION, price));

        potter.setRecipes(trades);

        return potter;
    }

    public static void setInventory(){
        sampleInventory = new SampleInventory(9, "Francis Clodo");
        //GLASS_BOTTLE (amount 2-10, price 6-25)
        int amount = (int) (Math.random() * 10) + 2;
        int price = (int) (Math.random() * 25) + 6;
        sampleInventory.addItem(new ItemBuilder(Material.GLASS_BOTTLE).amount(amount).addLore("" + price + " Poudres").build(), 0, price);
        //HONEY_BOTTLE (amount 2-10, price 15-25)
        amount = (int) (Math.random() * 10) + 2;
        price = (int) (Math.random() * 25) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.HONEY_BOTTLE).amount(amount).addLore("" + price + " Poudres").build(), 1, price);
        //MILK_BUCKET (amount 2-5, price 20-40)
        amount = (int) (Math.random() * 5) + 2;
        price = (int) (Math.random() * 20) + 40;
        sampleInventory.addItem(new ItemBuilder(Material.MILK_BUCKET).amount(amount).addLore("" + price + " Poudres").build(), 2, price);
        //BEETROOT_SOUP (amount 1, price 15-35)
        amount = 1;
        price = (int) (Math.random() * 35) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.BEETROOT_SOUP).amount(amount).addLore("" + price + " Poudres").build(), 3, price);
        //MUSHROOM_STEW (amount 1, price 15-35)
        amount = 1;
        price = (int) (Math.random() * 35) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.MUSHROOM_STEW).amount(amount).addLore("" + price + " Poudres").build(), 4, price);
        //POTION (amount 2-10, price 6-25)
        amount = (int) (Math.random() * 10) + 2;
        price = (int) (Math.random() * 25) + 6;
        sampleInventory.addItem(new ItemBuilder(Material.POTION).amount(amount).addLore("" + price + " Poudres").build(), 5, price);
        //ENCHANTING_TABLE (amount 1-3, price 150-500)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 500) + 150;
        sampleInventory.addItem(new ItemBuilder(Material.ENCHANTING_TABLE).amount(amount).addLore("" + price + " Poudres").build(), 6, price);
        //WRITABLE_BOOK (amount 1-3, price 15-35)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 35) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.WRITABLE_BOOK).amount(amount).addLore("" + price + " Poudres").build(), 7, price);
        //SPLASH_POTION (amount 1-3, price 50-150)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.SPLASH_POTION).amount(amount).addLore("" + price + " Poudres").build(), 8, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}
