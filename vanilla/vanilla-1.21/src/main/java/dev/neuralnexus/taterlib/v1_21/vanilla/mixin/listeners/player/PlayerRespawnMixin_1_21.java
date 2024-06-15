package dev.neuralnexus.taterlib.v1_21.vanilla.mixin.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_21.vanilla.event.player.VanillaPlayerRespawnEvent;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Mixin for the player respawn listener. */
@Mixin(PlayerList.class)
public class PlayerRespawnMixin_1_21 {
    /** Called when a player respawns. */
    @Inject(method = "respawn", at = @At("HEAD"))
    public void onPlayerRespawn(
            ServerPlayer player,
            boolean alive,
            Entity.RemovalReason removalReason,
            CallbackInfoReturnable<ServerPlayer> cir) {
        PlayerEvents.RESPAWN.invoke(new VanillaPlayerRespawnEvent(player, alive));
    }
}