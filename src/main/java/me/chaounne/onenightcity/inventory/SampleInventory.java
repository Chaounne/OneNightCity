package me.chaounne.onenightcity.inventory;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.utils.Random;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SampleInventory extends FastInv {

    public SampleInventory(int size, String title) {
        super(size, title);
    }

    private final ChatColor[] colors = {
            ChatColor.LIGHT_PURPLE,
            ChatColor.AQUA,
            ChatColor.DARK_PURPLE,
            ChatColor.GOLD,
            ChatColor.RED,
            ChatColor.GREEN,
            ChatColor.BLUE,
            ChatColor.WHITE,
            ChatColor.GRAY,
            ChatColor.DARK_GREEN,
            ChatColor.DARK_RED,
            ChatColor.YELLOW
    };

    private final String[] messages = {
            "Félicitations ! %d poudres magiques pour votre équipe ! ",
            "Gloire ! %d poudres magiques ont été récoltées ! ",
            "Seulement %d poudres ont été ajoutées à votre équipe ! ",
            "Bon y'a mieux mais %d poudre(s) gagnées ! ",
            "Encore toi ? %d poudres encore obtenues ! ",
            "WOW mais quel montant incroyable y'a %d poudre(s) ajoutées à votre équipe ! ",
            "Merveilleux ! %d poudres magiques ont été gagnées ! ",
            "Ronpich Zzzz ! %d poudres, c'est tout ?! ",
            "Espèce de rat, t'as que %d poudre(s) en échange pour la peine ! ",
            "Oula, c'est peu quand meme : %d poudre(s) ajoutées ! "
    };

    public void trade(int price, ItemStack item, Player player) {
        if (player.getInventory().contains(item.getType(), item.getAmount())) {
            GamePlayer gamePlayer = GamePlayer.getInstance(player);

            int powderGained = 0;
            int invItemAmount = 0;

            for (ItemStack slot : player.getInventory().getContents()) {
                if (slot != null && slot.getType() == item.getType())
                    invItemAmount += slot.getAmount();
            }

            while (invItemAmount >= item.getAmount()) {
                player.getInventory().removeItem(item);
                invItemAmount -= item.getAmount();
                gamePlayer.addScore(price);
                gamePlayer.getTeam().addScore(price);
                powderGained += price;
            }

            ChatColor randomColor = colors[Random.between(0, colors.length - 1)];
            String randomMessage = String.format(messages[Random.between(0, messages.length - 1)], powderGained);
            player.sendMessage(randomColor + randomMessage);
        }
    }

    public void addItem(Material material, int amount, int price) {
        ItemStack tradeItem = new ItemBuilder(material).amount(amount).addLore(price + " Poudres").build();
        addItem(tradeItem, e -> {
            ItemStack item = new ItemStack(tradeItem.getType(), tradeItem.getAmount());
            trade(price, item, (Player) e.getWhoClicked());
        });
    }

}