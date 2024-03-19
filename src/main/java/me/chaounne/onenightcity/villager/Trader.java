package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import java.util.LinkedHashMap;

public abstract class Trader {

    protected SampleInventory inventory;

    protected Villager villager;

    protected Trader(Location loc, String name, Villager.Type type, Villager.Profession profession) {
        createTrader(loc, name, type, profession);
        LinkedHashMap<Material, Integer[]> trades = getTrades();
        if (trades != null && !trades.isEmpty()) {
            int size = (int) Math.ceil((double) trades.size() / 9) * 9;
            inventory = new SampleInventory(size, name);
            trades.forEach((item, info) -> inventory.addItem(item, info[0], info[1]));
        }
    }

    protected void createTrader(Location loc, String name, Villager.Type type, Villager.Profession profession) {
        villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        villager.setCustomName(name);
        villager.setCustomNameVisible(true);
        if (type != null)
            villager.setVillagerType(type);
        villager.setProfession(profession);
        villager.setAI(false);
        villager.setSilent(true);
        villager.setVillagerLevel(5);
        villager.setCanPickupItems(false);
        villager.setRemoveWhenFarAway(false);
    }

    protected abstract LinkedHashMap<Material, Integer[]> getTrades();

}