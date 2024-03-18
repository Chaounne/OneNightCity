package me.chaounne.onenightcity.villager;

import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public abstract class Trader {

    protected SampleInventory inventory;

    protected Trader(Location loc, String name, Villager.Type type, Villager.Profession profession, int size) {
        createTrader(loc, name, type, profession, size);
        addTrades();
    }

    protected void createTrader(Location loc, String name, Villager.Type type, Villager.Profession profession, int size) {
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

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

        inventory = new SampleInventory(size, name);
    }

    protected abstract void addTrades();

}