package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class JeaneauEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager jean = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        jean.setCustomName("Jeaneau");
        jean.setCustomNameVisible(true);
        jean.setVillagerType(Villager.Type.PLAINS);
        jean.setProfession(Villager.Profession.FISHERMAN);
        jean.setAI(false);
        jean.setInvulnerable(true);
        jean.setSilent(true);
        jean.setCollidable(false);
        jean.setVillagerExperience(5);
        jean.setVillagerLevel(5);
        jean.setAdult();
        jean.setCanPickupItems(false);
        jean.setRemoveWhenFarAway(false);
        jean.setRecipes(new ArrayList<>());

        return jean;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(9, "Jeaneau");
        //PRISMARINE (amount 1-15, price 10-50)
        int amount = (int) (Math.random() * 15) + 1;
        int price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //PRISMARINE_SHARD (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_SHARD).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //PRISMARINE_BRICKS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_BRICKS).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //PRISMARINE_CRYSTALS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_CRYSTALS).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //PRISMARINE_BRICK_SLAB (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_BRICK_SLAB).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //PRISMARINE_BRICK_STAIRS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_BRICK_STAIRS).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //PRISMARINE_SLAB (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_SLAB).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //PRISMARINE_STAIRS (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_STAIRS).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //PRISMARINE_WALL (amount 1-15, price 10-50)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PRISMARINE_WALL).amount(amount).addLore(price + " Poudres").build(), 8, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}