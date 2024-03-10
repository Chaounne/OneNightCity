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

public class KilianMBoufféEntity {

    private static Villager MBouffe;
    private static SampleInventory sampleInventory;

    public KilianMBoufféEntity(){

    }

    public static Villager getEntity(Location loc){
        setInventory();
        MBouffe = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        MBouffe.setCustomName("Kylian MBouffé");
        MBouffe.setCustomNameVisible(true);
        MBouffe.setProfession(Villager.Profession.BUTCHER);
        MBouffe.setAI(false);
        MBouffe.setInvulnerable(true);
        MBouffe.setSilent(true);
        MBouffe.setCollidable(false);
        MBouffe.setVillagerExperience(5);
        MBouffe.setVillagerLevel(5);
        MBouffe.setAdult();
        MBouffe.setCanPickupItems(false);
        MBouffe.setRemoveWhenFarAway(false);

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
        int amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        int price = (int) (Math.random() * 5) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.COOKED_BEEF, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.COOKED_CHICKEN, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.COOKED_PORKCHOP, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.COOKED_MUTTON, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.COOKED_RABBIT, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.RABBIT_STEW));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.PUMPKIN_PIE, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.COOKIE, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 25) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.SWEET_BERRIES, price));

        //trade 10
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(9).addIngredient(new ItemStack(Material.DRIED_KELP, price));

        MBouffe.setRecipes(trades);

        return MBouffe;
    }

    public static void setInventory(){
        sampleInventory = new SampleInventory(18, "Kylian MBouffé");
        //COOKED_BEEF (amount 1-10, price 1-20)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.COOKED_BEEF).amount(amount).addLore("" + price + " Poudres").build(), 0, price);
        //COOKED_CHICKEN (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.COOKED_CHICKEN).amount(amount).addLore("" + price + " Poudres").build(), 1, price);
        //COOKED_PORKCHOP (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.COOKED_PORKCHOP).amount(amount).addLore("" + price + " Poudres").build(), 2, price);
        //COOKED_MUTTON (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.COOKED_MUTTON).amount(amount).addLore("" + price + " Poudres").build(), 3, price);
        //COOKED_RABBIT (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.COOKED_RABBIT).amount(amount).addLore("" + price + " Poudres").build(), 4, price);
        //RABBIT_STEW (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.RABBIT_STEW).amount(amount).addLore("" + price + " Poudres").build(), 5, price);
        //PUMPKIN_PIE (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.PUMPKIN_PIE).amount(amount).addLore("" + price + " Poudres").build(), 6, price);
        //COOKIE (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.COOKIE).amount(amount).addLore("" + price + " Poudres").build(), 7, price);
        //SWEET_BERRIES (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 25) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.SWEET_BERRIES).amount(amount).addLore("" + price + " Poudres").build(), 8, price);
        //DRIED_KELP (amount 1-10, price 1-10)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 10) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.DRIED_KELP).amount(amount).addLore("" + price + " Poudres").build(), 9, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}
