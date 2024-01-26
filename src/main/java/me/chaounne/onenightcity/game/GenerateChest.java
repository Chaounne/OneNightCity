package me.chaounne.onenightcity.game;

import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class GenerateChest implements Listener {

    private static Random random = new Random();

    private static Location spawn = new Location(Bukkit.getWorld("world"), 0, 200, 0);
    private static boolean Super = false;
    private static int rayon = 800;

    private static ItemStack[] ressources;
    private static String message;
    private static ItemStack getInvisibilityPotion(int amount) {
        ItemStack potion = new ItemStack(Material.POTION, amount);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 5400, 0), true);
        meta.setDisplayName("Potion d'invisibilité");
        meta.setColor(Color.fromRGB(200, 100, 200));
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        potion.setItemMeta(meta);
        return potion;
    }
    private static ItemStack getStrengthPotion(int amount) {
        ItemStack potion = new ItemStack(Material.POTION, amount);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3400, 0), true);
        meta.setDisplayName("Potion de force");
        meta.setColor(Color.RED);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        potion.setItemMeta(meta);
        return potion;
    }
    private static ItemStack creerLivreEnchanteSharpness(int amount) {
        // Crée un nouvel item "Livre"
        ItemStack livre = new ItemStack(Material.ENCHANTED_BOOK, amount);

        // Obtient l'objet "Enchantment" pour l'enchantement "Protection"
        Enchantment dmg = Enchantment.DAMAGE_ALL;

        // Applique l'enchantement "Protection" de niveau 1 au livre
        livre.addEnchantment(dmg, 1);

        // Définit le nom personnalisé du livre
        ItemMeta meta = livre.getItemMeta();
        meta.setDisplayName("Livre enchanté de Sharpness");
        livre.setItemMeta(meta);
        return livre;
    }
    public static void spawnCoffre() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(), () -> {

            int nombre = random.nextInt(10) + 1;
        if ( nombre == 2) {
            Super = true;
            ressources = new ItemStack[]{
                    new ItemStack(Material.DIAMOND, random.nextInt(40) + 10),
                    new ItemStack(Material.GOLD_INGOT, random.nextInt(40) + 10),
                    new ItemStack(Material.IRON_INGOT, random.nextInt(40) + 10),
                    new ItemStack(Material.EMERALD, random.nextInt(40) + 10),
                    PoudreItem.getSuperPoudre(random.nextInt(2) + 1),
};
            for(Player player:Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(),Sound.BLOCK_CONDUIT_AMBIENT, 100f,100f);
            }
            message = " &r&6&lUn &6&l&e&e&l&dS&e&e&lU&dP&l&6&lE&r&6&l&e&e&lR &r&6&lCoffre vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d&e&o!";
        } else if(nombre==3) {
            ressources = new ItemStack[]{
                    new ItemStack(Material.GOLDEN_CARROT, random.nextInt(4)+1), // 3 carottes dorées
                    new ItemStack(Material.FERMENTED_SPIDER_EYE, random.nextInt(4)+1), // 3 yeux d'araignée fermentés
                    new ItemStack(Material.BLAZE_POWDER, random.nextInt(4)+1), // 2 poudres de Blaze
                    new ItemStack(Material.BREWING_STAND, random.nextInt(4)+1),
                    new ItemStack(Material.MAGMA_CREAM, random.nextInt(4)+1), // Crée un objet ItemStack contenant 6 crèmes de magma.
                    new ItemStack(Material.NETHERITE_PICKAXE, random.nextInt(1)+0), // Crée un objet ItemStack contenant une pioche en netherite.
                    new ItemStack(Material.POTION, 1, (short) 16428), // Potion de résistance au feu
                    new ItemStack(Material.GHAST_TEAR, random.nextInt(4)+1),// 2 larmes de Ghast
                    new ItemStack(Material.DIAMOND, random.nextInt(4)+1),
                    new ItemStack(Material.NETHER_WART,random.nextInt(4)+1),
                    new ItemStack(Material.NETHER_BRICK, random.nextInt(4)+1), // Crée un objet ItemStack contenant 16 briques du Nether.
                    new ItemStack(Material.SOUL_SAND, random.nextInt(4)+1),
                    getInvisibilityPotion(1),
                    getStrengthPotion(1),
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
                    new ItemStack(Material.ARROW, random.nextInt(64)+16),
                    new ItemStack(Material.ENDER_PEARL, random.nextInt(4)+1),
                    new ItemStack(Material.GOLDEN_APPLE, random.nextInt(3)+1),
                    new ItemStack(Material.DIAMOND, random.nextInt(4)+1),
                    new ItemStack(Material.EXPERIENCE_BOTTLE, random.nextInt(15)+5),
                    new ItemStack(Material.BOW, 1),
              };
            for(Player player:Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(),Sound.BLOCK_BEACON_AMBIENT, 10f,10f);
            }
            message = "&6Un coffre &c&lEQUIPEMENT &6vient d'apparaître à la position : &bx=%d&6, &by=%d&6, &bz=%d&e&o";


       //idée de coffre suplémentaire : Coffre fermier, coffre mineur, coffre ?/.?§.?%^^?. (un coffre ultra bizarre faut voir ce qu'on en fait), Un coffre piège qui retire des poudres à celui qui l'ouvre ? et lui envoie un message (vous vous etes fait avoir par un faux coffre, vous perdez X poudres



        }else{
            ressources = new ItemStack[]{
                    new ItemStack(Material.DIAMOND, random.nextInt(15)+2),
                    new ItemStack(Material.GOLD_INGOT, random.nextInt(20)+10),
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
            }, 30000, 30000);// Spawn le coffre une fois toutes les 25 minutes

    }






}

