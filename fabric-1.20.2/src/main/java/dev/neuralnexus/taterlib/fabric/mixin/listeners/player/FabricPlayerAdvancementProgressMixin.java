package dev.neuralnexus.taterlib.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.fabric.events.player.FabricPlayerEvents;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin for the player advancement progress listener.
 */
@Mixin(PlayerAdvancementTracker.class)
public abstract class FabricPlayerAdvancementProgressMixin {
    @Shadow private ServerPlayerEntity owner;

    /**
     * Called when a player completes an advancement.
     * @param advancement The advancement
     * @param criterionName The criterion name
     * @param cir Callback info
     */
    @Inject(method = "grantCriterion", at = @At("HEAD"))
    public void onPlayerAdvancementProgress(AdvancementEntry advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        FabricPlayerEvents.ADVANCEMENT_PROGRESS.invoker().onPlayerAdvancementProgress(this.owner, advancement, criterionName);
    }
}
