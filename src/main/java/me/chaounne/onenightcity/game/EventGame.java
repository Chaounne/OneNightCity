package me.chaounne.onenightcity.game;

import fr.skytasul.guardianbeam.Laser;
import me.chaounne.onenightcity.OneNightCity;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EventGame {

    private static final Random random = new Random();

    private static int endTaskID = -1;

    private static boolean game = false;

    public static void revealPlayerPositions() {
        int randomDelayPeriod = 100; // 30 * 60 * 20 + random.nextInt(15 * 60 * 20); // Entre 40 et 55 minutes
        if (ONCGame.getInstance().hasStarted()) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
                if (ONCGame.getInstance().hasStarted()) {
                    int randomNumber = random.nextInt(3) + 1;
                    if (randomNumber == 1 || randomNumber == 2) {
                        for (Player player : Bukkit.getOnlinePlayers())
                            player.sendMessage(ChatColor.YELLOW + "Rayons planétaires en approche, ils vont arriver d'ici 10 secondes !");

                        Bukkit.getScheduler().scheduleSyncDelayedTask(OneNightCity.getInstance(), () -> {
                            if (ONCGame.getInstance().hasStarted()) {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    if (!player.isDead() && !(player.getGameMode() == GameMode.SPECTATOR)) {
                                        Location locationStart = player.getLocation().clone();
                                        locationStart.setY(-64);
                                        Location locationEnd = player.getLocation().clone();
                                        locationEnd.setY(319);
                                        try {
                                            Laser.CrystalLaser laser = new Laser.CrystalLaser(locationStart, locationEnd, 10, -1);
                                            laser.start(OneNightCity.getInstance());
                                            player.sendMessage(ChatColor.RED + "Les rayons planétaires ont atteint la Terre ! Positions des joueurs révélées pendant 10 secondes !");
                                        } catch (ReflectiveOperationException e) {
                                            player.sendMessage(ChatColor.RED + "Les rayons planétaires ont atteint la Terre ! Positions des joueurs révélées pendant 10 secondes !");
                                            player.sendMessage(ChatColor.RED + "Bizarrement aucun rayon ne vous a atteint !?");
                                        }
                                    }
                                }
                            }
                        }, 10 * 20);
                    }
                }
            }, randomDelayPeriod, randomDelayPeriod);
        }
    }

    public static  void LancerConcours() {
        if (ONCGame.getInstance().hasStarted()) {
            int randomDelayPeriod = 40 * 60 * 20 + random.nextInt(15 * 60 * 20); // Entre 40 et 55 minutes en ticks
            Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
                if (ONCGame.getInstance().hasStarted()) {
                    game=true;
                    RessourceConcour();
                }
            }, randomDelayPeriod, randomDelayPeriod);// Spawn le coffre une fois toutes les 25 minutes

        }
    }

    private static void RessourceConcour() {
        if (endTaskID == -1) { // Vérifie si aucun concours n'est en cours
            ItemStack itemToCollect = generateRandomItem();
            String itemDisplayName = itemToCollect.getType().toString();
            if (itemDisplayName == null) {
                itemDisplayName = itemToCollect.getType().toString();
            }

            // Annonce du début du concours
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (game) {
                    player.sendMessage(ChatColor.GREEN + "Début du concours de collecte, durée 25 minutes ! "+ChatColor.GOLD + "5000 poudres à gagner ! "+ChatColor.GREEN + "L'item à récupérer : " + ChatColor.RED + itemDisplayName);
                    // Afficher le message dans la barre de titre du joueur

                    player.playSound(player.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 10f, 10f);
                }
            }

            // Planification de l'envoi du message de l'action bar toutes les 10 secondes
            String finalItemDisplayName = itemDisplayName;
            int msgTaskId;
            AtomicInteger tempsRestant = new AtomicInteger(25);
            if (game) {

                msgTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
                    if (game) {
                        tempsRestant.getAndDecrement();
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            String message = ChatColor.GOLD + "Concours Collecte, item à farm: " + ChatColor.RED + finalItemDisplayName + ChatColor.GOLD + ", temps restant : " + ChatColor.RED + (tempsRestant.get() > 0 ? tempsRestant + (tempsRestant.get() > 1 ? ChatColor.GOLD + " minutes" : ChatColor.GOLD + " minute") : "fini !");
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                        }
                    }
                }, 60 * 20, 60 * 20); // Envoi toutes les 1 minutes (en ticks)
            } else {
                msgTaskId = -1;
            }

            // Planification de la fin du concours après 25 minutes
            endTaskID = Bukkit.getScheduler().scheduleSyncDelayedTask(OneNightCity.getInstance(), () -> {
                if (msgTaskId != -1) {
                    Bukkit.getScheduler().cancelTask(msgTaskId);
                }
                finConcours(itemToCollect);
                game = false;
            }, 20*60*25); // 25 minutes en ticks (20 ticks * 60 secondes * 25 minutes)
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
            game=false;
            if (winners.size() == 1) {
                for (Player winner : winners) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Le joueur " + winner.getName() + " a remporté le concours avec " + playerResources.get(winner) + " " + itemToCollect.getType() +" ! " +ChatColor.GOLD + " Il gagne 5000 poudres !");
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
                Material.OBSIDIAN,
                Material.REDSTONE,
                Material.LAPIS_BLOCK,
                Material.COBBLESTONE,
                Material.OAK_PLANKS,
                Material.SPRUCE_PLANKS,
                Material.BIRCH_PLANKS,
                Material.JUNGLE_PLANKS,
                Material.ACACIA_PLANKS,
                Material.DARK_OAK_PLANKS,
                Material.SANDSTONE,
                Material.RED_SANDSTONE,
                Material.STONE
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