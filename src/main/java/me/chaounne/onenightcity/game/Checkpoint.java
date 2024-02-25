package me.chaounne.onenightcity.game;

import org.bukkit.Location;

public class Checkpoint {

    private final Location location;
    private long timestamp;
    private boolean activated;
    private boolean firstCheckpointMessageSent;
    private boolean checkpointMessageSent;
    private boolean finalCheckpointMessageSent;
    private boolean woolGiven;

    public Checkpoint(Location location) {
        this.location = location;
        this.activated = false;
        this.timestamp = System.currentTimeMillis();
        this.firstCheckpointMessageSent = false;
        this.checkpointMessageSent = false;
        this.finalCheckpointMessageSent = false;
        this.woolGiven = false;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void resetTimestamp() {
        this.timestamp = System.currentTimeMillis();
    }

    public boolean isFirstCheckpointMessageSent() {
        return firstCheckpointMessageSent;
    }

    public void setFirstCheckpointMessageSent(boolean firstCheckpointMessageSent) {
        this.firstCheckpointMessageSent = firstCheckpointMessageSent;
    }
    public boolean isCheckpointMessageSent() {
        return checkpointMessageSent;
    }

    public void setCheckpointMessageSent(boolean checkpointMessageSent) {
        this.checkpointMessageSent = checkpointMessageSent;
    }

    public boolean isFinalCheckpointMessageSent() {
        return finalCheckpointMessageSent;
    }

    public void setFinalCheckpointMessageSent(boolean finalCheckpointMessageSent) {
        this.finalCheckpointMessageSent = finalCheckpointMessageSent;
    }

    public boolean isWoolGiven() {
        return woolGiven;
    }

    public void setWoolGiven(boolean woolGiven) {
        this.woolGiven = woolGiven;
    }

}
