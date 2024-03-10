package me.chaounne.onenightcity.villager.spawners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import fr.mrmicky.fastinv.ItemBuilder;

public class SpawnerItems {
    
    public static ItemStack getDiamondSpawner(){
        ItemStack spawner = new ItemBuilder(Material.BARREL).name(ChatColor.BLUE + "Spawner de diamant").enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
        return spawner;
    }

    public static ItemStack getGoldSpawner(){
        ItemStack spawner = new ItemBuilder(Material.BARREL).name(ChatColor.GOLD + "Spawner d'or").enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
        return spawner;
    }

    public static ItemStack getIronSpawner(){
        ItemStack spawner = new ItemBuilder(Material.BARREL).name(ChatColor.GRAY + "Spawner de fer").enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
        return spawner;
    }

    public static ItemStack getEmeraldSpawner(){
        ItemStack spawner = new ItemBuilder(Material.BARREL).name(ChatColor.GREEN + "Spawner d'émeraude").enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
        return spawner;
    }
}