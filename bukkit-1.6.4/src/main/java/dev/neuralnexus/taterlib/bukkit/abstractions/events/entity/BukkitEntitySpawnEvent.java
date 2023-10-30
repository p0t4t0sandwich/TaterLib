package dev.neuralnexus.taterlib.bukkit.abstractions.events.entity;

import dev.neuralnexus.taterlib.bukkit.abstractions.util.BukkitLocation;
import dev.neuralnexus.taterlib.common.abstractions.events.entity.AbstractEntitySpawnEvent;
import dev.neuralnexus.taterlib.common.abstractions.utils.Location;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Bukkit implementation of {@link AbstractEntitySpawnEvent}.
 */
public class BukkitEntitySpawnEvent extends BukkitEntityEvent implements AbstractEntitySpawnEvent {
    private final CreatureSpawnEvent event;

    public BukkitEntitySpawnEvent(CreatureSpawnEvent event) {
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