package me.chaounne.onenightcity.villager.spawners;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class SpawnerItems {

    public static ItemStack getIronSpawner() {
        return new ItemBuilder(Material.BARREL).name(ChatColor.GRAY + "Spawner de fer")
                .enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

    public static ItemStack getGoldSpawner() {
        return new ItemBuilder(Material.BARREL).name(ChatColor.GOLD + "Spawner d'or")
                .enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

    public static ItemStack getDiamondSpawner() {
        return new ItemBuilder(Material.BARREL).name(ChatColor.BLUE + "Spawner de diamant")
                .enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

    public static ItemStack getEmeraldSpawner() {
        return new ItemBuilder(Material.BARREL).name(ChatColor.GREEN + "Spawner d'émeraude")
                .enchant(Enchantment.LUCK).flags(ItemFlag.HIDE_ENCHANTS).build();
    }

}