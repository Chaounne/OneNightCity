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

public class MicoseMicodeEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc){
        setInventory();
        Villager champi = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        champi.setCustomName("Micose Micode");
        champi.setCustomNameVisible(true);
        champi.setVillagerType(Villager.Type.SWAMP);
        champi.setProfession(Villager.Profession.FLETCHER);
        champi.setAI(false);
        champi.setInvulnerable(true);
        champi.setSilent(true);
        champi.setCollidable(false);
        champi.setVillagerExperience(5);
        champi.setVillagerLevel(5);
        champi.setAdult();
        champi.setCanPickupItems(false);
        champi.setRemoveWhenFarAway(false);

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
        trades.get(0).addIngredient(new ItemStack(Material.MYCELIUM, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.RED_MUSHROOM_BLOCK, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.BROWN_MUSHROOM_BLOCK, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.MUSHROOM_STEM, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.SMITHING_TABLE, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.SHROOMLIGHT, price));

        champi.setRecipes(trades);

        return champi;
    }

    public static void setInventory(){
        sampleInventory = new SampleInventory(9, "Micose Micode");
        //MYCELIUM (amount 1-5, price 50-200)
        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 200) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.MYCELIUM).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //RED_MUSHROOM_BLOCK (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.RED_MUSHROOM_BLOCK).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //BROWN_MUSHROOM_BLOCK (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.BROWN_MUSHROOM_BLOCK).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //MUSHROOM_STEM (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.MUSHROOM_STEM).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //SHROOMLIGHT (amount 1-5, price 20-50)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.SHROOMLIGHT).amount(amount).addLore(price + " Poudres").build(), 4, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}
