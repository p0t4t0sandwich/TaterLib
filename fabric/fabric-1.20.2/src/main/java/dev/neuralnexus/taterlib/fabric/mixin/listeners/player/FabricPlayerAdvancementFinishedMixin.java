package dev.neuralnexus.taterlib.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.fabric.event.api.FabricPlayerEvents;
import net.minecraft.advancement.*;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin for the player advancement finished listener.
 */
@Mixin(PlayerAdvancementTracker.class)
public abstract class FabricPlayerAdvancementFinishedMixin {
    @Shadow private ServerPlayerEntity owner;

    @Shadow public abstract AdvancementProgress getProgress(AdvancementEntry advancement);

    /**
     * Called when a player completes an advancement.
     * @param advancement The advancement
     * @param ci Callback info
     */
    @Inject(method = "endTrackingCompleted", at = @At("HEAD"))
    public void onPlayerAdvancementFinished(AdvancementEntry advancement, CallbackInfo ci) {
        if (advancement.value().display().isPresent()) {
            AdvancementDisplay display = advancement.value().display().get();
            if (display.shouldAnnounceToChat() && getProgress(advancement).isDone()) {
                FabricPlayerEvents.ADVANCEMENT_FINISHED.invoker().onPlayerAdvancementFinished(this.owner, advancement);
            }
        }
    }
}
