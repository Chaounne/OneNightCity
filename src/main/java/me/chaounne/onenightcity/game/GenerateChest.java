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
                    new ItemStack(Material.DIAMOND, random.nextInt(21) + 30),
                    new ItemStack(Material.GOLD_INGOT, random.nextInt(21) + 30),
                    new ItemStack(Material.IRON_INGOT, random.nextInt(21) + 30),
                    new ItemStack(Material.EMERALD, random.nextInt(21) + 30),
                    PoudreItem.getSuperPoudre(random.nextInt(11) + 10),
};
            for(Player player:Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(),Sound.BLOCK_CONDUIT_AMBIENT, 10f,10f);
            }
            message = " &r&6&lUn &6&l&e&e&l&dS&e&e&lU&dP&l&6&lE&r&6&l&e&e&lR &r&6&lCoffre vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d&e&o!";
        } else if(nombre==3) {
            ressources = new ItemStack[]{
                    new ItemStack(Material.GOLDEN_CARROT, random.nextInt(4)+1), // 3 carottes dorées
                    new ItemStack(Material.FERMENTED_SPIDER_EYE, random.nextInt(4)+1), // 3 yeux d'araignée fermentés
                    new ItemStack(Material.BLAZE_POWDER, random.nextInt(4)+1), // 2 poudres de Blaze
                    new ItemStack(Material.BREWING_STAND, random.nextInt(4)+1),
                    new ItemStack(Material.GHAST_TEAR, random.nextInt(4)+1),// 2 larmes de Ghast
                    new ItemStack(Material.DIAMOND, random.nextInt(4)+1),
                    new ItemStack(Material.NETHER_WART,random.nextInt(4)+1),
            };

            for(Player player:Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(),Sound.BLOCK_LAVA_AMBIENT, 10f,10f);
            }
            message = "&6Un coffre &c&lPOTION&r&6 vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d";
        }else if (nombre ==7){
            ressources = new ItemStack[]{
                    new ItemStack(Material.DIAMOND_CHESTPLATE, random.nextInt(3)+1),
                    new ItemStack(Material.DIAMOND_BOOTS, random.nextInt(4)+1),
                    new ItemStack(Material.CROSSBOW, 1),
                    new ItemStack(Material.DIAMOND, random.nextInt(4)+1),
                    new ItemStack(Material.EXPERIENCE_BOTTLE, random.nextInt(15)+5)
        };
            for(Player player:Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(),Sound.BLOCK_BEACON_AMBIENT, 10f,10f);
            }
            message = "&6Un coffre &c&lEQUIPEMENT &6vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d&e&o";
        }else{
            ressources = new ItemStack[]{
                    new ItemStack(Material.DIAMOND, random.nextInt(15)+9),
                    new ItemStack(Material.GOLD_INGOT, random.nextInt(20)+15),
                    new ItemStack(Material.IRON_INGOT, random.nextInt(40)+20),
                    new ItemStack(Material.EMERALD, random.nextInt(16)+8),
                    PoudreItem.getItem(random.nextInt(60)+40),
                    PoudreItem.getItem(random.nextInt(60)+40),
                    PoudreItem.getItem(random.nextInt(60)+40),

            };
            message = "&6Un  coffre vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d";

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
            }, 360, 360);

    }






}

