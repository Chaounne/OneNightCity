package me.chaounne.onenightcity.events;

import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.PoudreItem;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class Handler implements Listener{

    private ONCGame game = ONCGame.getInstance();

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event){
        if(event.getPlayer() instanceof Player){
            Player player = (Player) event.getPlayer();
            if(player.getGameMode() == GameMode.SURVIVAL){
                if(player.getInventory().isEmpty()) return;
                GamePlayer gamePlayer = GamePlayer.getInstance(player);
                if(gamePlayer.getTeam() == null) return;
                // regarde si le joueur à de la poudre
                for(ItemStack item : player.getInventory().getContents()){
                    if(item != null&&Objects.equals(item.getItemMeta(), PoudreItem.getItem().getItemMeta())){
                        // ajoute autant de score à la team du joueur que de poudre ramassée
                        for (int i = 0; i < item.getAmount(); i++) {
                            gamePlayer.getTeam().addScore(1);
                            gamePlayer.addScore(1);
                        }
                        player.getInventory().removeItem(item);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        GamePlayer gamePlayer = GamePlayer.getInstance(player);
        player.teleport(new Location(player.getWorld(), 0, 70, 0));

        if(gamePlayer.hasBounty()){
            Player killer = player.getKiller();
            if(killer != null){
                GamePlayer gameKiller = GamePlayer.getInstance(killer);
                GamePlayer beter = gamePlayer.getBeter();
                gameKiller.addScore(gamePlayer.getBounty());
                gameKiller.getTeam().addScore(gamePlayer.getBounty());

                if(beter != null && beter.getTeam() != null && beter.getTeam() != gameKiller.getTeam() && beter.getTeam() != gamePlayer.getTeam()){
                    beter.addScore((int) (gamePlayer.getBounty() * 1.25));
                    beter.getTeam().addScore((int) (gamePlayer.getBounty() * 1.25));
                }

                System.out.println("bounty : " + gamePlayer.getBounty());

                assert beter != null;
                beter.getPlayer().sendMessage("Vous avez récupéré la prime de " + gamePlayer.getBounty() + " points multipliée par 1.25 !");
                gameKiller.getPlayer().sendMessage("Vous avez tué " + player.getName() + " et récupéré sa prime de " + gamePlayer.getBounty() + " points !");
                gamePlayer.getPlayer().sendMessage("Vous avez été tué par " + killer.getName() + " et perdu votre prime de " + gamePlayer.getBounty() + " points !");

                gamePlayer.removeBounty();
                gamePlayer.removeBeter();
            }
        }
    }

    @EventHandler
    public void onPlayerItemDropped(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        if(player.getGameMode() == GameMode.SURVIVAL){
            Item item = event.getItemDrop();
            if(Objects.equals(item.getItemStack().getItemMeta(), PoudreItem.getItem().getItemMeta())){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if(event.getPlayer() instanceof Player){
            Player player = (Player) event.getPlayer();
            if(player.getGameMode() == GameMode.SURVIVAL){
                if(player.getInventory().isEmpty()) return;
                GamePlayer gamePlayer = GamePlayer.getInstance(player);
                if(gamePlayer.getTeam() == null) return;
                // regarde si le joueur à de la poudre
                for(ItemStack item : player.getInventory().getContents()){
                    if(item != null&& Objects.equals(item.getItemMeta(), PoudreItem.getItem().getItemMeta())){
                        // ajoute autant de score à la team du joueur que de poudre ramassée
                        for (int i = 0; i < item.getAmount(); i++) {
                            gamePlayer.getTeam().addScore(1);
                            gamePlayer.addScore(1);
                        }
                        player.getInventory().removeItem(item);
                    }
                }
            }
        }
    }

    // cut clean
    // Ici qu'on gere si un joueur essaie de casser un block dans le spawn ?
    // reponse : Oui tu peux gérer ça ici
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        if(event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            if(event.getBlock().getType().equals(Material.GOLD_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_GOLD_ORE)){
                event.setCancelled(true);
                int fortuneLevel = event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                int drops = fortuneLevel > 0 ? (int) (Math.random() * (2 + fortuneLevel)) + 1 : 1; // calcul du nombre de lingots d'or générés
                event.getBlock().setType(Material.AIR);
                for (int i = 0; i < drops; i++) { // boucle pour générer le nombre de lingots d'or spécifié
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
                    event.getPlayer().setExp(event.getPlayer().getExp() + 1);
                }
            } else if(event.getBlock().getType().equals(Material.IRON_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_IRON_ORE)){
                event.setCancelled(true);
                int fortuneLevel = event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                int drops = fortuneLevel > 0 ? (int) (Math.random() * (2 + fortuneLevel)) + 1 : 1; // calcul du nombre de lingots de fer générés
                event.getBlock().setType(Material.AIR);
                for (int i = 0; i < drops; i++) { // boucle pour générer le nombre de lingots de fer spécifié
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                    event.getPlayer().setExp(event.getPlayer().getExp() + 1);
                }
            } else if(event.getBlock().getType().equals(Material.COPPER_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_COPPER_ORE)){
                event.setCancelled(true);
                int fortuneLevel = event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                int drops = fortuneLevel > 0 ? (int) (Math.random() * (2 + fortuneLevel)) + 1 : 1; // calcul du nombre de lingots de cuivre générés
                event.getBlock().setType(Material.AIR);
                for (int i = 0; i < drops; i++) { // boucle pour générer le nombre de lingots de cuivre spécifié
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.COPPER_INGOT));
                    event.getPlayer().setExp(event.getPlayer().getExp() + 1);
                }
            }
        }
    }

    // bouffe déjà cuite
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        Entity entity = event.getEntity();
        if (entity instanceof Pig){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.PORKCHOP)){
                    item.setType(Material.COOKED_PORKCHOP);
                }
            }
        } else if(entity instanceof Cow){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.BEEF)){
                    item.setType(Material.COOKED_BEEF);
                }
            }
        } else if (entity instanceof Sheep){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.MUTTON)){
                    item.setType(Material.COOKED_MUTTON);
                }
            }
        } else if (entity instanceof Chicken){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.CHICKEN)){
                    item.setType(Material.COOKED_CHICKEN);
                }
            }
        } else if(entity instanceof Rabbit){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.RABBIT)){
                    item.setType(Material.COOKED_RABBIT);
                }
            }
        } else if(entity instanceof Salmon){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.SALMON)){
                    item.setType(Material.COOKED_SALMON);
                }
            }
        } else if(entity instanceof Cod){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.COD)){
                    item.setType(Material.COOKED_COD);
                }
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event){if(!game.isStarted()) event.setCancelled(true);}

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){if(!game.isStarted()) event.setCancelled(true);}

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent e) {e.setCancelled(true);}
}
