package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.game.PoudreItem;
import fr.mrmicky.fastinv.ItemBuilder;
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

public class JustinPuechEntity {

    private static Villager justin;
    private static SampleInventory sampleInventory;
    public JustinPuechEntity(){

    }

    public static Villager getEntity(Location loc){
        setInventory();
        justin = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        justin.setCustomName("Justin Puech");
        justin.setCustomNameVisible(true);
        justin.setVillagerType(Villager.Type.SNOW);
        justin.setProfession(Villager.Profession.FISHERMAN);
        justin.setAI(false);
        justin.setInvulnerable(true);
        justin.setSilent(true);
        justin.setCollidable(false);
        justin.setVillagerExperience(5);
        justin.setVillagerLevel(5);
        justin.setAdult();
        justin.setCanPickupItems(false);
        justin.setRemoveWhenFarAway(false);

        /**int i = 69
         * int alea = random 100
         * if alea = i
         * message everyone "Prix doublé pendant 5 minutes"
         * on double le prix des items donc au lieu de 2 melon pour 2 poudres => 2 melon pour 4 poudres
         * else
         * wait(1minutes)

         List<MerchantRecipe> trades = new ArrayList<>();

         //trade 1
        // random amount of poudre
        int amount = (int) (Math.random() * 20) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        int price = (int) (Math.random() * 5) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.FISHING_ROD, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 115) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.WATER_BUCKET, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 50) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.PUFFERFISH, price));


        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.COOKED_COD, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 15) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.SALMON, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 15) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.TROPICAL_FISH, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.COD_BUCKET, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.SALMON_BUCKET, price));

        //trade 10
        // random amount of poudre
        amount = (int) (Math.random() * 50) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(9).addIngredient(new ItemStack(Material.PUFFERFISH_BUCKET, price));

        //trade 11
        // random amount of poudre
        amount = (int) (Math.random() * 50) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(10).addIngredient(new ItemStack(Material.TROPICAL_FISH_BUCKET, price));

        justin.setRecipes(trades);

        //ce qu'henry propose
       // MerchantRecipe recipe = new MerchantRecipe(null, 0);
        //ce qu'il veut
      //  recipe.addIngredient(null);

         */

        return justin;
    }

    public static void setInventory(){
        sampleInventory = new SampleInventory(18, "Justin Puech");
        //FISHING_ROD (amount 1, price 10-50)
        int amount = 1;
        int price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.FISHING_ROD).amount(amount).addLore("" + price + " Poudres").build(), 0, price);
        //WATER_BUCKET (amount 1, price 20-40)
        amount = 1;
        price = (int) (Math.random() * 40) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.WATER_BUCKET).amount(amount).addLore("" + price + " Poudres").build(), 1, price);
        //PUFFERFISH (amount 1, price 50-150)
        amount = 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.PUFFERFISH).amount(amount).addLore("" + price + " Poudres").build(), 2, price);
        //COOKED_COD (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.COOKED_COD).amount(amount).addLore("" + price + " Poudres").build(), 3, price);
        //SALMON (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.SALMON).amount(amount).addLore("" + price + " Poudres").build(), 4, price);
        //TROPICAL_FISH (amount 1, price 150-500)
        amount = 1;
        price = (int) (Math.random() * 500) + 150;
        sampleInventory.addItem(new ItemBuilder(Material.TROPICAL_FISH).amount(amount).addLore("" + price + " Poudres").build(), 5, price);
        //COD_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.COD_BUCKET).amount(amount).addLore("" + price + " Poudres").build(), 6, price);
        //SALMON_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.SALMON_BUCKET).amount(amount).addLore("" + price + " Poudres").build(), 7, price);
        //PUFFERFISH_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PUFFERFISH_BUCKET).amount(amount).addLore("" + price + " Poudres").build(), 8, price);
        //TROPICAL_FISH_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.TROPICAL_FISH_BUCKET).amount(amount).addLore("" + price + " Poudres").build(), 9, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}
