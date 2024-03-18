package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class BeauThonyEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager thony = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        thony.setCustomName("Beau Thony");
        thony.setCustomNameVisible(true);
        thony.setVillagerType(Villager.Type.JUNGLE);
        thony.setProfession(Villager.Profession.CARTOGRAPHER);
        thony.setAI(false);
        thony.setInvulnerable(true);
        thony.setSilent(true);
        thony.setCollidable(false);
        thony.setVillagerExperience(5);
        thony.setVillagerLevel(5);
        thony.setAdult();
        thony.setCanPickupItems(false);
        thony.setRemoveWhenFarAway(false);
        thony.setRecipes(new ArrayList<>());

        return thony;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(18, "Beau Thony");
        //acacia_sapling (amount 1-10, price 50-150)
        int amount = (int) (Math.random() * 10) + 3;
        int price = (int) (Math.random() * 100) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.ACACIA_SAPLING).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //bamboo (amount 1-10, price 2-15)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 15) + 2;
        sampleInventory.addItem(new ItemBuilder(Material.BAMBOO).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //birch_sapling (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 3;
        price = (int) (Math.random() * 100) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.BIRCH_SAPLING).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //dark_oak_sapling (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 3;
        price = (int) (Math.random() * 100) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.DARK_OAK_SAPLING).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //spruce_sapling (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 3;
        price = (int) (Math.random() * 100) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.SPRUCE_SAPLING).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //jungle_sapling (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 3;
        price = (int) (Math.random() * 100) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.JUNGLE_SAPLING).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //oak_sapling (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 3;
        price = (int) (Math.random() * 100) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.OAK_SAPLING).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //flower_pot (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.FLOWER_POT).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //mangrove_propagule (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 3;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.MANGROVE_PROPAGULE).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //azalea (amount 1-10, price 50-150)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.AZALEA).amount(amount).addLore(price + " Poudres").build(), 9, price);
        //azalea_leaves (1-10, 50-150)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.AZALEA_LEAVES).amount(amount).addLore(price + " Poudres").build(), 10, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}