package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class JykaRoulerEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager potter = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        potter.setCustomName("Jyka Rouler");
        potter.setCustomNameVisible(true);
        potter.setVillagerType(Villager.Type.PLAINS);
        potter.setProfession(Villager.Profession.LIBRARIAN);
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
        sampleInventory = new SampleInventory(9, "Jyka Rouler");
        //BOOK (amount 1-5, price 10-50)
        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.BOOK).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //BOOKSHELF (amount 1-5, price 60-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 60;
        sampleInventory.addItem(new ItemBuilder(Material.BOOKSHELF).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //LECTERN (amount 1-5, price 10-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.LECTERN).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //CROSSBOW (amount 1-5, price 10-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.CROSSBOW).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //INK_SAC (amount 1-5, price 10-100)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.INK_SAC).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //GLOW_INK_SAC (amount 1-5, price 10-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.GLOW_INK_SAC).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //WRITABLE_BOOK (amount 1-5, price 10-70)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 70) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.WRITABLE_BOOK).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //FEATHER (amount 1-10, price 1-20)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 20) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.FEATHER).amount(amount).addLore(price + " Poudres").build(), 7, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}