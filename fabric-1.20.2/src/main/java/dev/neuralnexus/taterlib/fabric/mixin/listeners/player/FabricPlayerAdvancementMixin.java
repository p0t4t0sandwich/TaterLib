package dev.neuralnexus.taterlib.fabric.mixin.listeners.player;

import com.mojang.datafixers.DataFixer;
import dev.neuralnexus.taterlib.fabric.events.player.FabricPlayerEvents;
import net.minecraft.advancement.*;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;

/**
 * Listens for player advancements and emits an event.
 */
@Mixin(PlayerAdvancementTracker.class)
public abstract class FabricPlayerAdvancementMixin {
    @Shadow private ServerPlayerEntity owner;

    @Shadow public abstract AdvancementProgress getProgress(AdvancementEntry advancement);

    /**
     * Called when a player completes an advancement.
     * @param advancement The advancement
     * @param ci Callback info
     */
    @Inject(method = "endTrackingCompleted", at = @At("HEAD"))
    public void onPlayerAdvancement(AdvancementEntry advancement, CallbackInfo ci) {
        // Fire the generic advancement event
        FabricPlayerEvents.ADVANCEMENT.invoker().onPlayerAdvancement(this.owner, advancement.value());

        if (advancement.value().display().isPresent()) {
            AdvancementDisplay display = advancement.value().display().get();
            if (display.shouldAnnounceToChat() && getProgress(advancement).isDone()) {
                // Fire the advancement finished event
                FabricPlayerEvents.ADVANCEMENT_FINISHED.invoker().onPlayerAdvancementFinished(this.owner, advancement.value());
            }
        }
    }
}
