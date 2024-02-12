package dev.neuralnexus.taterlib.bukkit.event.entity;

import dev.neuralnexus.taterlib.bukkit.util.BukkitLocation;
import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.utils.Location;

import org.bukkit.event.entity.CreatureSpawnEvent;

/** Bukkit implementation of {@link EntitySpawnEvent}. */
public class BukkitEntitySpawnEvent extends BukkitEntityEvent implements EntitySpawnEvent {
    private final CreatureSpawnEvent event;

    public BukkitEntitySpawnEvent(CreatureSpawnEvent event) {
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
