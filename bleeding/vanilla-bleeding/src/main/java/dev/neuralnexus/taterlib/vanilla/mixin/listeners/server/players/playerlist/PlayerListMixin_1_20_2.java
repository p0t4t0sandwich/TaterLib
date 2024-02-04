package dev.neuralnexus.taterlib.vanilla.mixin.listeners.server.players.playerlist;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.vanilla.event.player.VanillaPlayerLoginEvent;
import dev.neuralnexus.taterlib.vanilla.event.player.VanillaPlayerRespawnEvent;

import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.players.PlayerList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Mixin for the player login listener. */
@Mixin(PlayerList.class)
public class PlayerListMixin_1_20_2 {
    /** Called when a player logs in. */
    @Inject(
            method = "placeNewPlayer",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/network/protocol/game/ClientboundPlayerAbilitiesPacket;<init>(Lnet/minecraft/world/entity/player/Abilities;)V"))
    private void onLogin(
            Connection connection,
            ServerPlayer player,
            CommonListenerCookie listenerCookie,
            CallbackInfo ci) {
        PlayerEvents.LOGIN.invoke(new VanillaPlayerLoginEvent(connection, player));
    }

    /** Called when a player respawns. */
    @Inject(method = "respawn", at = @At("HEAD"))
    public void onPlayerRespawn(
            ServerPlayer player, boolean alive, CallbackInfoReturnable<ServerPlayer> cir) {
        PlayerEvents.RESPAWN.invoke(new VanillaPlayerRespawnEvent(player, alive));
    }
}
