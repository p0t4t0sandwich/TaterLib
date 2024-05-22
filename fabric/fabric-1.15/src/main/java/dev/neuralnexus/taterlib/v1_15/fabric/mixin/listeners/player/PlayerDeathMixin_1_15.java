package dev.neuralnexus.taterlib.v1_15.fabric.mixin.listeners.player;

import dev.neuralnexus.taterlib.v1_15.fabric.event.api.FabricPlayerEvents;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player death listener. */
@Mixin(ServerPlayerEntity.class)
public class PlayerDeathMixin_1_15 {
    /**
     * Called when a player dies.
     *
     * @param source The source of the damage.
     * @param ci The callback info.
     */
    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onPlayerDeath(DamageSource source, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        FabricPlayerEvents.DEATH.invoker().onPlayerDeath(player, source);
    }
}
