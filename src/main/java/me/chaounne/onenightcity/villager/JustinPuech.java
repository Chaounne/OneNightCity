package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class JustinPuech extends Trader {

    private static Trader instance;

    public JustinPuech(Location loc) {
        super(loc, "Justin Puech", Villager.Type.SNOW, Villager.Profession.FISHERMAN, 9);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        //FISHING_ROD (amount 1, price 10-50)
        int amount = 1;
        int price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.FISHING_ROD).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //WATER_BUCKET (amount 1, price 20-40)
        price = (int) (Math.random() * 40) + 20;
        inventory.addItem(new ItemBuilder(Material.WATER_BUCKET).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //PUFFERFISH (amount 1, price 50-150)
        price = (int) (Math.random() * 150) + 50;
        inventory.addItem(new ItemBuilder(Material.PUFFERFISH).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //COOKED_COD (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.COOKED_COD).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //TROPICAL_FISH (amount 2-3, price 150-200)
        amount = (int) (Math.random() * 2) + 2;
        price = (int) (Math.random() * 51) + 150;
        inventory.addItem(new ItemBuilder(Material.TROPICAL_FISH).amount(amount).addLore(price + " Poudres").build(), 4, price);
        //COD_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.COD_BUCKET).amount(amount).addLore(price + " Poudres").build(), 5, price);
        //SALMON_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.SALMON_BUCKET).amount(amount).addLore(price + " Poudres").build(), 6, price);
        //PUFFERFISH_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.PUFFERFISH_BUCKET).amount(amount).addLore(price + " Poudres").build(), 7, price);
        //TROPICAL_FISH_BUCKET (amount 1-3, price 10-50)
        amount = (int) (Math.random() * 3) + 1;
        price = (int) (Math.random() * 50) + 10;
        inventory.addItem(new ItemBuilder(Material.TROPICAL_FISH_BUCKET).amount(amount).addLore(price + " Poudres").build(), 8, price);
    }

}