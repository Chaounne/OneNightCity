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

public class LegiasEntity {

    private static Villager legolas;
    private static SampleInventory sampleInventory;

    public LegiasEntity(){

    }

    public static Villager getEntity(Location loc){
        setInventory();
        legolas = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        legolas.setCustomName("Legias");
        legolas.setCustomNameVisible(true);
        legolas.setVillagerType(Villager.Type.SWAMP);
        legolas.setProfession(Villager.Profession.FLETCHER);
        legolas.setAI(false);
        //henry3.setInvulnerable(true);
        legolas.setSilent(true);
        legolas.setCollidable(false);
        legolas.setVillagerExperience(5);
        legolas.setVillagerLevel(5);
        legolas.setAdult();
        legolas.setCanPickupItems(false);
        legolas.setRemoveWhenFarAway(false);

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
        int price = (int) (Math.random() * 64) + 3;
        if(price > 64){
            price = 64;
        }
        trades.get(0).addIngredient(new ItemStack(Material.ARROW, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        trades.get(1).addIngredient(new ItemStack(Material.BOW, 1));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 64) + 3;
        if(price > 64){
            price = 64;
        }
        trades.get(2).addIngredient(new ItemStack(Material.SPECTRAL_ARROW, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 30) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        trades.get(3).addIngredient(new ItemStack(Material.CROSSBOW, 1));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.BIG_DRIPLEAF, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.SMALL_DRIPLEAF, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.SPORE_BLOSSOM, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.VINE, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.LILY_PAD, price));

        legolas.setRecipes(trades);

        return legolas;
    }

    public static void setInventory(){
        sampleInventory = new SampleInventory(9, "Legias");
        //ARROW (amount 4-16, price 20-30)
        int amount = (int) (Math.random() * 13) + 4;
        int price = (int) (Math.random() * 30) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.ARROW).amount(amount).addLore("" + price + " Poudres").build(), 0, price);
        //BOW (amount 1, price 10-200)
        amount = 1;
        price = (int) (Math.random() * 200) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.BOW).amount(amount).addLore("" + price + " Poudres").build(), 1, price);
        //SPECTRAL_ARROW (amount 4-16, price 10-150)
        amount = (int) (Math.random() * 13) + 4;
        price = (int) (Math.random() * 150) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.SPECTRAL_ARROW).amount(amount).addLore("" + price + " Poudres").build(), 2, price);
        //CROSSBOW (amount 1, price 40-250)
        amount = 1;
        price = (int) (Math.random() * 250) + 40;
        sampleInventory.addItem(new ItemBuilder(Material.CROSSBOW).amount(amount).addLore("" + price + " Poudres").build(), 3, price);
        //BIG_DRIPLEAF (amount 1-10, price 15-30)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 30) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.BIG_DRIPLEAF).amount(amount).addLore("" + price + " Poudres").build(), 4, price);
        //SMALL_DRIPLEAF (amount 1-10, price 15-30)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 30) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.SMALL_DRIPLEAF).amount(amount).addLore("" + price + " Poudres").build(), 5, price);
        //SPORE_BLOSSOM (amount 1-5, price 40-250)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 250) + 40;
        sampleInventory.addItem(new ItemBuilder(Material.SPORE_BLOSSOM).amount(amount).addLore("" + price + " Poudres").build(), 6, price);
        //VINE (amount 1-10, price 15-30)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 30) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.VINE).amount(amount).addLore("" + price + " Poudres").build(), 7, price);
        //LILY_PAD (amount 1-10, price 15-30)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 30) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.LILY_PAD).amount(amount).addLore("" + price + " Poudres").build(), 8, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}
