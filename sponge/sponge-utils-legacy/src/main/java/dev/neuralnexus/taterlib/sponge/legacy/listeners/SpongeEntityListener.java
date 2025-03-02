/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.sponge.legacy.listeners;

import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterlib.sponge.legacy.event.entity.SpongeEntityDamageEvent;
import dev.neuralnexus.taterlib.sponge.legacy.event.entity.SpongeEntityDeathEvent;
import dev.neuralnexus.taterlib.sponge.legacy.event.entity.SpongeEntitySpawnEvent;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.entity.SpawnEntityEvent;

/** Listens to entity events. */
public class SpongeEntityListener {
    /**
     * Called when an entity is damaged.
     *
     * @param event The event.
     */
    @Listener
    public void onEntityDamage(DamageEntityEvent event) {
        EntityEvents.DAMAGE.invoke(new SpongeEntityDamageEvent(event));
    }

    /**
     * Called when an entity dies.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerDeath(DestructEntityEvent.Death event) {
        EntityEvents.DEATH.invoke(new SpongeEntityDeathEvent(event));
    }

    /**
     * Called when an entity is spawned.
     *
     * @param event The event.
     */
    @Listener
    public void onEntitySpawn(SpawnEntityEvent.Custom event) {
        EntityEvents.SPAWN.invoke(new SpongeEntitySpawnEvent(event));
    }
}
