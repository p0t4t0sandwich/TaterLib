package dev.neuralnexus.taterlib.vanilla.mixin.listeners.server.playeradvancements;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.vanilla.event.player.VanillaPlayerAdvancementEvent;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.server.PlayerAdvancements;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Mixin for the player advancement finished listener. */
@Mixin(PlayerAdvancements.class)
public abstract class PlayerAdvancementsMixin_1_20_2 {
    /** Called when a player completes an advancement. */
    @Inject(method = "unregisterListeners", at = @At("HEAD"))
    public void onPlayerAdvancementFinished(AdvancementHolder advancementHolder, CallbackInfo ci) {
        Advancement advancement = advancementHolder.value();
        if (advancement.display().isPresent()) {
            DisplayInfo display = advancement.display().get();
            if (display.shouldAnnounceChat()
                    && ((PlayerAdvancements) (Object) this)
                            .getOrStartProgress(advancementHolder)
                            .isDone()) {
                PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                        new VanillaPlayerAdvancementEvent.AdvancementFinished(
                                ((PlayerAdvancements) (Object) this).player, advancementHolder));
            }
        }
    }

    /** Called when a player progresses an advancement. */
    @Inject(method = "award", at = @At("HEAD"))
    public void onPlayerAdvancementProgress(
            AdvancementHolder advancementHolder,
            String criterionName,
            CallbackInfoReturnable<Boolean> cir) {
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                new VanillaPlayerAdvancementEvent.AdvancementProgress(
                        ((PlayerAdvancements) (Object) this).player,
                        advancementHolder,
                        criterionName));
    }
}
