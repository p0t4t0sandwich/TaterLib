/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.v1_16.fabric.event.api.FabricPlayerEvents;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.network.ServerPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Mixin for the player advancement progress listener. */
@Mixin(PlayerAdvancementTracker.class)
public abstract class PlayerAdvancementProgressMixin_1_16 {
    @Shadow private ServerPlayerEntity owner;

    /**
     * Called when a player completes an advancement.
     *
     * @param advancement The advancement
     * @param criterionName The criterion name
     * @param cir Callback info
     */
    @Inject(method = "grantCriterion", at = @At("HEAD"))
    public void onPlayerAdvancementProgress(
            Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        FabricPlayerEvents.ADVANCEMENT_PROGRESS
                .invoker()
                .onPlayerAdvancementProgress(this.owner, advancement, criterionName);
    }
}
