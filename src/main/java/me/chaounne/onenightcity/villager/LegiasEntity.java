package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class LegiasEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager legolas = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        legolas.setCustomName("Legias");
        legolas.setCustomNameVisible(true);
        legolas.setVillagerType(Villager.Type.SWAMP);
        legolas.setProfession(Villager.Profession.FLETCHER);
        legolas.setAI(false);
        legolas.setSilent(true);
        legolas.setCollidable(false);
        legolas.setVillagerExperience(5);
        legolas.setVillagerLevel(5);
        legolas.setAdult();
        legolas.setCanPickupItems(false);
        legolas.setRemoveWhenFarAway(false);
        legolas.setRecipes(new ArrayList<>());

        return legolas;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(9, "Legias");
        //ARROW (amount 4-16, price 20-30)
        int amount = (int) (Math.random() * 13) + 4;
        int price = (int) (Math.random() * 30) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.ARROW).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //BOW (amount 1, price 10-200)
        amount = 1;
        price = (int) (Math.random() * 200) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.BOW).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //SPECTRAL_ARROW (amount 4-16, price 10-150)
        amount = (int) (Math.random() * 13) + 4;
        price = (int) (Math.random() * 150) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.SPECTRAL_ARROW).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //CROSSBOW (amount 1, price 40-250)
        amount = 1;
        price = (int) (Math.random() * 250) + 40;
        sampleInventory.addItem(new ItemBuilder(Material.CROSSBOW).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //BIG_DRIPLEAF (amount 1-10, price 15-30)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 30) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.BIG_DRIPLEAF).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //SMALL_DRIPLEAF (amount 1-10, price 15-30)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 30) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.SMALL_DRIPLEAF).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //SPORE_BLOSSOM (amount 1-5, price 40-250)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 250) + 40;
        sampleInventory.addItem(new ItemBuilder(Material.SPORE_BLOSSOM).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //VINE (amount 1-10, price 15-30)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 30) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.VINE).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //LILY_PAD (amount 1-10, price 15-30)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 30) + 15;
        sampleInventory.addItem(new ItemBuilder(Material.LILY_PAD).amount(amount).addLore(price + " Poudres").build(), 8, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}