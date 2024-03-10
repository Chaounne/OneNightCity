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

public class JeaneauEntity {

    private static Villager jean;
    private static SampleInventory sampleInventory;

    public JeaneauEntity(){

    }

    public static Villager getEntity(Location loc){
        setInventory();
        jean = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        jean.setCustomName("Jeaneau");
        jean.setCustomNameVisible(true);
        jean.setVillagerType(Villager.Type.PLAINS);
        jean.setProfession(Villager.Profession.FISHERMAN);
        jean.setAI(false);
        jean.setInvulnerable(true);
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

         //trade 1
        // random amount of poudre
        int amount = (int) (Math.random() * 20) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        int price = (int) (Math.random() * 5) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.PRISMARINE, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.PRISMARINE_SHARD, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.PRISMARINE_BRICKS, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.PRISMARINE_CRYSTALS, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.PRISMARINE_BRICK_SLAB, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.PRISMARINE_BRICK_STAIRS, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.PRISMARINE_SLAB, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.PRISMARINE_STAIRS, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 20) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.PRISMARINE_WALL, price));

        jean.setRecipes(trades);

        //ce qu'henry propose
       // MerchantRecipe recipe = new MerchantRecipe(null, 0);
        //ce qu'il veut
      //  recipe.addIngredient(null);



        return jean;
    }

    public static void setInventory(){
        sampleInventory = new SampleInventory(9, "Jeaneau");
        //PRISMARINE (amount 1-15, price 10-50)
        int amount = (int) (Math.random() * 15) + 1;
        int price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE).amount(amount).addLore("" + price + " Poudres").build(), 0, price);
        //PRISMARINE_SHARD (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_SHARD).amount(amount).addLore("" + price + " Poudres").build(), 1, price);
        //PRISMARINE_BRICKS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_BRICKS).amount(amount).addLore("" + price + " Poudres").build(), 2, price);
        //PRISMARINE_CRYSTALS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_CRYSTALS).amount(amount).addLore("" + price + " Poudres").build(), 3, price);
        //PRISMARINE_BRICK_SLAB (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_BRICK_SLAB).amount(amount).addLore("" + price + " Poudres").build(), 4, price);
        //PRISMARINE_BRICK_STAIRS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_BRICK_STAIRS).amount(amount).addLore("" + price + " Poudres").build(), 5, price);
        //PRISMARINE_SLAB (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_SLAB).amount(amount).addLore("" + price + " Poudres").build(), 6, price);
        //PRISMARINE_STAIRS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_STAIRS).amount(amount).addLore("" + price + " Poudres").build(), 7, price);
        //PRISMARINE_WALL (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_WALL).amount(amount).addLore("" + price + " Poudres").build(), 8, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}
