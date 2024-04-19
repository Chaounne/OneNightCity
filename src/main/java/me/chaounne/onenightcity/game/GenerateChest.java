package me.chaounne.onenightcity.game;

import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class GenerateChest {

    private static final Random random = new Random();

    private static final Location spawn = new Location(Bukkit.getWorld("world"), 0, 200, 0);

    private static final int rayon = 1000;

    private static ItemStack[] ressources;

    private static String message;

    public static void spawnEndChest(Location location) {
        if (ONCGame.getInstance().hasStarted()) {
            ItemStack[] ressources = new ItemStack[]{
                    new ItemStack(Material.DIAMOND, random.nextInt(5) + 1),
                    new ItemStack(Material.GOLD_INGOT, random.nextInt(5) + 1),
                    new ItemStack(Material.IRON_INGOT, random.nextInt(5) + 1),
                    new ItemStack(Material.EMERALD, random.nextInt(5) + 1),
                    PoudreItem.getItem(random.nextInt(50) + 10),
                    new ItemStack(Material.GOLDEN_APPLE, random.nextInt(5) + 1),
                    new ItemStack(Material.ENDER_PEARL, random.nextInt(10) + 1)
            };
            Block block = location.getBlock();
            block.setType(Material.CHEST);
            Chest chest = (Chest) block.getState();
            Inventory inventory = chest.getInventory();
            inventory.setContents(ressources);
        }
    }

    public static void spawnCoffre() {
        if  (ONCGame.getInstance().hasStarted()) {

            int randomDelayPeriod = 30 * 60 * 20 + random.nextInt(15 * 60 * 20); // Entre 40 et 55 minutes en ticks
            // int randomDelayPeriod = 150 + random.nextInt(200); // Génère un nombre aléatoire entre 1 minute (60000 ms) et 2 minutes (120000 ms)

            Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
                int nombre = random.nextInt(10) + 1;

                if (nombre == 2 ) {

                    ressources = new ItemStack[]{
                            new ItemStack(Material.DIAMOND, random.nextInt(40) + 10),
                            new ItemStack(Material.GOLD_INGOT, random.nextInt(40) + 10),
                            new ItemStack(Material.IRON_INGOT, random.nextInt(40) + 10),
                            new ItemStack(Material.EMERALD, random.nextInt(40) + 10),
                            PoudreItem.getSuperPoudre(random.nextInt(2) + 1),
                    };
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.playSound(player.getLocation(), Sound.BLOCK_CONDUIT_AMBIENT, 100f, 100f);
                    }
                    message = " &r&6&lUn &6&l&e&e&l&dS&e&e&lU&dP&l&6&lE&r&6&l&e&e&lR &r&6&lCoffre vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d&e&o!";
                } else if (nombre == 4) {
                    ressources = new ItemStack[]{
                            new ItemStack(Material.DIAMOND_HOE, 1),
                            new ItemStack(Material.IRON_SHOVEL, 1),
                            new ItemStack(Material.WHEAT_SEEDS, random.nextInt(64) + 16),
                            new ItemStack(Material.CARROT, random.nextInt(64) + 16),
                            new ItemStack(Material.POTATO, random.nextInt(64) + 16),
                            new ItemStack(Material.BEETROOT_SEEDS, random.nextInt(64) + 16),
                            new ItemStack(Material.MELON_SEEDS, random.nextInt(32) + 8),
                            new ItemStack(Material.PUMPKIN_SEEDS, random.nextInt(32) + 8),
                            new ItemStack(Material.BONE_MEAL, random.nextInt(32) + 8),
                    };
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 10f, 10f);
                    }

                    message = "&6Un coffre &c&lFERMIER &6vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d&e&o";
                } else if (nombre == 5) {
                    ressources = new ItemStack[]{
                            new ItemStack(Material.DIAMOND_PICKAXE, 1),
                            new ItemStack(Material.IRON_PICKAXE, 1),
                            new ItemStack(Material.COAL, random.nextInt(32) + 16),
                            new ItemStack(Material.IRON_ORE, random.nextInt(16) + 8),
                            new ItemStack(Material.GOLD_ORE, random.nextInt(8) + 4),
                            new ItemStack(Material.REDSTONE, random.nextInt(16) + 8),
                            new ItemStack(Material.LAPIS_LAZULI, random.nextInt(16) + 8),
                            new ItemStack(Material.DIAMOND, random.nextInt(4) + 1),
                            new ItemStack(Material.EMERALD, random.nextInt(4) + 1),
                    };
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 10f, 10f);
                    }

                    message = "&6Un coffre &c&fMINEUR &6vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d&e&o";
                }else if (nombre == 3) {

                    ressources = new ItemStack[]{
                        new ItemStack(Material.GOLDEN_CARROT, random.nextInt(4) + 1), // 3 golden carrots
                        new ItemStack(Material.FERMENTED_SPIDER_EYE, random.nextInt(4) + 1), // 3 fermented spider eyes
                        new ItemStack(Material.BLAZE_POWDER, random.nextInt(4) + 1), // 2 blaze powders
                        new ItemStack(Material.BREWING_STAND, random.nextInt(4) + 1), // Brewing Stand
                        new ItemStack(Material.MAGMA_CREAM, random.nextInt(4) + 1), // 6 magma creams
                        new ItemStack(Material.NETHERITE_PICKAXE, random.nextInt(1) + 1), // Netherite Pickaxe
                        new ItemStack(Material.GHAST_TEAR, random.nextInt(4) + 1), // 2 ghast tears
                        new ItemStack(Material.DIAMOND, random.nextInt(4) + 1), // Diamonds
                        new ItemStack(Material.NETHER_WART, random.nextInt(4) + 1), // Nether Wart
                        new ItemStack(Material.NETHER_BRICK, random.nextInt(4) + 1), // 16 Nether bricks
                        new ItemStack(Material.SOUL_SAND, random.nextInt(4) + 1), // Soul Sand
                    };


                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.playSound(player.getLocation(), Sound.BLOCK_LAVA_AMBIENT, 10f, 10f);
                    }
                    message = "&6Un coffre &c&lPOTION&r&6 vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d";

                } else if (nombre == 7) {
                    ressources = new ItemStack[]{
                            new ItemStack(Material.DIAMOND_CHESTPLATE, random.nextInt(3) + 1),
                            new ItemStack(Material.DIAMOND_BOOTS, random.nextInt(4) + 1),
                            new ItemStack(Material.CROSSBOW, 1),
                            new ItemStack(Material.ARROW, random.nextInt(64) + 16),
                            new ItemStack(Material.ENDER_PEARL, random.nextInt(4) + 1),
                            new ItemStack(Material.GOLDEN_APPLE, random.nextInt(3) + 1),
                            new ItemStack(Material.DIAMOND, random.nextInt(4) + 1),
                            new ItemStack(Material.EXPERIENCE_BOTTLE, random.nextInt(15) + 5),
                            new ItemStack(Material.BOW, 1),
                    };
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.playSound(player.getLocation(), Sound.BLOCK_BEACON_AMBIENT, 10f, 10f);
                    }
                    message = "&6Un coffre &c&lEQUIPEMENT &6vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d&e&o";


                    //idée de coffre suplémentaire :  coffre ?/.?§.?%^^?. (un coffre ultra bizarre faut voir ce qu'on en fait), Un coffre piège qui retire des poudres à celui qui l'ouvre ? et lui envoie un message (vous vous etes fait avoir par un faux coffre, vous perdez X poudres, Un coffre ou faut etre plusieurs pour l'ouvrir

                } else {
                    ressources = new ItemStack[]{
                            new ItemStack(Material.DIAMOND, random.nextInt(15) + 2),
                            new ItemStack(Material.GOLD_INGOT, random.nextInt(20) + 10),
                            new ItemStack(Material.IRON_INGOT, random.nextInt(40) + 20),
                            new ItemStack(Material.EMERALD, random.nextInt(16) + 8),
                            PoudreItem.getItem(random.nextInt(60) + 40),
                            PoudreItem.getItem(random.nextInt(60) + 40),
                            PoudreItem.getItem(random.nextInt(60) + 40),

                    };
                    message = "&6Un  coffre vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d";

                }

                if (ONCGame.getInstance().hasStarted()) {
                    // Calcul d'une position aléatoire
                    double x, z;
                    do {
                        x = spawn.getX() + (random.nextDouble() * rayon * 2) - rayon;
                        z = spawn.getZ() + (random.nextDouble() * rayon * 2) - rayon;
                    } while ((x < 200 && x > 50) || (z > -100 && z < 100)); // Ajout de la condition

                    Location chestLocation = new Location(spawn.getWorld(), x, spawn.getY(), z);

                    // Recherche d'un block solide sous la position
                    World world = chestLocation.getWorld();
                    Block block;
                    int y = chestLocation.getBlockY();
                    do {
                        block = world.getBlockAt(chestLocation.getBlockX(), y, chestLocation.getBlockZ());
                        if (block.getType().isSolid()) {
                            chestLocation.setY(y + 1);
                            break;
                        }
                        y--;
                    } while (y > 0);

                    // Création du coffre et ajout des ressources
                    Block chestBlock = world.getBlockAt(chestLocation);
                    chestBlock.setType(Material.CHEST);
                    Chest chest = (Chest) chestBlock.getState();
                    Inventory inventory = chest.getInventory();
                    inventory.setContents(ressources);

                    // Envoi d'un message à tous les joueurs
                    String messageToSend = String.format(message, chestLocation.getBlockX(), chestLocation.getBlockY(), chestLocation.getBlockZ());
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', messageToSend));
                    }
                }
            }, randomDelayPeriod, randomDelayPeriod);// Spawn le coffre une fois toutes les 25 minutes

        }
    }

}