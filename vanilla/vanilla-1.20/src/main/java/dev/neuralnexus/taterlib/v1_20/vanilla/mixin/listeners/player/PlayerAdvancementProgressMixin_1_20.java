package dev.neuralnexus.taterlib.v1_20.vanilla.mixin.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.player.VanillaPlayerAdvancementEvent;

import net.minecraft.advancements.Advancement;
import net.minecraft.server.PlayerAdvancements;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Mixin for the player advancement progress listener. */
@Mixin(PlayerAdvancements.class)
public class PlayerAdvancementProgressMixin_1_20 {
    /** Called when a player progresses an advancement. */
    @Inject(method = "award", at = @At("HEAD"))
    public void onPlayerAdvancementProgress(
            Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                new VanillaPlayerAdvancementEvent.AdvancementProgress(
                        ((PlayerAdvancements) (Object) this).player, advancement, criterionName));
    }
}
