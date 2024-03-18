package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class ClodoFrancisEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager potter = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

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
        potter.setRecipes(new ArrayList<>());

        return potter;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(9, "Francis Clodo");
        //GLASS_BOTTLE (amount 2-10, price 6-25)
        int amount = (int) (Math.random() * 10) + 2;
        int price = (int) (Math.random() * 30) + 6;
        sampleInventory.addItem(new ItemBuilder(Material.GLASS_BOTTLE).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //HONEY_BOTTLE (amount 2-10, price 15-25)
        amount = (int) (Math.random() * 10) + 2;
        price = (int) (Math.random() * 30) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.HONEY_BOTTLE).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //MILK_BUCKET (amount 2-5, price 20-40)
        amount = (int) (Math.random() * 5) + 2;
        price = (int) (Math.random() * 20) + 40;
        sampleInventory.addItem(new ItemBuilder(Material.MILK_BUCKET).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //BEETROOT_SOUP (amount 1, price 15-35)
        amount = 1;
        price = (int) (Math.random() * 45) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.BEETROOT_SOUP).amount(amount).addLore( price + " Poudres").build(), 3, price);
        //MUSHROOM_STEW (amount 1, price 15-35)
        price = (int) (Math.random() * 35) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.MUSHROOM_STEW).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //POTION (amount 2-10, price 6-25)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 45) + 6;
        sampleInventory.addItem(new ItemBuilder(Material.POTION).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //ENCHANTING_TABLE (amount 1-3, price 150-500)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 500) + 150;
        sampleInventory.addItem(new ItemBuilder(Material.ENCHANTING_TABLE).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //WRITABLE_BOOK (amount 1-3, price 15-35)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 55) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.WRITABLE_BOOK).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //SPLASH_POTION (amount 1-3, price 50-150)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.SPLASH_POTION).amount(amount).addLore(price + " Poudres").build(), 8, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}