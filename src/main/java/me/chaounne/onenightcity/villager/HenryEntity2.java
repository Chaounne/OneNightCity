package me.chaounne.onenightcity.villager;


import me.chaounne.onenightcity.game.PoudreItem;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;

public class HenryEntity2 {

    public static Villager henry2;

    public HenryEntity2() {

    }

    public static void removeEntity() {
        if (henry2 != null) {
            henry2.remove();
            henry2 = null;
        }
    }

    public static Villager getEntity(Location loc) {
        henry2 = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        henry2.setCustomName(ChatColor.DARK_RED + "DARKHenry");
        henry2.setCustomNameVisible(true);
        henry2.setProfession(Villager.Profession.WEAPONSMITH);
        henry2.setAI(false);
        henry2.setSilent(true);
        henry2.setCollidable(false);
        henry2.setVillagerExperience(5);
        henry2.setVillagerLevel(5);
        henry2.setAdult();
        henry2.setCanPickupItems(false);
        henry2.setRemoveWhenFarAway(false);

        List<MerchantRecipe> trades = new ArrayList<>();
        trades.add(new MerchantRecipe(PoudreItem.getItem(1), 1));
        trades.get(0).addIngredient(new ItemStack(Material.BEACON, 1));

        henry2.setRecipes(trades);

        return henry2;
    }

    public static void onTrade(Player player) {
        HenryEntity2.removeEntity();

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(ChatColor.RED + "DARKHENRY : Vous avez laissé passer votre chance ? Ou bien avez-vous été devancé ?", "ADIEU");
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 5f, 5f);
        }

        // Send message to all players about the trade
        String message = ChatColor.GREEN + "Le joueur " + player.getName() + " vient de faire un échange avec DARKHENRY !";
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(message);
        }
    }

}

