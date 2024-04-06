package me.chaounne.onenightcity.events;

import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.game.GameTeam;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.PlayerTracker;
import me.chaounne.onenightcity.utils.RandomFromList;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.CompassMeta;

import java.util.*;

public class TrackerHandler implements Listener {

    private Map<GameTeam, Integer> timesBought = new HashMap<>();

    @EventHandler
    public void onTradeSelect(TradeSelectEvent event) {
        ItemStack result = event.getMerchant().getRecipe(event.getIndex()).getResult();
        if (result.getItemMeta() instanceof CompassMeta meta
                && meta.getDisplayName().equals(PlayerTracker.getItemName() + "(désactivé)")) {
            Player player = (Player) event.getWhoClicked();
            GamePlayer gp = GamePlayer.getInstance(player);
            if (timesBought.containsKey(gp.getTeam()) && timesBought.get(gp.getTeam()) == 4)
                player.sendMessage(ChatColor.RED + "Votre équipe a atteint le quota de traqueurs (4), vous ne pouvez plus en acheter.");
            else if (gp.getScore() >= 25000 && player.getInventory().firstEmpty() != -1) {
                gp.substractScore(25000);
                gp.getTeam().substractScore(25000);
                UUID uuid = UUID.randomUUID();
                meta.setLore(List.of(uuid + ""));
                result.setItemMeta(meta);
                player.getInventory().addItem(result);
                if (timesBought.putIfAbsent(gp.getTeam(), 1) != null)
                    timesBought.put(gp.getTeam(), timesBought.get(gp.getTeam()) + 1);
            } else
                player.sendMessage(ChatColor.RED + "Vous ne pouvez pas vous payer ça ou vous avez l'inventaire plein.");
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();

        if (item != null && event.getAction() == Action.RIGHT_CLICK_AIR
                && item.getItemMeta() instanceof CompassMeta meta
                && meta.getDisplayName().equals(PlayerTracker.getItemName() + "(désactivé)")) {

            item.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            UUID uuid = UUID.fromString(meta.getLore().get(0));

            Player player = event.getPlayer();

            List<Player> potentialTargets = new ArrayList<>();
            ONCGame.getInstance().getTeams().forEach(gt -> {
                if (gt != GamePlayer.getInstance(player).getTeam())
                    potentialTargets.addAll(gt.getPlayers());
            });
            Player target = RandomFromList.get(potentialTargets);

            new PlayerTracker(player, target, uuid);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // si pas dans l'inventaire du joueur -> interdire SWAP_OFFHAND
        Inventory inv = event.getClickedInventory();
        if (!(inv instanceof PlayerInventory)) {
            if (event.getClick() == ClickType.SWAP_OFFHAND
                    && event.getWhoClicked().getInventory().getItemInOffHand().getItemMeta() instanceof CompassMeta metaInv
                    && metaInv.getDisplayName().startsWith(PlayerTracker.getItemName() + "(0"))
                event.setCancelled(true);
            else if ((event.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD || event.getAction() == InventoryAction.HOTBAR_SWAP)
                    && event.getWhoClicked().getInventory().getItem(event.getHotbarButton()).getItemMeta() instanceof CompassMeta metaInv2
                    && metaInv2.getDisplayName().startsWith(PlayerTracker.getItemName() + "(0"))
                event.setCancelled(true);
        }

        ItemStack item = event.getCurrentItem();

        // si dans l'inventaire du joueur
        if (item != null && item.getItemMeta() instanceof CompassMeta meta
                && meta.getDisplayName().startsWith(PlayerTracker.getItemName() + "(0")
                && event.getAction() != InventoryAction.HOTBAR_SWAP)
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getItemMeta() instanceof CompassMeta meta
                && meta.getDisplayName().startsWith(PlayerTracker.getItemName() + "(0"))
            event.setCancelled(true);
    }

}