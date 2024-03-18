package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class IkikomoriEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager nolife = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        nolife.setCustomName("Ikikomori");
        nolife.setCustomNameVisible(true);
        nolife.setVillagerType(Villager.Type.PLAINS);
        nolife.setProfession(Villager.Profession.TOOLSMITH);
        nolife.setAI(false);
        nolife.setInvulnerable(true);
        nolife.setSilent(true);
        nolife.setCollidable(false);
        nolife.setVillagerExperience(5);
        nolife.setVillagerLevel(5);
        nolife.setAdult();
        nolife.setCanPickupItems(false);
        nolife.setRemoveWhenFarAway(false);
        nolife.setRecipes(new ArrayList<>());

        return nolife;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(9, "Ikikomori");
        //MOSS_BLOCK (amount 1-15, price 2-35)
        int amount = (int) (Math.random() * 15) + 1;
        int price = (int) (Math.random() * 35) + 2;
        sampleInventory.addItem(new ItemBuilder(Material.MOSS_BLOCK).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //MOSS_CARPET (amount 1-15, price 2-25)
        amount = (int) (Math.random() * 15) + 1;
        price = (int) (Math.random() * 35) + 2;
        sampleInventory.addItem(new ItemBuilder(Material.MOSS_CARPET).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //OCHRE_FROGLIGHT (amount 1-5, price 50-150)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.OCHRE_FROGLIGHT).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //PEARLESCENT_FROGLIGHT (amount 1-5, price 50-150)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.PEARLESCENT_FROGLIGHT).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //VERDANT_FROGLIGHT (amount 1-5, price 50-150)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 150) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.VERDANT_FROGLIGHT).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //AXOLOTL_BUCKET (amount 1, price 20-50)
        amount = 1;
        price = (int) (Math.random() * 50) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.AXOLOTL_BUCKET).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //MANGROVE_PLANKS (amount 1-20, price 5-10)
        amount = (int) (Math.random() * 20) + 1;
        price = (int) (Math.random() * 15) + 5;
        sampleInventory.addItem(new ItemBuilder(Material.MANGROVE_PLANKS).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //MANGROVE_LEAVES (amount 1-10, price 4-10)
        amount = (int) (Math.random() * 10) + 1;
        price = (int) (Math.random() * 15) + 4;
        sampleInventory.addItem(new ItemBuilder(Material.MANGROVE_LEAVES).amount(amount).addLore(price + " Poudres").build(), 7, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}