package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class JeanMineurEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager jean = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        jean.setCustomName("Jean Mineur");
        jean.setCustomNameVisible(true);
        jean.setVillagerType(Villager.Type.DESERT);
        jean.setProfession(Villager.Profession.LEATHERWORKER);
        jean.setAI(false);
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
        sampleInventory = new SampleInventory(18, "Jean Mineur");
        //COAL (amount 1-15, price 10-50)
        int amount = (int) (Math.random() * 15) + 1;
        int price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.COAL).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //DEEPSLATE_COAL_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 1000) + 500;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_COAL_ORE).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //IRON_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.IRON_ORE).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //DEEPSLATE_IRON_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_IRON_ORE).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //COPPER_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.COPPER_ORE).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //DEEPSLATE_COPPER_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_COPPER_ORE).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //GOLD_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.GOLD_ORE).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //DEEPSLATE_GOLD_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 20;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_GOLD_ORE).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //REDSTONE_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 500) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.REDSTONE_ORE).amount(amount).addLore(price + " Poudres").build(), 8, price);
        //DEEPSLATE_REDSTONE_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 100) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_REDSTONE_ORE).amount(amount).addLore(price + " Poudres").build(), 9, price);
        //EMERALD_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 0) + 1;
        price = (int) (Math.random() * 1000) + 500;
        sampleInventory.addItem(new ItemBuilder(Material.EMERALD_ORE).amount(amount).addLore(price + " Poudres").build(), 10, price);
        //DEEPSLATE_EMERALD_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 0) + 1;
        price = (int) (Math.random() * 1000) + 500;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_EMERALD_ORE).amount(amount).addLore(price + " Poudres").build(), 11, price);
        //LAPIS_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 500) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.LAPIS_ORE).amount(amount).addLore(price + " Poudres").build(), 12, price);
        //DEEPSLATE_LAPIS_ORE (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 500) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_LAPIS_ORE).amount(amount).addLore(price + " Poudres").build(), 13, price);
        //DIAMOND_ORE (amount 1-5, price 50-300)
        amount = (int) (Math.random() * 0) + 1;
        price = (int) (Math.random() * 1000) + 500;
        sampleInventory.addItem(new ItemBuilder(Material.DIAMOND_ORE).amount(amount).addLore(price + " Poudres").build(), 14, price);
        //DEEPSLATE_DIAMOND_ORE (amount 1-5, price 50-300)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        sampleInventory.addItem(new ItemBuilder(Material.DEEPSLATE_DIAMOND_ORE).amount(amount).addLore(price + " Poudres").build(), 15, price);
        //NETHER_GOLD_ORE (amount 1-5, price 50-150)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 50) + 10;
        sampleInventory.addItem(new ItemBuilder(Material.NETHER_GOLD_ORE).amount(amount).addLore(price + " Poudres").build(), 16, price);
        //ANCIENT_DEBRIS (amount 1-3, price 100-500)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 1000) + 500;
        sampleInventory.addItem(new ItemBuilder(Material.ANCIENT_DEBRIS).amount(amount).addLore(price + " Poudres").build(), 17, price);
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}