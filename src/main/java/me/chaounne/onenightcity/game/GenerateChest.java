package me.chaounne.onenightcity.game;

import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class GenerateChest implements Listener {

    private static Random random = new Random();

    private static Location spawn = new Location(Bukkit.getWorld("world"), 0, 200, 0);
    private static boolean Super = false;
    private static int rayon = 2000;

    private static ItemStack[] ressources;
    private static String message;

    public static void spawnCoffre() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {

            int nombre = random.nextInt(10) + 1;
        if ( nombre == 2) {
            Super = true;
            ressources = new ItemStack[]{
                    new ItemStack(Material.DIAMOND, 32),
                    new ItemStack(Material.GOLD_INGOT, 44),
                    new ItemStack(Material.IRON_INGOT, 64),
                    new ItemStack(Material.EMERALD, 16),
                    PoudreItem.getSuperPoudre(15)
            };
            for(Player player:Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(),Sound.BLOCK_CONDUIT_AMBIENT, 10f,10f);
            }
            message = ""+nombre+ " &r&6&lUn &6&lS&e&e&lU&dp&l&6&lE&r&6&lR &r&6&lCoffre vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d&e&o!";
        } else if(nombre==3) {
            ressources = new ItemStack[]{
                    new ItemStack(Material.GOLDEN_CARROT, 3), // 3 carottes dorées
                    new ItemStack(Material.FERMENTED_SPIDER_EYE, 3), // 3 yeux d'araignée fermentés
                    new ItemStack(Material.BLAZE_POWDER, 2), // 2 poudres de Blaze
                    new ItemStack(Material.BREWING_STAND, 1),
                    new ItemStack(Material.GHAST_TEAR, 2) // 2 larmes de Ghast
            };

            message = ""+nombre+"&6Un coffre &c&lPOTION&r&6 vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d";
        }else if (nombre ==7||nombre==10){
            ressources = new ItemStack[]{
                    new ItemStack(Material.DIAMOND_CHESTPLATE, 2),
                    new ItemStack(Material.DIAMOND_BOOTS, 2),
                    new ItemStack(Material.CROSSBOW, 1),

                    new ItemStack(Material.EXPERIENCE_BOTTLE, 10)
};
            message = ""+nombre+"&6Un coffre &c&lEQUIPEMENT &6vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d&e&o";
        }else{
            ressources = new ItemStack[]{
                    new ItemStack(Material.DIAMOND, 10),
                    new ItemStack(Material.GOLD_INGOT, 20),
                    new ItemStack(Material.IRON_INGOT, 30),
                    new ItemStack(Material.EMERALD, 4),
                    PoudreItem.getItem(250)
            };
            message = ""+nombre+ "&6Un  coffre vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d";

        }

         if (ONCGame.getInstance().isStarted()) {
                // Calcul d'une position aléatoire
                double x, z;
                do {
                    x = spawn.getX() + (random.nextDouble() * rayon * 2) - rayon;
                    z = spawn.getZ() + (random.nextDouble() * rayon * 2) - rayon;
                } while (x < 100 || z < 100); // Ajout de la condition

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
            }, 0L, 36);

    }






}

