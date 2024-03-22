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

public class PlayerTracker implements Listener {

    private static final Map<Player, PlayerTracker> trackers = new HashMap<>();

    private final Player owner;

    private final Player target;

    private final ItemStack compass;

    private Material replacedBlock;

    private Location replacedLocation;

    private boolean wasInOffHand;

    private int index = -1;

    private int timer = 5 * 60;

    private int taskId;

    public PlayerTracker(Player owner, Player target, ItemStack compass) {
        this.owner = owner;
        this.target = target;
        this.compass = compass;
        startTracking();
        trackers.put(owner, this);
    }

    private void startTracking() {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(OneNightCity.getInstance(),
                () -> {
                    if (timer == 0) {
                        if (owner.getInventory().getItemInOffHand().equals(compass))
                            owner.getInventory().setItemInOffHand(null);
                        else
                            owner.getInventory().remove(compass);
                        System.out.println("FIN");
                        replacedLocation.getWorld().getBlockAt(replacedLocation).setType(replacedBlock);
                        Bukkit.getScheduler().cancelTask(taskId);
                        return;
                    }
                    ItemMeta meta = compass.getItemMeta();
                    if (meta != null) {
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
                    }
                    owner.getInventory().remove(compass);
                    if (owner.getInventory().getItemInOffHand().equals(compass)) {
                        wasInOffHand = true;
                        owner.getInventory().setItemInOffHand(null);
                    } else
                        wasInOffHand = false;
                    compass.setItemMeta(meta);
                    if (wasInOffHand)
                        owner.getInventory().setItemInOffHand(compass);
                    else if (index != -1)
                        owner.getInventory().setItem(index, compass);
                    else {
                        owner.getInventory().addItem(compass);
                    }
                    timer -= 5;
                }, 0, 100);

        Bukkit.broadcastMessage(ChatColor.RED + "Un traqueur de joueur vient d'être activé, faites attention...");
    }

    public void setSlot(int index) {
        this.index = index;
    }

    public static Map<Player, PlayerTracker> getTrackers() {
        return trackers;
    }

    public static ItemStack getItem() {
        return new ItemBuilder(Material.COMPASS).name(getItemName() + "(désactivé)").build();
    }

    public static String getItemName() {
        return ChatColor.LIGHT_PURPLE + "Traqueur de joueur ";
    }

}