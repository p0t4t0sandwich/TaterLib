package dev.neuralnexus.taterlib.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.fabric.event.api.FabricPlayerEvents;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player death listener. */
@Mixin(ServerPlayer.class)
public class FabricPlayerDeathMixin {
    /**
     * Called when a player dies.
     *
     * @param source The source of the damage.
     * @param ci The callback info.
     */
    @Inject(method = "die", at = @At("HEAD"))
    public void onPlayerDeath(DamageSource source, CallbackInfo ci) {
        Player player = (Player) (Object) this;
        FabricPlayerEvents.DEATH.invoker().onPlayerDeath(player, source);
    }
}
