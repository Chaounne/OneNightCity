package me.chaounne.onenightcity.game;

import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class QuineItem {
    private static int time = 0;

    private static
    ItemStack[] ressources = new ItemStack[]{
            new ItemStack(Material.DIAMOND),
            new ItemStack(Material.GOLD_INGOT),
            new ItemStack(Material.IRON_INGOT),
            new ItemStack(Material.EMERALD),
            new ItemStack(Material.REDSTONE),
            new ItemStack(Material.QUARTZ)
    };

    private static ItemStack[] itemsToFind = new ItemStack[3];
    private static boolean gameEnded = false;
    public static void start() {
        Random random = new Random();

        // Choix aléatoire des 3 items à trouver
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(ressources.length);
            itemsToFind[i] = ressources[index];
        }

        // Affichage des 3 items à trouver aux joueurs
        String message = "Les 3 items à trouver sont : ";
        for (ItemStack item : itemsToFind) {
            message += item.getAmount() + " " + item.getType().toString() + ", ";
        }
        message = message.substring(0, message.length() - 2); // suppression de la dernière virgule et espace
        for (
                Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }

        // Vérification de la proximité des joueurs avec le spawn
        Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
            if (time ==50) {
                gameEnded = true;
                Bukkit.broadcastMessage(ChatColor.RED + "La quine est terminée !");
                time =51;
                return;
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getLocation().distance(player.getWorld().getSpawnLocation()) < 50) {
                    // Vérification de la présence des 3 items requis dans l'inventaire du joueur
                    boolean hasAllItems = true;
                    for (ItemStack item : itemsToFind) {
                        if (!player.getInventory().containsAtLeast(item, item.getAmount())) {
                            hasAllItems = false;
                            break;
                        }
                    }
                    if (hasAllItems && !gameEnded) {
                        player.sendMessage("Vous avez réussi à completer la Quine d'item, vous gagnez 1000 points!");
                        for(Player playerE: Bukkit.getOnlinePlayers()){
                            playerE.sendMessage("Le joueur : "+player.getName()+" à réussi la QUINE");
                        }
                        gameEnded = true;
                        GamePlayer player1 = GamePlayer.getInstance(player);
                        player1.getTeam().addScore(1000);
                        player1.addScore(1000);
                        player.getInventory().removeItem(ressources);

                    }
                }
            }
            time += 1;
        }, 0L, 20L);
    }
}
