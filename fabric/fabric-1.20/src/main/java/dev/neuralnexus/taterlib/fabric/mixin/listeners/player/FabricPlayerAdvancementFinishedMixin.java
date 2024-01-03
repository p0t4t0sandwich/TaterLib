package dev.neuralnexus.taterlib.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.fabric.event.api.FabricPlayerEvents;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player advancement finished listener. */
@Mixin(PlayerAdvancements.class)
public abstract class FabricPlayerAdvancementFinishedMixin {
    @Shadow private ServerPlayer player;

    @Shadow
    public abstract AdvancementProgress getOrStartProgress(Advancement advancement);

    /**
     * Called when a player completes an advancement.
     *
     * @param advancement The advancement
     * @param ci Callback info
     */
    @Inject(method = "unregisterListeners", at = @At("HEAD"))
    public void onPlayerAdvancementFinished(Advancement advancement, CallbackInfo ci) {
        if (advancement.getDisplay() != null) {
            DisplayInfo display = advancement.getDisplay();
            if (display.shouldAnnounceChat() && getOrStartProgress(advancement).isDone()) {
                FabricPlayerEvents.ADVANCEMENT_FINISHED
                        .invoker()
                        .onPlayerAdvancementFinished(this.player, advancement);
            }
        }
    }
}
