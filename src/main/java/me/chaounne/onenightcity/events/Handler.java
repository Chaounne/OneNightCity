package me.chaounne.onenightcity.events;

import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.game.PoudreItem;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class Handler implements Listener{

    @EventHandler
    public void onPlayerItemPickupEvent(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            GamePlayer gamePlayer = GamePlayer.getInstance(player);
            if(player.getGameMode() == GameMode.SURVIVAL) {
                if(event.getItem().getItemStack().equals(PoudreItem.getItem())){
                    // ajoute autant de score à la team du joueur que de poudre ramassée
                    for (int i = 0; i < event.getItem().getItemStack().getAmount(); i++) {
                        gamePlayer.getTeam().addScore(1);
                        gamePlayer.addScore(1);
                    }
                    // enlève toutes les poudres de l'inventaire du joueur
                    player.getInventory().remove(PoudreItem.getItem());
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
            if(event.getBlock().getType().equals(Material.GOLD_ORE)){
                // replace raw gold by gold ingot
                for (ItemStack item : event.getBlock().getDrops()) {
                    if(item.getType().equals(Material.RAW_GOLD)){
                        item.setType(Material.GOLD_INGOT);
                        // drop xp
                        event.setExpToDrop(1);
                    }
                }
            } else if (event.getBlock().getType().equals(Material.IRON_ORE)){
                // replace raw iron by iron ingot
                for (ItemStack item : event.getBlock().getDrops()) {
                    if(item.getType().equals(Material.RAW_IRON)){
                        item.setType(Material.IRON_INGOT);
                        // drop xp
                        event.setExpToDrop(1);
                    }
                }
            } else if (event.getBlock().getType().equals(Material.COPPER_ORE)){
                // replace raw copper by copper ingot
                for (ItemStack item : event.getBlock().getDrops()) {
                    if(item.getType().equals(Material.RAW_COPPER)){
                        item.setType(Material.COPPER_INGOT);
                        // drop xp
                        event.setExpToDrop(1);
                    }
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
}
