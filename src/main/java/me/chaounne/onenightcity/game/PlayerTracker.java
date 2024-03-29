package me.chaounne.onenightcity.game;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerTracker implements Listener {

    private static final Map<UUID, PlayerTracker> trackers = new HashMap<>();

    private final Player owner;

    private final Player target;

    private final UUID uuid;

    private Material replacedBlock;

    private Location replacedLocation;

    private int timer = 5 * 60;

    private int taskId;

    public PlayerTracker(Player owner, Player target, UUID uuid) {
        this.owner = owner;
        this.target = target;
        this.uuid = uuid;
        trackers.put(uuid, this);
        createScheduler();
    }

    private void createScheduler() {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(),
                () -> {
                    ItemStack compass = getItemFromInv();
                    if (compass == null) {
                        stop();
                        return;
                    }

                    if (timer == 0) {
                        // obligé car remove() ne supprime pas l'item dans la main secondaire
                        if (owner.getInventory().getItemInOffHand().equals(compass))
                            owner.getInventory().setItemInOffHand(null);
                        else
                            removeItemFromInv();
                        replacedLocation.getWorld().getBlockAt(replacedLocation).setType(replacedBlock);
                        stop();
                        return;
                    }

                    // update lodestone
                    Location loc = target.getLocation();
                    switch (target.getWorld().getEnvironment()) {
                        case THE_END:
                            loc.setY(255);
                            break;
                        case NETHER:
                            loc.setY(0);
                            break;
                        default:
                            loc.setY(-64);
                            break;
                    }

                    ItemMeta meta = compass.getItemMeta();

                    // change blocks
                    if (replacedBlock != null)
                        replacedLocation.getWorld().getBlockAt(replacedLocation).setType(replacedBlock);
                    replacedBlock = target.getWorld().getBlockAt(loc).getType();
                    replacedLocation = loc;
                    if (target.getWorld().getBlockAt(loc).getType() != Material.LODESTONE)
                        target.getWorld().getBlockAt(loc).setType(Material.LODESTONE);
                    if (meta instanceof CompassMeta compassMeta)
                        compassMeta.setLodestone(loc);

                    // update timer
                    int minutes = timer / 60;
                    int seconds = timer % 60;
                    String strSeconds = seconds < 10 ? "0" + seconds : "" + seconds;
                    meta.setDisplayName(getItemName() + "(0" + minutes + ":" + strSeconds + ")");

                    compass.setItemMeta(meta);
                    timer -= 5;
                }, 0, 100);

        Bukkit.broadcastMessage(ChatColor.RED + "Un traqueur de joueur vient d'être activé, faites attention...");
    }

    private ItemStack getItemFromInv() {
        for (ItemStack item : owner.getInventory().getContents()) {
            if (item == null) continue;
            if (item.getItemMeta() instanceof CompassMeta meta
                    && meta.getLore() != null && meta.getLore().get(0).equals(uuid + ""))
                return item;
        }
        return null;
    }

    private void removeItemFromInv() {
        for (ItemStack item : owner.getInventory().getContents()) {
            if (item == null) continue;
            if (item.getItemMeta() instanceof CompassMeta meta
                    && meta.getLore() != null && meta.getLore().get(0).equals(uuid + "")) {
                owner.getInventory().remove(item);
                return;
            }
        }
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    public static Map<UUID, PlayerTracker> getTrackers() {
        return trackers;
    }

    public static ItemStack getItem() {
        return new ItemBuilder(Material.COMPASS).name(getItemName() + "(désactivé)").build();
    }

    public static String getItemName() {
        return ChatColor.LIGHT_PURPLE + "Traqueur de joueur ";
    }

}