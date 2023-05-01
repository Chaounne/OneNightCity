package me.chaounne.onenightcity.game;

import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuineItem {
    private static int time = 0;

    private static
    ItemStack[] ressources = new ItemStack[]{
            new ItemStack(Material.CAKE),
            new ItemStack(Material.JUKEBOX),
            new ItemStack(Material.PUFFERFISH_BUCKET),
            new ItemStack(Material.COOKIE),
            new ItemStack(Material.CLOCK),
            new ItemStack(Material.EMERALD),
            new ItemStack(Material.FERMENTED_SPIDER_EYE),
            new ItemStack(Material.CARROT_ON_A_STICK),
            new ItemStack(Material.INK_SAC),
            new ItemStack(Material.MILK_BUCKET),
            new ItemStack(Material.RABBIT_HIDE),
            new ItemStack(Material.BLAZE_ROD),
            new ItemStack(Material.BRICK_STAIRS),
            new ItemStack(Material.MAGMA_CREAM),
            new ItemStack(Material.NETHER_WART),
            new ItemStack(Material.ARMOR_STAND),
            new ItemStack(Material.AMETHYST_BLOCK),



    };

    private static ItemStack[] itemsToFind = new ItemStack[3];
    private static boolean gameEnded = false;
    public static void start() {
        Random random = new Random();
        List<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < ressources.length; i++) {
            indices.add(i);
        }

        // Choix aléatoire des 3 items à trouver
        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(indices.size());
            int itemIndex = indices.get(index);
            itemsToFind[i] = ressources[itemIndex];
            indices.remove(index);
        }

        // Affichage des 3 items à trouver aux joueurs
        String message = "Les 3 items à trouver sont : ";
        for (ItemStack item : itemsToFind) {
            message += item.getAmount() + " " + item.getType().toString() + ", ";
        }
        message = message.substring(0, message.length() - 2); // suppression de la dernière virgule et espace
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
        // Vérification de la proximité des joueurs avec le spawn
        Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
            if (time ==50||gameEnded) {
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
