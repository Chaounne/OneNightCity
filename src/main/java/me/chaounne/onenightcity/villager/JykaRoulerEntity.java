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

public class JykaRoulerEntity {

    private static Villager potter;
    private static SampleInventory sampleInventory;


    public JykaRoulerEntity(){

    }

    public static Villager getEntity(Location loc){
        setInventory();
        potter = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        potter.setCustomName("Jyka Rouler");
        potter.setCustomNameVisible(true);
        potter.setVillagerType(Villager.Type.PLAINS);
        potter.setProfession(Villager.Profession.LIBRARIAN);
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
        int price = (int) (Math.random() * 5) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.BOOK, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 50) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.BOOKSHELF, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 30) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.LECTERN, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        trades.get(3).addIngredient(new ItemStack(Material.CROSSBOW, 1));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.INK_SAC, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.GLOW_INK_SAC, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 5;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 1) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.ENCHANTING_TABLE, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.WRITABLE_BOOK, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 3) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.FEATHER, price));

        potter.setRecipes(trades);

        return potter;
    }
    public static void setInventory(){
        sampleInventory = new SampleInventory(9, "Jyka Rouler");
        //BOOK (amount 1-5, price 10-50)
        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.BOOK).amount(amount).addLore("" + price + " Poudres").build(), 0, price);
        //BOOKSHELF (amount 1-5, price 60-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 60;
        sampleInventory.addItem(new ItemBuilder(Material.BOOKSHELF).amount(amount).addLore("" + price + " Poudres").build(), 1, price);
        //LECTERN (amount 1-5, price 10-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.LECTERN).amount(amount).addLore("" + price + " Poudres").build(), 2, price);
        //CROSSBOW (amount 1-5, price 10-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.CROSSBOW).amount(amount).addLore("" + price + " Poudres").build(), 3, price);
        //INK_SAC (amount 1-5, price 10-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.INK_SAC).amount(amount).addLore("" + price + " Poudres").build(), 4, price);
        //GLOW_INK_SAC (amount 1-5, price 10-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.GLOW_INK_SAC).amount(amount).addLore("" + price + " Poudres").build(), 5, price);
        //WRITABLE_BOOK (amount 1-5, price 10-70)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 70) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.WRITABLE_BOOK).amount(amount).addLore("" + price + " Poudres").build(), 6, price);
        //FEATHER (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.FEATHER).amount(amount).addLore("" + price + " Poudres").build(), 7, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}
