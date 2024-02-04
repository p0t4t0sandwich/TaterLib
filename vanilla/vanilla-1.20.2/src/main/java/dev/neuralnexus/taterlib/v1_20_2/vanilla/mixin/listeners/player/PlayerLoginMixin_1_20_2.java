package dev.neuralnexus.taterlib.v1_20_2.vanilla.mixin.listeners.player;

import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.player.VanillaPlayerLoginEvent;

import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.players.PlayerList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the player login listener. */
@Mixin(PlayerList.class)
public class PlayerLoginMixin_1_20_2 {
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
            CommonListenerCookie cookie,
            CallbackInfo ci) {
        PlayerEvents.LOGIN.invoke(new VanillaPlayerLoginEvent(connection, player));
    }
}
