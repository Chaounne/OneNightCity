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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EventGame {
    private static Random random = new Random();

    // Méthode pour révéler la position des joueurs dans 10 secondes avec des feux d'artifice
    public static void revealPlayerPositions() {
        int randomDelayPeriod = 15000 + random.nextInt(20000); // Génère un nombre aléatoire entre 12.5 min et environ 27 min

        Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {
            int randomNumber = random.nextInt(3) + 1; // Génère un nombre aléatoire entre 1 et 3
            Bukkit.broadcastMessage(ChatColor.YELLOW + "Random Number: " + randomNumber);

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


}
