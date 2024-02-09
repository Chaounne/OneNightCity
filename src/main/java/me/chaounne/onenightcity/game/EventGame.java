package me.chaounne.onenightcity.game;

import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class EventGame {
    private static Random random = new Random();

    // Méthode pour révéler la position des joueurs dans 10 secondes avec des feux d'artifice
    public static void revealPlayerPositions() {
        int randomDelayPeriod = 1500 + random.nextInt(2000); // Génère un nombre aléatoire entre 12.5 min et environ 27 min

        Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
            int randomNumber = random.nextInt(3) + 1; // Génère un nombre aléatoire entre 1 et 3

            if (randomNumber == 1 || randomNumber == 2) {

                Bukkit.broadcastMessage(ChatColor.YELLOW + "Position révélée dans 10 secondes !");
                Bukkit.getScheduler().scheduleSyncDelayedTask(OneNightCity.getInstance(), () -> {
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "Feu d'artifice ! Position des joueurs révélée !");
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        Location fireworkLocation = player.getLocation();
                        Firework firework = player.getWorld().spawn(fireworkLocation, Firework.class);
                        FireworkMeta fireworkMeta = firework.getFireworkMeta();

                        // Création de l'effet de feu d'artifice
                        FireworkEffect effect = FireworkEffect.builder()
                                .flicker(true)
                                .trail(true)
                                .with(FireworkEffect.Type.BURST)
                                .with(FireworkEffect.Type.BALL_LARGE)
                                .withColor(Color.RED)
                                .withFade(Color.ORANGE)
                                .build();


                        fireworkMeta.addEffect(effect);
                        fireworkMeta.setPower(2);
                        firework.setFireworkMeta(fireworkMeta);
                    }

                }, 200L); // 20 ticks * 10 seconde
            }// s
        }, randomDelayPeriod, randomDelayPeriod); // 20 ticks * 30 secondes
    }
    // Ajoutez cette variable pour stocker l'identifiant de la tâche planifiée pour la fin du concours
    private static int endTaskID = -1;

    public static  void LancerConcours(){
        int randomDelayPeriod =2 * 60 * 20;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
            RessourceConcour();
        }, randomDelayPeriod, randomDelayPeriod);// Spawn le coffre une fois toutes les 25 minutes

        }
    private static void RessourceConcour() {
        if (endTaskID == -1) { // Vérifie si aucun concours n'est en cours
             ItemStack itemToCollect = generateRandomItem();
           // Annonce du début du concours
            Bukkit.broadcastMessage(ChatColor.YELLOW + "Début du concours de collecte, durée 10 minutes ! 5000 poudres à gagner ! L'item à récupérer : " + itemToCollect.getType().toString());
            for (Player player : Bukkit.getOnlinePlayers()) {

                for (Player player1 : Bukkit.getOnlinePlayers()) {
                    player1.playSound(player.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 10f, 10f);

                }
            }
            // Planification de la fin du concours après 2 minutes
            endTaskID = Bukkit.getScheduler().scheduleSyncDelayedTask(OneNightCity.getInstance(), () -> {
                finConcours(itemToCollect);
            }, 1000); // environ 55s 2 minutes en ticks (20 ticks * 60 secondes * 2 minutes)
        }
    }

    private static void finConcours(ItemStack itemToCollect) {
        Map<Player, Integer> playerResources = new HashMap<>();

        // Vérification du contenu de l'inventaire de chaque joueur
        for (Player player : Bukkit.getOnlinePlayers()) {
            int itemCount = countItems(player.getInventory(), itemToCollect);
            playerResources.put(player, itemCount);
        }
        System.out.println(itemToCollect);
        // Calcul des joueurs avec le plus grand nombre d'items collectés
        List<Player> winners = calculateWinner(playerResources);

        // Annonce des gagnants
        if (!winners.isEmpty()) {
            if (winners.size() == 1) {
                for (Player winner : winners) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Le joueur " + winner.getName() + " a remporté le concours avec " + playerResources.get(winner) + " " + itemToCollect.getType().toString() + "! Il gagne 5000 poudres !");
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 10f, 10f);
                    }
                    GamePlayer gamePlayer = GamePlayer.getInstance(winner);
                    gamePlayer.getTeam().addScore(5000);
                    gamePlayer.addScore(5000);
                }
            } else {
                Bukkit.broadcastMessage(ChatColor.GREEN + "Les joueurs suivants ont remporté le concours avec le même nombre d'objets (" + playerResources.get(winners.get(0)) + ") : ");
                for (Player winner : winners) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + "- " + winner.getName());
                }
                Bukkit.broadcastMessage(ChatColor.GREEN + "Chacun gagne 5000 poudres !");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(player.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 10f, 10f);
                }
                for (Player winner : winners) {
                    GamePlayer gamePlayer = GamePlayer.getInstance(winner);
                    gamePlayer.getTeam().addScore(5000);
                    gamePlayer.addScore(5000);
                }
            }
        } else {
            Bukkit.broadcastMessage(ChatColor.YELLOW + "Le concours s'est terminé, mais personne n'a collecté l'item.");
        }

        // Réinitialisation de la variable endTaskID pour indiquer qu'aucun concours n'est en cours
        endTaskID = -1;
    }
    // Méthode pour compter le nombre d'items spécifiques dans l'inventaire d'un joueur
    private static int countItems(Inventory inventory, ItemStack itemToCount) {
        int count = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.isSimilar(itemToCount)) {
                count += item.getAmount();
            }
        }
        return count;
    }

    // Méthode pour générer un item aléatoire à collecter
    private static ItemStack generateRandomItem() {
        // Génération de l'item aléatoire ici, vous pouvez personnaliser selon vos besoins
        Material[] blocks = {
                Material.CRAFTING_TABLE,
                Material.WHEAT_SEEDS,
                Material.COOKED_MUTTON,
                Material.FLINT,
                Material.OAK_WOOD,
                Material.SAND,
                Material.GRAVEL,
                Material.COAL_BLOCK,
                Material.IRON_BLOCK,
                Material.GOLD_BLOCK,
                Material.DIAMOND_BLOCK,
                Material.BONE,
                Material.NETHER_BRICKS,
                Material.BAMBOO,
                Material.CLAY,
                Material.GLOWSTONE,
                Material.GLOWSTONE_DUST,
                Material.SPIDER_EYE,
                Material.PAINTING,
                Material.OBSIDIAN
        };

        // Sélection aléatoire d'un bloc dans la liste
        Material randomBlock = blocks[new Random().nextInt(blocks.length)];

        // Création de l'item avec le bloc choisi
        ItemStack item = new ItemStack(randomBlock);

        // Personnalisation de l'item
        ItemMeta meta = item.getItemMeta();
        item.setItemMeta(meta);
        System.out.println(item);
        return item;
    }

    // Méthode pour calculer le joueur avec le plus grand nombre d'items collectés
    private static List<Player> calculateWinner(Map<Player, Integer> playerResources) {
        List<Player> winners = new ArrayList<>();
        int maxResources = 0;

        // Trouver le nombre maximal d'objets collectés
        for (Map.Entry<Player, Integer> entry : playerResources.entrySet()) {
            int resources = entry.getValue();
            if (resources > maxResources) {
                maxResources = resources;
            }
        }

        // Ajouter tous les joueurs qui ont collecté le nombre maximal d'objets
        for (Map.Entry<Player, Integer> entry : playerResources.entrySet()) {
            if (entry.getValue() == maxResources && maxResources > 0) {
                winners.add(entry.getKey());
            }
        }

        return winners;
    }



    }
