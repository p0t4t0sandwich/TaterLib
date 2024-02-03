package dev.neuralnexus.taterlib.v1_19.vanilla.mixin.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.player.VanillaPlayerAdvancementEvent;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.server.PlayerAdvancements;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player advancement finished listener. */
@Mixin(PlayerAdvancements.class)
public class PlayerAdvancementFinishedMixin_1_19 {
    /** Called when a player completes an advancement. */
    @Inject(method = "unregisterListeners", at = @At("HEAD"))
    public void onPlayerAdvancementFinished(Advancement advancement, CallbackInfo ci) {
        if (advancement.getDisplay() != null) {
            DisplayInfo display = advancement.getDisplay();
            if (display.shouldAnnounceChat()
                    && ((PlayerAdvancements) (Object) this)
                            .getOrStartProgress(advancement)
                            .isDone()) {
                PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                        new VanillaPlayerAdvancementEvent.AdvancementFinished(
                                ((PlayerAdvancements) (Object) this).player, advancement));
            }
        }
    }
}
