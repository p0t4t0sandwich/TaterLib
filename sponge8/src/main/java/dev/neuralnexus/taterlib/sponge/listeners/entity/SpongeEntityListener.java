package dev.neuralnexus.taterlib.sponge.listeners.entity;

import dev.neuralnexus.taterlib.common.event.entity.EntityEvents;
import dev.neuralnexus.taterlib.sponge.abstractions.events.entity.SpongeEntityDamageEvent;
import dev.neuralnexus.taterlib.sponge.abstractions.events.entity.SpongeEntityDeathEvent;
import dev.neuralnexus.taterlib.sponge.abstractions.events.entity.SpongeEntitySpawnEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.entity.SpawnEntityEvent;

/**
 * Listens to entity events.
 */
public class SpongeEntityListener {
    /**
     * Called when an entity is damaged.
     * @param event The event.
     */
    @Listener
    public void onEntityDamage(DamageEntityEvent event) {
        EntityEvents.DAMAGE.invoke(new SpongeEntityDamageEvent(event));
    }

    /**
     * Called when an entity dies.
     * @param event The event.
     */
    @Listener
    public void onPlayerDeath(DestructEntityEvent.Death event) {
        EntityEvents.DEATH.invoke(new SpongeEntityDeathEvent(event));
    }

    /**
     * Called when an entity is spawned.
     * @param event The event.
     */
    @Listener
    public void onEntitySpawn(SpawnEntityEvent.Pre event) {
        EntityEvents.SPAWN.invoke(new SpongeEntitySpawnEvent(event));
    }
}
