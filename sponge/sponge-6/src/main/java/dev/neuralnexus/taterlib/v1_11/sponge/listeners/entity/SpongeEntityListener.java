/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_11.sponge.listeners.entity;

import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterlib.v1_11.sponge.event.entity.SpongeEntityDamageEvent;
import dev.neuralnexus.taterlib.v1_11.sponge.event.entity.SpongeEntityDeathEvent;
import dev.neuralnexus.taterlib.v1_11.sponge.event.entity.SpongeEntitySpawnEvent;

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
