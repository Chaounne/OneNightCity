package me.chaounne.onenightcity.inventory;

import me.chaounne.fastinv.FastInv;
import me.chaounne.onenightcity.game.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SampleInventory extends FastInv {

    public SampleInventory(int size, String title) {
        super(size, title);
    }

    public void trade(int amountPoudre, ItemStack given, Player player){
        if(player.getInventory().containsAtLeast(given, given.getAmount())){
            GamePlayer gamePlayer = GamePlayer.getInstance(player);
            player.getInventory().removeItem(given);
            gamePlayer.addScore(amountPoudre);
            System.out.println(gamePlayer.getScore());
        }

    }

    public void addItem(ItemStack item, int slot, int amountPoudre){
        setItem(slot, item, e -> {
            ItemStack itemStack = new ItemStack(item.getType());
            trade(amountPoudre, itemStack, (Player) e.getWhoClicked());
        });
    }
}
