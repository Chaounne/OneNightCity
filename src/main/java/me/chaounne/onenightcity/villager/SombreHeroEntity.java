package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class SombreHeroEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager warden = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        warden.setCustomName("Sombre Héros");
        warden.setCustomNameVisible(true);
        warden.setVillagerType(Villager.Type.SWAMP);
        warden.setProfession(Villager.Profession.ARMORER);
        warden.setAI(false);
        warden.setInvulnerable(true);
        warden.setSilent(true);
        warden.setCollidable(false);
        warden.setVillagerExperience(5);
        warden.setVillagerLevel(5);
        warden.setAdult();
        warden.setCanPickupItems(false);
        warden.setRemoveWhenFarAway(false);
        warden.setRecipes(new ArrayList<>());

        return warden;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(18, "Sombre Héros");

        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 70) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.SCULK).amount(amount).addLore(price + " Poudres").build(), 0, price);

        amount = (int) (Math.random() * 2) + 1;
        price = (int) (Math.random() * 90) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.SCULK_VEIN).amount(amount).addLore(price + " Poudres").build(), 1, price);

        amount = (int) (Math.random() * 2) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.SCULK_CATALYST).amount(amount).addLore(price + " Poudres").build(), 2, price);

        amount = (int) (Math.random() * 2) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.SCULK_SENSOR).amount(amount).addLore(price + " Poudres").build(), 3, price);

        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 10) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.COBWEB).amount(amount).addLore(price + " Poudres").build(), 4, price);

        amount = 1;
        price = (int) (Math.random() * 5000) + 1000;
        sampleInventory.addItem(new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).amount(amount).addLore(price + " Poudres").build(), 5, price);

        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 8) + 1;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_BRICKS).amount(amount).addLore(price + " Poudres").build(), 6, price);

        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 200) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.CANDLE).amount(amount).addLore(price + " Poudres").build(), 7, price);

        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 20) + 5;
        sampleInventory.addItem(new ItemBuilder(Material.SOUL_LANTERN).amount(amount).addLore(price + " Poudres").build(), 8, price);

        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 8) + 5;
        sampleInventory.addItem(new ItemBuilder(Material.POLISHED_DEEPSLATE).amount(amount).addLore(price + " Poudres").build(), 9, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}