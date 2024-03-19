package me.chaounne.onenightcity.inventory;

import fr.mrmicky.fastinv.FastInv;
import me.chaounne.onenightcity.game.GamePlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

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

    public void trade(int amountPoudre, ItemStack given, Player player){
        if(player.getInventory().containsAtLeast(given, given.getAmount())){
            GamePlayer gamePlayer = GamePlayer.getInstance(player);
            //player.sendMessage(given+""+given.getAmount());
            int amountEchange = 0;
            int nbitem = 0;
            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null && item.getType() == given.getType()) {
                    nbitem = nbitem + item.getAmount();
                }
            }

            while (nbitem >= given.getAmount()) {
                player.getInventory().removeItem(given);
                gamePlayer.addScore(amountPoudre);
                gamePlayer.getTeam().addScore(amountPoudre);
                amountEchange = amountEchange + amountPoudre;
                nbitem = nbitem - given.getAmount();
            }

            // Choix aléatoire de l'index du message
            int randomIndex = (int) (Math.random() * messages.length);
            // Choix aléatoire de la couleur
            ChatColor randomColor = colors[(int) (Math.random() * colors.length)];
            // Sélection du message aléatoire
            String randomMessage = String.format(messages[randomIndex], amountEchange);
            // Envoi du message avec la couleur et le message aléatoire
            player.sendMessage(randomColor + randomMessage);
        }

    }

    public void addItem(ItemStack item, int amountPoudre) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null)
            meta.setLore(Collections.singletonList(amountPoudre + " Poudres"));
        item.setItemMeta(meta);

        addItem(item, e -> {
            ItemStack itemStack = new ItemStack(item.getType(), item.getAmount());
            trade(amountPoudre, itemStack, (Player) e.getWhoClicked());
        });
    }

}