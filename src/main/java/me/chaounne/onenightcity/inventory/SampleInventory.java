package me.chaounne.onenightcity.inventory;

import me.chaounne.fastinv.FastInv;
import me.chaounne.onenightcity.game.GamePlayer;
import org.bukkit.ChatColor;
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
            "Félicitations ! %d poudre(s) magiques pour votre équipe ! ",
            "Gloire ! %d poudre(s) magiques ont été récoltées ! ",
            "Seulement %d poudre(s) ont été ajouté à votre équipe ! ",
            "Bon y'a mieux mais %d poudre(s) gagnées ! ",
            "Encore toi ? %d poudre(s) encore obtenues ! ",
            "WOW mais quel montant incroyable y'a %d poudre(s) ajoutées à votre équipe ! ",
            "Merveilleux ! %d poudre(s) magiques ont été gagnées ! ",
            "Ronpich Zzzz ! %d poudre(s), c'est tout ?! ",
            "Espece de rat, t'as que %d poudre(s) en echange pour la peine ! ",
            "Oula, c'est peu quand meme : %d poudre(s) ajoutées ! "
    };
    public void trade(int amountPoudre, ItemStack given, Player player) {
        if (player.getInventory().containsAtLeast(given, given.getAmount())) {
            GamePlayer gamePlayer = GamePlayer.getInstance(player);
            player.getInventory().removeItem(given);

            gamePlayer.addScore(amountPoudre);
            gamePlayer.getTeam().addScore(amountPoudre);
            System.out.println(gamePlayer.getScore());

            // Choix aléatoire de l'index du message
            int randomIndex = (int) (Math.random() * messages.length);
            // Choix aléatoire de la couleur
            ChatColor randomColor = colors[(int) (Math.random() * colors.length)];
            // Sélection du message aléatoire
            String randomMessage = String.format(messages[randomIndex], amountPoudre);

            // Envoi du message avec la couleur et le message aléatoire
            player.sendMessage(randomColor + randomMessage);

        }
    }
    public void addItem(ItemStack item, int slot, int amountPoudre){
        setItem(slot, item, e -> {
            // Créez une copie de l'ItemStack avec la quantité spécifiée
            ItemStack itemStack = new ItemStack(item.getType(), item.getAmount());
            // Passez l'ItemStack correct à la méthode trade
            trade(amountPoudre, itemStack, (Player) e.getWhoClicked());
        });
    }

}
