package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class NeigeuDemotEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager neigeux = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        neigeux.setCustomName("Négeux Demo");
        neigeux.setCustomNameVisible(true);
        neigeux.setVillagerType(Villager.Type.SNOW);
        neigeux.setProfession(Villager.Profession.MASON);
        neigeux.setAI(false);
        neigeux.setInvulnerable(true);
        neigeux.setSilent(true);
        neigeux.setCollidable(false);
        neigeux.setVillagerExperience(5);
        neigeux.setVillagerLevel(5);
        neigeux.setAdult();
        neigeux.setCanPickupItems(false);
        neigeux.setRemoveWhenFarAway(false);
        neigeux.setRecipes(new ArrayList<>());

        return neigeux;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(9, "Négeux Demo");
        //SNOWBALL (amount 1-5, price 1-15)
        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 15) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.SNOWBALL).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //POWDER_SNOW_BUCKET (amount 1, price 1-15)
        amount = 1;
        price = (int) (Math.random() * 15) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.POWDER_SNOW_BUCKET).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //SNOW (amount 1-5, price 5-25)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 25) + 5;
        sampleInventory.addItem(new ItemBuilder(Material.SNOW).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //SNOW_BLOCK (amount 1-5, price 5-30)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 60) + 4;
        sampleInventory.addItem(new ItemBuilder(Material.SNOW_BLOCK).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //CARVED_PUMPKIN (amount 1-5, price 2-25)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 25) + 2;
        sampleInventory.addItem(new ItemBuilder(Material.CARVED_PUMPKIN).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //ICE (amount 1-5, price 1-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.ICE).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //BLUE_ICE (amount 1-5, price 1-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.BLUE_ICE).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //PACKED_ICE (amount 1-5, price 1-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.PACKED_ICE).amount(amount).addLore(price + " Poudres").build(), 7, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}