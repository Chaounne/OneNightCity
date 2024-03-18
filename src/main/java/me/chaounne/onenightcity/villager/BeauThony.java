package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.utils.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class BeauThony extends Trader {

    private static Trader instance;

    private BeauThony(Location loc) {
        super(loc, "Beau Thony", Villager.Type.JUNGLE, Villager.Profession.CARTOGRAPHER, 18);
        instance = this;
    }

    public static void create(Location loc) {
        if (instance == null)
            new BeauThony(loc);
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    protected void addTrades() {
        int price = Random.between(50, 150);
        inventory.addItem(new ItemBuilder(Material.ACACIA_SAPLING).amount(Random.between(3, 10)).addLore(price + " Poudres").build(), 0, price);

        price = Random.between(5, 15);
        inventory.addItem(new ItemBuilder(Material.BAMBOO).amount(Random.between(5, 10)).addLore(price + " Poudres").build(), 1, price);

        price = Random.between(50, 150);
        inventory.addItem(new ItemBuilder(Material.BIRCH_SAPLING).amount(Random.between(3, 12)).addLore(price + " Poudres").build(), 2, price);

        price = Random.between(50, 150);
        inventory.addItem(new ItemBuilder(Material.DARK_OAK_SAPLING).amount(Random.between(3, 10)).addLore(price + " Poudres").build(), 3, price);

        price = Random.between(50, 150);
        inventory.addItem(new ItemBuilder(Material.SPRUCE_SAPLING).amount(Random.between(3, 10)).addLore(price + " Poudres").build(), 4, price);

        price = Random.between(50, 150);
        inventory.addItem(new ItemBuilder(Material.JUNGLE_SAPLING).amount(Random.between(3, 10)).addLore(price + " Poudres").build(), 5, price);

        price = Random.between(50, 150);
        inventory.addItem(new ItemBuilder(Material.OAK_SAPLING).amount(Random.between(5, 10)).addLore(price + " Poudres").build(), 6, price);

        price = Random.between(50, 150);
        inventory.addItem(new ItemBuilder(Material.FLOWER_POT).amount(Random.between(1, 10)).addLore(price + " Poudres").build(), 7, price);

        price = Random.between(50, 150);
        inventory.addItem(new ItemBuilder(Material.MANGROVE_PROPAGULE).amount(Random.between(3, 10)).addLore(price + " Poudres").build(), 8, price);

        price = Random.between(50, 150);
        inventory.addItem(new ItemBuilder(Material.AZALEA).amount(Random.between(1, 10)).addLore(price + " Poudres").build(), 9, price);

        price = Random.between(50, 150);
        inventory.addItem(new ItemBuilder(Material.AZALEA_LEAVES).amount(Random.between(1, 10)).addLore(price + " Poudres").build(), 10, price);
    }

}