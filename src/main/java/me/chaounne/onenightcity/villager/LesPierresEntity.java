package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class LesPierresEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager aypierre = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        aypierre.setCustomName("Les Pierres");
        aypierre.setCustomNameVisible(true);
        aypierre.setVillagerType(Villager.Type.SWAMP);
        aypierre.setProfession(Villager.Profession.MASON);
        aypierre.setAI(false);
        aypierre.setSilent(true);
        aypierre.setCollidable(false);
        aypierre.setVillagerExperience(5);
        aypierre.setVillagerLevel(5);
        aypierre.setAdult();
        aypierre.setCanPickupItems(false);
        aypierre.setRemoveWhenFarAway(false);
        aypierre.setRecipes(new ArrayList<>());

        return aypierre;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(18, "Les Pierres");
        //REDSTONE_BLOCK (amount 1-10, price 20-250)
        int amount = (int) (Math.random() * 10) + 1;
        int price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.REDSTONE_BLOCK).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //COMPARATOR (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.COMPARATOR).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //TARGET (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.TARGET).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //HOPPER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.HOPPER).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //DISPENSER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.DISPENSER).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //DROPPER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.DROPPER).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //OBSERVER (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.OBSERVER).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //PISTON (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 250) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.PISTON).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //STICKY_PISTON (amount 1-10, price 20-250)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 300) + 30;
        sampleInventory.addItem(new ItemBuilder(Material.STICKY_PISTON).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //TRIPWIRE_HOOK (amount 4-10, price 10-100)
        amount = (int) (Math.random() * 7) + 4;
        price = (int) (Math.random() * 100) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.TRIPWIRE_HOOK).amount(amount).addLore(price + " Poudres").build(), 9, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}