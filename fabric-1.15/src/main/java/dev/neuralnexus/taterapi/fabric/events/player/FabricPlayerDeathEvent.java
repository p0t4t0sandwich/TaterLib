package dev.neuralnexus.taterapi.fabric.events.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

/**
 * This is a Fabric event that is fired when a player dies.
 */
public interface FabricPlayerDeathEvent {
    Event<FabricPlayerDeathEvent> EVENT = EventFactory.createArrayBacked(FabricPlayerDeathEvent.class, (listeners) -> (player, source) -> {
        for (FabricPlayerDeathEvent listener : listeners) {
            listener.onPlayerDeath(player, source);
        }
    });

    void onPlayerDeath(PlayerEntity player, DamageSource source);
}
