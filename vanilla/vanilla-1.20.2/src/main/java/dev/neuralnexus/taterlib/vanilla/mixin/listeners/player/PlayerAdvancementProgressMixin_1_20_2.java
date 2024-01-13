package dev.neuralnexus.taterlib.vanilla.mixin.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.vanilla.event.player.VanillaPlayerAdvancementEvent_1_20;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Mixin for the player advancement progress listener. */
@Mixin(PlayerAdvancements.class)
public abstract class PlayerAdvancementProgressMixin_1_20_2 {
    @Shadow private ServerPlayer player;

    /**
     * Called when a player completes an advancement.
     *
     * @param advancementHolder The advancement
     * @param criterionName The criterion name
     * @param cir Callback info
     */
    @Inject(method = "award", at = @At("HEAD"))
    public void onPlayerAdvancementProgress(
            AdvancementHolder advancementHolder,
            String criterionName,
            CallbackInfoReturnable<Boolean> cir) {
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                new VanillaPlayerAdvancementEvent_1_20.AdvancementProgress(
                        this.player, advancementHolder.value(), criterionName));
    }
}
