package me.chaounne.onenightcity.game.jump;

import org.bukkit.Location;

public class Checkpoint {

    private final Location location;

    public Checkpoint(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

}