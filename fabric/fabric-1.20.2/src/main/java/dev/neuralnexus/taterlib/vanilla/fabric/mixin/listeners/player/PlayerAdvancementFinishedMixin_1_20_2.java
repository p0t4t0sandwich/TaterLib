package dev.neuralnexus.taterlib.vanilla.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.vanilla.event.player.VanillaPlayerAdvancementEvent_1_20_2;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
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
public abstract class PlayerAdvancementFinishedMixin_1_20_2 {
    @Shadow private ServerPlayer player;

    @Shadow
    public abstract AdvancementProgress getOrStartProgress(AdvancementHolder advancement);

    /**
     * Called when a player completes an advancement.
     *
     * @param advancementHolder The advancement holder
     * @param ci Callback info
     */
    @Inject(method = "unregisterListeners", at = @At("HEAD"))
    public void onPlayerAdvancementFinished(AdvancementHolder advancementHolder, CallbackInfo ci) {
        Advancement advancement = advancementHolder.value();
        if (advancement.display().isPresent()) {
            DisplayInfo display = advancement.display().get();
            if (display.shouldAnnounceChat() && getOrStartProgress(advancementHolder).isDone()) {
                PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                        new VanillaPlayerAdvancementEvent_1_20_2.AdvancementFinished(
                                this.player, advancementHolder));
            }
        }
    }
}
