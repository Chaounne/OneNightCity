package me.chaounne.onenightcity.villager;

import me.chaounne.fastinv.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class Spawners {

    public static ItemStack getDiamondSpawner(){
        ItemStack spawner = new ItemBuilder(Material.DIAMOND_BLOCK).name(ChatColor.BLUE + "Spawner de diamant").enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
        return spawner;
    }

    public static ItemStack getGoldSpawner(){
        ItemStack spawner = new ItemBuilder(Material.GOLD_BLOCK).name(ChatColor.GOLD + "Spawner d'or").enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
        return spawner;
    }

    public static ItemStack getIronSpawner(){
        ItemStack spawner = new ItemBuilder(Material.IRON_BLOCK).name(ChatColor.GRAY + "Spawner de fer").enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
        return spawner;
    }

    public static ItemStack getEmeraldSpawner(){
        ItemStack spawner = new ItemBuilder(Material.EMERALD_BLOCK).name(ChatColor.GREEN + "Spawner d'émeraude").enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
        return spawner;
    }
}
