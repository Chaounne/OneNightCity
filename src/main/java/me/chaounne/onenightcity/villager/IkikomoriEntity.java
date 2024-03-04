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

public class IkikomoriEntity {

    private static Villager nolife;
    private static SampleInventory sampleInventory;

    public IkikomoriEntity(){

    }

    public static Villager getEntity(Location loc){
        setInventory();
        nolife = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        nolife.setCustomName("Ikikomori");
        nolife.setCustomNameVisible(true);
        nolife.setVillagerType(Villager.Type.PLAINS);
        nolife.setProfession(Villager.Profession.TOOLSMITH);
        nolife.setAI(false);
        nolife.setInvulnerable(true);
        nolife.setSilent(true);
        nolife.setCollidable(false);
        nolife.setVillagerExperience(5);
        nolife.setVillagerLevel(5);
        nolife.setAdult();
        nolife.setCanPickupItems(false);
        nolife.setRemoveWhenFarAway(false);

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
        int amount = (int) (Math.random() * 15) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        int price = (int) (Math.random() * 5) + 5;
        trades.get(0).addIngredient(new ItemStack(Material.MOSS_BLOCK, price));

        //trade 2
        // random amount of poudre
        amount = (int) (Math.random() * 15) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(1).addIngredient(new ItemStack(Material.MOSS_CARPET, price));

        //trade 3
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(2).addIngredient(new ItemStack(Material.OCHRE_FROGLIGHT, price));

        //trade 4
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(3).addIngredient(new ItemStack(Material.PEARLESCENT_FROGLIGHT, price));

        //trade 5
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(4).addIngredient(new ItemStack(Material.VERDANT_FROGLIGHT, price));

        //trade 6
        // random amount of poudre
        amount = (int) (Math.random() * 5) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 5) + 1;
        trades.get(5).addIngredient(new ItemStack(Material.AXOLOTL_BUCKET, price));

        //trade 7
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 5;
        trades.get(6).addIngredient(new ItemStack(Material.MANGROVE_PLANKS, price));

        //trade 8
        // random amount of poudre
        amount = (int) (Math.random() * 10) + 1;
        trades.add(new MerchantRecipe(PoudreItem.getItem(amount), Integer.MAX_VALUE));
        // random price
        price = (int) (Math.random() * 10) + 10;
        trades.get(7).addIngredient(new ItemStack(Material.MANGROVE_LEAVES, price));

        nolife.setRecipes(trades);

        return nolife;
    }
    public static void setInventory(){
        sampleInventory = new SampleInventory(9, "Ikikomori");
        //MOSS_BLOCK (amount 1-15, price 2-35)
        int amount = (int) (Math.random() * 15) + 1;
        int price = (int) (Math.random() * 35) + 2;
        sampleInventory.addItem(new ItemBuilder(Material.MOSS_BLOCK).amount(amount).addLore("" + price + " Poudres").build(), 0, price);
        //MOSS_CARPET (amount 1-15, price 2-25)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 35) + 2;
        sampleInventory.addItem(new ItemBuilder(Material.MOSS_CARPET).amount(amount).addLore("" + price + " Poudres").build(), 1, price);
        //OCHRE_FROGLIGHT (amount 1-5, price 50-150)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.OCHRE_FROGLIGHT).amount(amount).addLore("" + price + " Poudres").build(), 2, price);
        //PEARLESCENT_FROGLIGHT (amount 1-5, price 50-150)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.PEARLESCENT_FROGLIGHT).amount(amount).addLore("" + price + " Poudres").build(), 3, price);
        //VERDANT_FROGLIGHT (amount 1-5, price 50-150)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.VERDANT_FROGLIGHT).amount(amount).addLore("" + price + " Poudres").build(), 4, price);
        //AXOLOTL_BUCKET (amount 1, price 20-50)
        amount = 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.AXOLOTL_BUCKET).amount(amount).addLore("" + price + " Poudres").build(), 5, price);
        //MANGROVE_PLANKS (amount 1-20, price 5-10)
        amount = (int) (Math.random() * 20) + 1;
        price = (int) (Math.random() * 15) + 5;
        sampleInventory.addItem(new ItemBuilder(Material.MANGROVE_PLANKS).amount(amount).addLore("" + price + " Poudres").build(), 6, price);
        //MANGROVE_LEAVES (amount 1-10, price 4-10)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 15) + 4;
        sampleInventory.addItem(new ItemBuilder(Material.MANGROVE_LEAVES).amount(amount).addLore("" + price + " Poudres").build(), 7, price);
    }

    public static void openInventory(Player player){
        sampleInventory.open(player);
    }

}
