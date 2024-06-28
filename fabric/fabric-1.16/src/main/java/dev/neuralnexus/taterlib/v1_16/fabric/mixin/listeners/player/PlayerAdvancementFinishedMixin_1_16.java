/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.v1_16.fabric.event.api.FabricPlayerEvents;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.network.ServerPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player advancement finished listener. */
@Mixin(PlayerAdvancementTracker.class)
public abstract class PlayerAdvancementFinishedMixin_1_16 {
    @Shadow private ServerPlayerEntity owner;

    @Shadow
    public abstract AdvancementProgress getProgress(Advancement advancement);

    /**
     * Called when a player completes an advancement.
     *
     * @param advancement The advancement
     * @param ci Callback info
     */
    @Inject(method = "endTrackingCompleted", at = @At("HEAD"))
    public void onPlayerAdvancementFinished(Advancement advancement, CallbackInfo ci) {
        if (advancement.getDisplay() != null) {
            AdvancementDisplay display = advancement.getDisplay();
            if (display.shouldAnnounceToChat() && getProgress(advancement).isDone()) {
                FabricPlayerEvents.ADVANCEMENT_FINISHED
                        .invoker()
                        .onPlayerAdvancementFinished(this.owner, advancement);
            }
        }
    }
}
