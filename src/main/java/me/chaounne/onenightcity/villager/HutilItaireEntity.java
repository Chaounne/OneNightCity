package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class HutilItaireEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager utilitaire = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

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
        utilitaire.setRecipes(new ArrayList<>());

        return utilitaire;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(18, "Hutil Itaire");
        //CRAFTING_TABLE (amount 1-3, price 2-15)
        int amount = (int) (Math.random() * 3) + 1;
        int price = (int) (Math.random() * 15) + 2;
        sampleInventory.addItem(new ItemBuilder(Material.CRAFTING_TABLE).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //STONECUTTER (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.STONECUTTER).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //CARTOGRAPHY_TABLE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 31) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.CARTOGRAPHY_TABLE).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //FLETCHING_TABLE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.FLETCHING_TABLE).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //SMITHING_TABLE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.SMITHING_TABLE).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //GRINDSTONE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.GRINDSTONE).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //LOOM (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.LOOM).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //FURNACE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.FURNACE).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //SMOKER (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.SMOKER).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //BLAST_FURNACE (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.BLAST_FURNACE).amount(amount).addLore(price + " Poudres").build(), 9, price);
        //NOTE_BLOCK (amount 1-3, price 20-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.NOTE_BLOCK).amount(amount).addLore(price + " Poudres").build(), 10, price);
        //JUKEBOX (amount 1-3, price 70-150)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 150) + 70;
        sampleInventory.addItem(new ItemBuilder(Material.JUKEBOX).amount(amount).addLore(price + " Poudres").build(), 11, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}