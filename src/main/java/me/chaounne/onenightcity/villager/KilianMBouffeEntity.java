package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class KilianMBouffeEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager MBouffe = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

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
        MBouffe.setRecipes(new ArrayList<>());

        return MBouffe;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(18, "Kylian MBouffé");
        //COOKED_BEEF (amount 1-10, price 1-20)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.COOKED_BEEF).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //COOKED_CHICKEN (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.COOKED_CHICKEN).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //COOKED_PORKCHOP (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.COOKED_PORKCHOP).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //COOKED_MUTTON (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.COOKED_MUTTON).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //COOKED_RABBIT (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.COOKED_RABBIT).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //RABBIT_STEW (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.RABBIT_STEW).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //PUMPKIN_PIE (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.PUMPKIN_PIE).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //COOKIE (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.COOKIE).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //SWEET_BERRIES (amount 1-5, price 20-100)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 25) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.SWEET_BERRIES).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //DRIED_KELP (amount 1-10, price 1-10)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 10) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.DRIED_KELP).amount(amount).addLore(price + " Poudres").build(), 9, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}