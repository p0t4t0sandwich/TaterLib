package dev.neuralnexus.taterlib.v1_13_2.bukkit.event.entity;

import dev.neuralnexus.taterlib.v1_13_2.bukkit.world.BukkitLocation;
import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.world.Location;

/** Bukkit implementation of {@link EntitySpawnEvent}. */
public class BukkitEntitySpawnEvent extends BukkitEntityEvent implements EntitySpawnEvent {
    private final org.bukkit.event.entity.EntitySpawnEvent event;

    public BukkitEntitySpawnEvent(org.bukkit.event.entity.EntitySpawnEvent event) {
        super(event);
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean cancelled() {
        return event.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new BukkitLocation(event.getLocation());
    }
}
