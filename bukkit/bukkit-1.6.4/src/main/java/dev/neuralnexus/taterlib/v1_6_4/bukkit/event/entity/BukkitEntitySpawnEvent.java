/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_6_4.bukkit.event.entity;

import dev.neuralnexus.taterlib.event.entity.EntitySpawnEvent;
import dev.neuralnexus.taterlib.v1_6_4.bukkit.world.BukkitLocation;
import dev.neuralnexus.taterlib.world.Location;

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
