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

public class HutilItaireEntity {

    private static Villager utilitaire;
    private static SampleInventory sampleInventory;

    public HutilItaireEntity(){

    }

    public static Villager getEntity(Location loc){
        setInventory();
        utilitaire = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        utilitaire.setCustomName("Hutil Itaire");
        utilitaire.setCustomNameVisible(true);
        utilitaire.setVillagerType(Villager.Type.PLAINS);
        utilitaire.setProfession(Villager.Profession.CARTOGRAPHER);
        utilitaire.setAI(false);
        utilitaire.setInvulnerable(true);
        utilitaire.setSilent(true);
        utilitaire.setCollidable(false);
        utilitaire.setVillagerExperience(5);
        utilitaire.setVillagerLevel(5);
        utilitaire.setAdult();
        utilitaire.setCanPickupItems(false);
        utilitaire.setRemoveWhenFarAway(false);

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
        int price = (int) (Math.random() * 7) + 1;
        trades.get(0).addIngredient(new ItemStack(Material.CRAFTING_TABLE, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 7) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.STONECUTTER, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 7) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.CARTOGRAPHY_TABLE, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 7) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.FLETCHING_TABLE, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 7) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.SMITHING_TABLE, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 7) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.GRINDSTONE, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 7) + 1;
        trades.get(6).addIngredient(new ItemStack(Material.LOOM, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 7) + 1;
        trades.get(7).addIngredient(new ItemStack(Material.FURNACE, price));

        //trade 9
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 7) + 1;
        trades.get(8).addIngredient(new ItemStack(Material.SMOKER, price));

        //trade 10
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 7) + 1;
        trades.get(9).addIngredient(new ItemStack(Material.BLAST_FURNACE, price));

        //trade 11
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 7) + 1;
        trades.get(10).addIngredient(new ItemStack(Material.NOTE_BLOCK, price));

        //trade 12
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), 1));
        // random price
        price = (int) (Math.random() * 7) + 1;
        trades.get(11).addIngredient(new ItemStack(Material.JUKEBOX, price));

        utilitaire.setRecipes(trades);

        return utilitaire;
    }

    public static void setInventory(){
        sampleInventory = new SampleInventory(18, "Hutil Itaire");
        //CRAFTING_TABLE (amount 1-3, price 2-15)
        int amount = (int) (Math.random() * 3) + 1;
        int price = (int) (Math.random() * 15) + 2;
        sampleInventory.addItem(new ItemBuilder(Material.CRAFTING_TABLE).amount(amount).addLore("" + price + " Poudres").build(), 0, price);
        //STONECUTTER (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.STONECUTTER).amount(amount).addLore("" + price + " Poudres").build(), 1, price);
        //CARTOGRAPHY_TABLE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 31) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.CARTOGRAPHY_TABLE).amount(amount).addLore("" + price + " Poudres").build(), 2, price);
        //FLETCHING_TABLE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.FLETCHING_TABLE).amount(amount).addLore("" + price + " Poudres").build(), 3, price);
        //SMITHING_TABLE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.SMITHING_TABLE).amount(amount).addLore("" + price + " Poudres").build(), 4, price);
        //GRINDSTONE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.GRINDSTONE).amount(amount).addLore("" + price + " Poudres").build(), 5, price);
        //LOOM (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.LOOM).amount(amount).addLore("" + price + " Poudres").build(), 6, price);
        //FURNACE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.FURNACE).amount(amount).addLore("" + price + " Poudres").build(), 7, price);
        //SMOKER (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.SMOKER).amount(amount).addLore("" + price + " Poudres").build(), 8, price);
        //BLAST_FURNACE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.BLAST_FURNACE).amount(amount).addLore("" + price + " Poudres").build(), 9, price);
        //NOTE_BLOCK (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.NOTE_BLOCK).amount(amount).addLore("" + price + " Poudres").build(), 10, price);
        //JUKEBOX (amount 1-3, price 70-150)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 150) + 70;
        sampleInventory.addItem(new ItemBuilder(Material.JUKEBOX).amount(amount).addLore("" + price + " Poudres").build(), 11, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}
