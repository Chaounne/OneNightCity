package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class JustinPuechEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager justin = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        justin.setCustomName("Justin Puech");
        justin.setCustomNameVisible(true);
        justin.setVillagerType(Villager.Type.SNOW);
        justin.setProfession(Villager.Profession.FISHERMAN);
        justin.setAI(false);
        justin.setInvulnerable(true);
        justin.setSilent(true);
        justin.setCollidable(false);
        justin.setVillagerExperience(5);
        justin.setVillagerLevel(5);
        justin.setAdult();
        justin.setCanPickupItems(false);
        justin.setRemoveWhenFarAway(false);
        justin.setRecipes(new ArrayList<>());

        return justin;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(18, "Justin Puech");
        //FISHING_ROD (amount 1, price 10-50)
        int amount = 1;
        int price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.FISHING_ROD).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //WATER_BUCKET (amount 1, price 20-40)
        price = (int) (Math.random() * 40) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.WATER_BUCKET).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //PUFFERFISH (amount 1, price 50-150)
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.PUFFERFISH).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //COOKED_COD (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.COOKED_COD).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //TROPICAL_FISH (amount 2-3, price 150-200)
        amount = (int) (Math.random() * 2) + 2;
        price = (int) (Math.random() * 51) + 150;
        sampleInventory.addItem(new ItemBuilder(Material.TROPICAL_FISH).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //COD_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.COD_BUCKET).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //SALMON_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.SALMON_BUCKET).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //PUFFERFISH_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.PUFFERFISH_BUCKET).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //TROPICAL_FISH_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.TROPICAL_FISH_BUCKET).amount(amount).addLore(price + " Poudres").build(), 9, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}