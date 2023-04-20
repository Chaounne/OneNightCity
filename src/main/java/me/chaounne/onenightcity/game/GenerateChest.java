package me.chaounne.onenightcity.game;

import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class GenerateChest implements Listener {

    private static Random random = new Random();

    private static Location spawn = new Location(Bukkit.getWorld("world"), 0, 200, 0);
    private static int rayon = 5000;

    private static ItemStack[] ressources = new ItemStack[]{
            new ItemStack(Material.DIAMOND, 16),
            new ItemStack(Material.GOLD_INGOT, 32),
            new ItemStack(Material.IRON_INGOT, 64),
            new ItemStack(Material.EMERALD, 8),
    };



    public static void spawnCoffre() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
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
            String message = String.format("&6Un coffre vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d",
                    chestLocation.getBlockX(), chestLocation.getBlockY(), chestLocation.getBlockZ());
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }


        }, 0L, 1200L);
    }






}

