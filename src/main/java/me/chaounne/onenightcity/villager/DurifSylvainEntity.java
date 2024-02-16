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

public class DurifSylvainEntity {

    private static Villager durif;
    private static SampleInventory sampleInventory;

    public DurifSylvainEntity(){

    }

    public static Villager getEntity(Location loc){
        setInventory();
        durif = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        durif.setCustomName("Civique Cosmique Durif");
        durif.setCustomNameVisible(true);
        durif.setVillagerType(Villager.Type.SWAMP);
        durif.setProfession(Villager.Profession.LEATHERWORKER);
        durif.setAI(false);
        durif.setInvulnerable(true);
        durif.setSilent(true);
        durif.setCollidable(false);
        durif.setVillagerExperience(5);
        durif.setVillagerLevel(5);
        durif.setAdult();
        durif.setCanPickupItems(false);
        durif.setRemoveWhenFarAway(false);

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
        int amount = (int) (Math.random() * 150) + 50;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        int price = (int) (Math.random() * 5) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.CHORUS_FRUIT, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 50) + 20;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.CHORUS_FLOWER, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 15) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.PURPUR_BLOCK, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 15) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.PURPUR_PILLAR, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.PURPUR_SLAB, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.PURPUR_STAIRS, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 5;
        trades.get(6).addIngredient(new ItemStack(Material.END_STONE_BRICKS, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.END_STONE_BRICK_SLAB, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.END_STONE_BRICK_STAIRS, price));

        //trade 10
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(9).addIngredient(new ItemStack(Material.END_STONE_BRICK_WALL, price));

        //trade 11
        // random amount of poudre
        amount = (int) (Math.random() * 30) + 10;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 1) + 1;
        trades.get(10).addIngredient(new ItemStack(Material.END_CRYSTAL, price));

        //trade 12
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(11).addIngredient(new ItemStack(Material.END_ROD, price));

        //trade 13
        // random amount of poudre
        amount = (int) (Math.random() * 40) + 20;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(12).addIngredient(new ItemStack(Material.OBSIDIAN, price));

        durif.setRecipes(trades);

        return durif;
    }

    public static void setInventory(){
        sampleInventory = new SampleInventory(18, "Civique Cosmique Durif");
        //CHORUS_FRUIT (amount 1-3, price 15-35)
        int amount = (int) (Math.random() * 3) + 1;
        int price = (int) (Math.random() * 21) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.CHORUS_FRUIT).amount(amount).addLore("" + price + " Poudres").build(), 0, price);
        //CHORUS_FLOWER (amount 1-3, price 10-30)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 21) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.CHORUS_FLOWER).amount(amount).addLore("" + price + " Poudres").build(), 1, price);
        //PURPUR_BLOCK (amount 1-3, price 50-80)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 31) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.PURPUR_BLOCK).amount(amount).addLore("" + price + " Poudres").build(), 2, price);
        //PURPUR_PILLAR (amount 1-3, price 50-80)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 31) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.PURPUR_PILLAR).amount(amount).addLore("" + price + " Poudres").build(), 3, price);
        //PURPUR_SLAB (amount 1-3, price 50-60)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 11) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.PURPUR_SLAB).amount(amount).addLore("" + price + " Poudres").build(), 4, price);
        //PURPUR_STAIRS (amount 1-3, price 50-60)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 11) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.PURPUR_STAIRS).amount(amount).addLore("" + price + " Poudres").build(), 5, price);
        //END_STONE_BRICKS (amount 5-10, price 10-50)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 41) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.END_STONE_BRICKS).amount(amount).addLore("" + price + " Poudres").build(), 6, price);
        //END_STONE_BRICK_SLAB (amount 5-10, price 10-25)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 16) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.END_STONE_BRICK_SLAB).amount(amount).addLore("" + price + " Poudres").build(), 7, price);
        //END_STONE_BRICK_STAIRS (amount 5-10, price 10-50)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 41) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.END_STONE_BRICK_STAIRS).amount(amount).addLore("" + price + " Poudres").build(), 8, price);
        //END_STONE_BRICK_WALL (amount 5-10, price 10-50)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 41) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.END_STONE_BRICK_WALL).amount(amount).addLore("" + price + " Poudres").build(), 9, price);
        //END_CRYSTAL (amount 1, price 100-500)
        amount = 1;
        price = (int) (Math.random() * 401) + 100;
        sampleInventory.addItem(new ItemBuilder(Material.END_CRYSTAL).amount(amount).addLore("" + price + " Poudres").build(), 10, price);
        //END_ROD (amount 1-5, price 5-110)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 106) + 5;
        sampleInventory.addItem(new ItemBuilder(Material.END_ROD).amount(amount).addLore("" + price + " Poudres").build(), 11, price);
        //OBSIDIAN (amount 1-10, price 50-100)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 51) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.OBSIDIAN).amount(amount).addLore("" + price + " Poudres").build(), 12, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}
