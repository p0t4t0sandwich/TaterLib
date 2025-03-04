/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_10_2.fabric.event.api;

import net.legacyfabric.fabric.api.event.Event;
import net.legacyfabric.fabric.api.event.EventFactory;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public final class FabricPlayerEvents {
    /** Called when a player finishes an advancement. */
    public static final Event<PlayerAdvancementFinished> ADVANCEMENT_FINISHED =
            EventFactory.createArrayBacked(
                    PlayerAdvancementFinished.class,
                    (listeners) ->
                            (player, advancement) -> {
                                for (PlayerAdvancementFinished listener : listeners) {
                                    listener.onPlayerAdvancementFinished(player, advancement);
                                }
                            });

    /** Called when a player advances an advancement. */
    public static final Event<PlayerAdvancementProgress> ADVANCEMENT_PROGRESS =
            EventFactory.createArrayBacked(
                    PlayerAdvancementProgress.class,
                    (listeners) ->
                            (player, advancement, criterionName) -> {
                                for (PlayerAdvancementProgress listener : listeners) {
                                    listener.onPlayerAdvancementProgress(
                                            player, advancement, criterionName);
                                }
                            });

    public static final Event<PlayerDeath> DEATH =
            EventFactory.createArrayBacked(
                    PlayerDeath.class,
                    (listeners) ->
                            (player, source) -> {
                                for (PlayerDeath listener : listeners) {
                                    listener.onPlayerDeath(player, source);
                                }
                            });

    public static final Event<PlayerMessage> MESSAGE =
            EventFactory.createArrayBacked(
                    PlayerMessage.class,
                    (listeners) ->
                            (player, message, ci) -> {
                                for (PlayerMessage listener : listeners) {
                                    listener.onPlayerMessage(player, message, ci);
                                }
                            });

    public static final Event<PlayerRespawn> RESPAWN =
            EventFactory.createArrayBacked(
                    PlayerRespawn.class,
                    (listeners) ->
                            (player, dimension, alive) -> {
                                for (PlayerRespawn listener : listeners) {
                                    listener.onPlayerRespawn(player, dimension, alive);
                                }
                            });

    @FunctionalInterface
    public interface PlayerAdvancementFinished {
        void onPlayerAdvancementFinished(PlayerEntity player, Object advancement);
    }

    @FunctionalInterface
    public interface PlayerAdvancementProgress {
        void onPlayerAdvancementProgress(
                PlayerEntity player, Object advancement, String criterionName);
    }

    @FunctionalInterface
    public interface PlayerDeath {
        void onPlayerDeath(PlayerEntity player, DamageSource source);
    }

    @FunctionalInterface
    public interface PlayerMessage {
        void onPlayerMessage(PlayerEntity player, String message, CallbackInfo ci);
    }

    @FunctionalInterface
    public interface PlayerRespawn {
        void onPlayerRespawn(PlayerEntity player, int dimension, boolean alive);
    }
}
