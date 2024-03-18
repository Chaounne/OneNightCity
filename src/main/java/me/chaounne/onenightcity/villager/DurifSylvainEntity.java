package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class DurifSylvainEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager durif = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

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
        durif.setRecipes(new ArrayList<>());

        return durif;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(18, "Civique Cosmique Durif");
        //CHORUS_FRUIT (amount 1-3, price 15-35)
        int amount = (int) (Math.random() * 3) + 1;
        int price = (int) (Math.random() * 21) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.CHORUS_FRUIT).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //CHORUS_FLOWER (amount 1-3, price 10-30)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 21) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.CHORUS_FLOWER).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //PURPUR_BLOCK (amount 1-3, price 50-80)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 31) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.PURPUR_BLOCK).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //PURPUR_PILLAR (amount 1-3, price 50-80)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 31) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.PURPUR_PILLAR).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //PURPUR_SLAB (amount 1-3, price 50-60)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 11) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.PURPUR_SLAB).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //PURPUR_STAIRS (amount 1-3, price 50-60)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 11) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.PURPUR_STAIRS).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //END_STONE_BRICKS (amount 5-10, price 10-50)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 41) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.END_STONE_BRICKS).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //END_STONE_BRICK_SLAB (amount 5-10, price 10-25)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 16) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.END_STONE_BRICK_SLAB).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //END_STONE_BRICK_STAIRS (amount 5-10, price 10-50)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 41) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.END_STONE_BRICK_STAIRS).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //END_STONE_BRICK_WALL (amount 5-10, price 10-50)
        amount = (int) (Math.random() * 6) + 5;
        price = (int) (Math.random() * 41) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.END_STONE_BRICK_WALL).amount(amount).addLore(price + " Poudres").build(), 9, price);
        //END_CRYSTAL (amount 1, price 100-500)
        amount = 1;
        price = (int) (Math.random() * 401) + 100;
        sampleInventory.addItem(new ItemBuilder(Material.END_CRYSTAL).amount(amount).addLore(price + " Poudres").build(), 10, price);
        //END_ROD (amount 1-5, price 5-110)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 106) + 5;
        sampleInventory.addItem(new ItemBuilder(Material.END_ROD).amount(amount).addLore(price + " Poudres").build(), 11, price);
        //OBSIDIAN (amount 1-10, price 50-100)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 51) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.OBSIDIAN).amount(amount).addLore(price + " Poudres").build(), 12, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}