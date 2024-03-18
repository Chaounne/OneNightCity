package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class MicoseMicodeEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager champi = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        champi.setCustomName("Micose Micode");
        champi.setCustomNameVisible(true);
        champi.setVillagerType(Villager.Type.SWAMP);
        champi.setProfession(Villager.Profession.FLETCHER);
        champi.setAI(false);
        champi.setInvulnerable(true);
        champi.setSilent(true);
        champi.setCollidable(false);
        champi.setVillagerExperience(5);
        champi.setVillagerLevel(5);
        champi.setAdult();
        champi.setCanPickupItems(false);
        champi.setRemoveWhenFarAway(false);
        champi.setRecipes(new ArrayList<>());

        return champi;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(9, "Micose Micode");
        //MYCELIUM (amount 1-5, price 50-200)
        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 200) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.MYCELIUM).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //RED_MUSHROOM_BLOCK (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.RED_MUSHROOM_BLOCK).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //BROWN_MUSHROOM_BLOCK (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.BROWN_MUSHROOM_BLOCK).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //MUSHROOM_STEM (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.MUSHROOM_STEM).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //SHROOMLIGHT (amount 1-5, price 20-50)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.SHROOMLIGHT).amount(amount).addLore(price + " Poudres").build(), 4, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}
