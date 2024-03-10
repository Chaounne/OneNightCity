package me.chaounne.onenightcity.game;

import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class PoudreItem {

    private static ItemStack poudre;

    public static ItemStack getItem(){
        poudre = new ItemBuilder(Material.SUGAR)
                .name(ChatColor.GOLD + "Poudre").enchant(Enchantment.LUCK,1).flags(ItemFlag.HIDE_ENCHANTS).build();

        return poudre;
    }

    public static ItemStack getItem(int amount){
        poudre = new ItemBuilder(Material.SUGAR)
                .name(ChatColor.GOLD + "Poudre").amount(amount).enchant(Enchantment.LUCK,1).flags(ItemFlag.HIDE_ENCHANTS).build();

        return poudre;
    }

    public static ItemStack getSuperPoudre(){
        poudre = new ItemBuilder(Material.SUGAR)
                .name(ChatColor.GOLD + "" + ChatColor.BOLD + "Super Poudre").enchant(Enchantment.LUCK,1).flags(ItemFlag.HIDE_ENCHANTS).build();

        return poudre;
    }
    public static ItemStack getSuperPoudre(int amount){
        poudre = new ItemBuilder(Material.SUGAR)
                .name(ChatColor.GOLD + ""+ChatColor.BOLD +"Super Poudre").amount(amount).enchant(Enchantment.LUCK,1).flags(ItemFlag.HIDE_ENCHANTS).build();

        return poudre;
    }


}
