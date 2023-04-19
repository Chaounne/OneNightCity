package me.chaounne.onenightcity.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class GenerateChest extends JavaPlugin implements Listener {

    private Random random = new Random();

    private Location spawn = new Location(Bukkit.getWorld("world"), 0, 70, 0);
    private int rayon = 10000;

    private ItemStack[] ressources = new ItemStack[]{
            new ItemStack(Material.DIAMOND, 16),
            new ItemStack(Material.GOLD_INGOT, 32),
            new ItemStack(Material.IRON_INGOT, 64),
            new ItemStack(Material.EMERALD, 8),
    };


    @Override
    public void onEnable() {


        Bukkit.getPluginManager().registerEvents(this, this);

        // Lancement du spawn du coffre toutes les 5 minutes
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> spawnCoffre(), 0L, 60L);
    }

    public void spawnCoffre() {

        // Calcul d'une position aléatoire
        double x = spawn.getX() + (random.nextDouble() * rayon * 2) - rayon;
        double z = spawn.getZ() + (random.nextDouble() * rayon * 2) - rayon;
        Location chestLocation = new Location(spawn.getWorld(), x, spawn.getY(), z);

        // Recherche d'un block solide sous la position
        World world = chestLocation.getWorld();
        for (int y = chestLocation.getBlockY(); y > 0; y--) {
            Block block = world.getBlockAt(chestLocation.getBlockX(), y, chestLocation.getBlockZ());
            if (block.getType().isSolid()) {
                chestLocation.setY(y + 1);
                break;
            }
        }

        // Création du coffre et ajout des ressources
        Block chestBlock = world.getBlockAt(chestLocation);
        chestBlock.setType(Material.CHEST);
        Chest chest = (Chest) chestBlock.getState();
        Inventory inventory = chest.getInventory();
        inventory.setContents(ressources);

        // Envoi d'un message à tous les joueurs
        String message = String.format("Un coffre vient d'apparaître à la position : x=%d, y=%d, z=%d",
                chestLocation.getBlockX(), chestLocation.getBlockY(), chestLocation.getBlockZ());
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Vérification si l'inventaire fermé est un coffre et s'il est vide
        Inventory inventory = event.getInventory();
        if (inventory.getType() == InventoryType.CHEST && isChestEmpty(inventory)) {
            // Suppression du coffre
            Block chestBlock = event.getInventory().getLocation().getBlock();
            chestBlock.setType(Material.AIR);
        }
    }

    private boolean isChestEmpty(Inventory inventory) {
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack != null && itemStack.getType() != Material.AIR) {
                return false;
            }
        }
        return true;
    }
}

