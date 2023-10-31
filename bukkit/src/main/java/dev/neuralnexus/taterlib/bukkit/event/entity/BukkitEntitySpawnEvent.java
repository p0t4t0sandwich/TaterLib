package dev.neuralnexus.taterlib.bukkit.event.entity;

import dev.neuralnexus.taterlib.bukkit.util.BukkitLocation;
import dev.neuralnexus.taterlib.common.event.entity.AbstractEntitySpawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import org.bukkit.event.entity.EntitySpawnEvent;

/**
 * Bukkit implementation of {@link AbstractEntitySpawnEvent}.
 */
public class BukkitEntitySpawnEvent extends BukkitEntityEvent implements AbstractEntitySpawnEvent {
    private final EntitySpawnEvent event;

    public BukkitEntitySpawnEvent(EntitySpawnEvent event) {
        super(event);
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Location getLocation() {
        return new BukkitLocation(event.getLocation());
    }
}
