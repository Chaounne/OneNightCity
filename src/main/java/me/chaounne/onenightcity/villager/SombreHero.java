package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class SombreHero extends Trader {

    private static Trader instance;

    public SombreHero(Location loc) {
        super(loc, "Sombre Héros", Villager.Type.SWAMP, Villager.Profession.ARMORER, 18);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 70) + 20;
        inventory.addItem(new ItemBuilder(Material.SCULK).amount(amount).addLore(price + " Poudres").build(), 0, price);

        amount = (int) (Math.random() * 2) + 1;
        price = (int) (Math.random() * 90) + 50;
        inventory.addItem(new ItemBuilder(Material.SCULK_VEIN).amount(amount).addLore(price + " Poudres").build(), 1, price);

        amount = (int) (Math.random() * 2) + 1;
        price = (int) (Math.random() * 150) + 50;
        inventory.addItem(new ItemBuilder(Material.SCULK_CATALYST).amount(amount).addLore(price + " Poudres").build(), 2, price);

        amount = (int) (Math.random() * 2) + 1;
        price = (int) (Math.random() * 150) + 50;
        inventory.addItem(new ItemBuilder(Material.SCULK_SENSOR).amount(amount).addLore(price + " Poudres").build(), 3, price);

        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 10) + 1;
        inventory.addItem(new ItemBuilder(Material.COBWEB).amount(amount).addLore(price + " Poudres").build(), 4, price);

        amount = 1;
        price = (int) (Math.random() * 5000) + 1000;
        inventory.addItem(new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE).amount(amount).addLore(price + " Poudres").build(), 5, price);

        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 8) + 1;
        inventory.addItem(new ItemBuilder(Material.DEEPSLATE_BRICKS).amount(amount).addLore(price + " Poudres").build(), 6, price);

        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 200) + 50;
        inventory.addItem(new ItemBuilder(Material.CANDLE).amount(amount).addLore(price + " Poudres").build(), 7, price);

        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 20) + 5;
        inventory.addItem(new ItemBuilder(Material.SOUL_LANTERN).amount(amount).addLore(price + " Poudres").build(), 8, price);

        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 8) + 5;
        inventory.addItem(new ItemBuilder(Material.POLISHED_DEEPSLATE).amount(amount).addLore(price + " Poudres").build(), 9, price);
    }

}