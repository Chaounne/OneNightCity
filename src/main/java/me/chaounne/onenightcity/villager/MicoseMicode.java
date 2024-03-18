package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class MicoseMicode extends Trader {

    private static Trader instance;

    public MicoseMicode(Location loc) {
        super(loc, "Micose Micode", Villager.Type.SWAMP, Villager.Profession.FLETCHER, 9);
        instance = this;
    }

    public static void openInventory(Player player) {
        instance.inventory.open(player);
    }

    @Override
    public void addTrades() {
        //MYCELIUM (amount 1-5, price 50-200)
        int amount = (int) (Math.random() * 5) + 1;
        int price = (int) (Math.random() * 200) + 50;
        inventory.addItem(new ItemBuilder(Material.MYCELIUM).amount(amount).addLore(price + " Poudres").build(), 0, price);
        //RED_MUSHROOM_BLOCK (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        inventory.addItem(new ItemBuilder(Material.RED_MUSHROOM_BLOCK).amount(amount).addLore(price + " Poudres").build(), 1, price);
        //BROWN_MUSHROOM_BLOCK (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        inventory.addItem(new ItemBuilder(Material.BROWN_MUSHROOM_BLOCK).amount(amount).addLore(price + " Poudres").build(), 2, price);
        //MUSHROOM_STEM (amount 1-5, price 50-200)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 50;
        inventory.addItem(new ItemBuilder(Material.MUSHROOM_STEM).amount(amount).addLore(price + " Poudres").build(), 3, price);
        //SHROOMLIGHT (amount 1-5, price 20-50)
        amount = (int) (Math.random() * 5) + 1;
        price = (int) (Math.random() * 200) + 20;
        inventory.addItem(new ItemBuilder(Material.SHROOMLIGHT).amount(amount).addLore(price + " Poudres").build(), 4, price);
    }

}
