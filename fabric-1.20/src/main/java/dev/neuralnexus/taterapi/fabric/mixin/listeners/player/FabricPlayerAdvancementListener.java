package dev.neuralnexus.taterapi.fabric.mixin.listeners.player;

import dev.neuralnexus.taterapi.fabric.events.player.FabricPlayerAdvancementEvent;
import dev.neuralnexus.taterapi.fabric.events.player.FabricPlayerAdvancementFinishedEvent;
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

/**
 * Listens for player advancements and emits an event.
 */
@Mixin(PlayerAdvancementTracker.class)
public abstract class FabricPlayerAdvancementListener {
    @Shadow private ServerPlayerEntity owner;
    @Shadow public abstract AdvancementProgress getProgress(Advancement advancement);

    /**
     * Called when a player completes an advancement.
     * @param advancement The advancement
     * @param ci Callback info
     */
    @Inject(method = "endTrackingCompleted", at = @At("HEAD"))
    public void onPlayerAdvancement(Advancement advancement, CallbackInfo ci) {
        // Fire the generic advancement event
        FabricPlayerAdvancementEvent.EVENT.invoker().onPlayerAdvancement(this.owner, advancement);

        AdvancementDisplay display = advancement.getDisplay();
        if (display != null && display.shouldAnnounceToChat() && getProgress(advancement).isDone()) {
            // Fire the advancement finished event
            FabricPlayerAdvancementFinishedEvent.EVENT.invoker().onPlayerAdvancementFinished(this.owner, advancement);
        }
    }
}
