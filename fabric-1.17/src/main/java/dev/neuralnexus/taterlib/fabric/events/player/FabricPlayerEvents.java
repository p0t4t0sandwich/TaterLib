package dev.neuralnexus.taterlib.fabric.events.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public final class FabricPlayerEvents {
    /**
     * Called when a player advances an advancement.
     */
    public static final Event<PlayerAdvancement> ADVANCEMENT = EventFactory.createArrayBacked(PlayerAdvancement.class, (listeners) -> (player, advancement) -> {
        for (PlayerAdvancement listener : listeners) {
            listener.onPlayerAdvancement(player, advancement);
        }
    });

    /**
     * Called when a player finishes an advancement.
     */
    public static final Event<PlayerAdvancementFinished> ADVANCEMENT_FINISHED = EventFactory.createArrayBacked(PlayerAdvancementFinished.class, (listeners) -> (player, advancement) -> {
        for (PlayerAdvancementFinished listener : listeners) {
            listener.onPlayerAdvancementFinished(player, advancement);
        }
    });

    /**
     * Called when a player dies.
     */
    public static final Event<PlayerDeath> DEATH = EventFactory.createArrayBacked(PlayerDeath.class, (listeners) -> (player, source) -> {
        for (PlayerDeath listener : listeners) {
            listener.onPlayerDeath(player, source);
        }
    });

    /**
     * Called when a player sends a message.
     */
    public static final Event<PlayerMessage> MESSAGE = EventFactory.createArrayBacked(PlayerMessage.class, (listeners) -> (player, message, isCanceled) -> {
        for (PlayerMessage listener : listeners) {
            listener.onPlayerMessage(player, message, isCanceled);
        }
    });

    /**
     * Called when a player respawns.
     */
    public static final Event<FabricPlayerRespawnEvent> RESPAWN = EventFactory.createArrayBacked(FabricPlayerRespawnEvent.class, (listeners) -> (player) -> {
        for (FabricPlayerRespawnEvent listener : listeners) {
            listener.onPlayerRespawn(player);
        }
    });

    @FunctionalInterface
    public interface PlayerAdvancement {
        void onPlayerAdvancement(PlayerEntity player, Advancement advancement);
    }

    @FunctionalInterface
    public interface PlayerAdvancementFinished {
        void onPlayerAdvancementFinished(PlayerEntity player, Advancement advancement);
    }

    @FunctionalInterface
    public interface PlayerDeath {
        void onPlayerDeath(PlayerEntity player, DamageSource source);
    }

    @FunctionalInterface
    public interface PlayerMessage {
        void onPlayerMessage(PlayerEntity player, String message, boolean isCanceled);
    }

    @FunctionalInterface
    public interface FabricPlayerRespawnEvent {
        void onPlayerRespawn(PlayerEntity player);
    }
}
