package dev.neuralnexus.taterlib.bukkit.event.api.entity;

import dev.neuralnexus.taterlib.bukkit.util.BukkitLocation;
import dev.neuralnexus.taterlib.common.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.common.utils.Location;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Bukkit implementation of {@link EntitySpawnEvent}.
 */
public class BukkitEntitySpawnEvent extends BukkitEntityEvent implements EntitySpawnEvent {
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
