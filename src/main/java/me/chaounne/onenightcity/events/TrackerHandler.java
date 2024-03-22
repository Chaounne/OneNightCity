package me.chaounne.onenightcity.events;

import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.PlayerTracker;
import me.chaounne.onenightcity.utils.RandomFromList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TrackerHandler implements Listener {

    @EventHandler
    public void onTradeSelect(TradeSelectEvent event) {
        ItemStack result = event.getMerchant().getRecipe(event.getIndex()).getResult();
        if (result.getItemMeta() instanceof CompassMeta meta) {
            if (meta.getDisplayName().equals(PlayerTracker.getItemName() + "(désactivé)")) {
                Player player = (Player) event.getWhoClicked();
                GamePlayer gp = GamePlayer.getInstance(player);
                if (gp.getScore() >= 50000 && player.getInventory().firstEmpty() != -1) {
                    gp.substractScore(50000);
                    gp.getTeam().substractScore(50000);
                    player.getInventory().addItem(result);
                } else
                    player.sendMessage(ChatColor.RED + "Vous ne pouvez pas vous payer ça ou vous avez l'inventaire plein.");
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.COMPASS
                && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                && item.getItemMeta() instanceof CompassMeta meta
                && meta.getDisplayName().equals(PlayerTracker.getItemName() + "(désactivé)")) {

            ItemStack tracker = PlayerTracker.getItem();
            boolean isInOffHand = false;
            if (player.getInventory().getItemInOffHand().equals(tracker)) {
                isInOffHand = true;
                player.getInventory().setItemInOffHand(null);
            }
            else
                player.getInventory().removeItem(tracker);

            tracker.addEnchantment(Enchantment.VANISHING_CURSE, 1);

            List<Player> potentialTargets = new ArrayList<>();
            ONCGame.getInstance().getTeams().forEach(gt -> {
                if (gt != GamePlayer.getInstance(player).getTeam())
                    potentialTargets.addAll(gt.getPlayers());
            });
            Player target = RandomFromList.get(potentialTargets);

            new PlayerTracker(player, target, tracker, isInOffHand);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item == null) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        if (meta.getDisplayName().startsWith(PlayerTracker.getItemName() + "(0")) {
            if (event.getAction() == InventoryAction.HOTBAR_SWAP) {
                PlayerTracker.getTrackers().get((Player) event.getWhoClicked()).setSlot(event.getHotbarButton());
            } else
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        if (PlayerTracker.getTrackers().containsKey(event.getPlayer()))
            PlayerTracker.getTrackers().get(event.getPlayer()).setSlot(-1);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemMeta meta = event.getItemDrop().getItemStack().getItemMeta();
        if (meta != null && meta.getDisplayName().startsWith(PlayerTracker.getItemName() + "(0")) {
            event.setCancelled(true);
        }
    }

}