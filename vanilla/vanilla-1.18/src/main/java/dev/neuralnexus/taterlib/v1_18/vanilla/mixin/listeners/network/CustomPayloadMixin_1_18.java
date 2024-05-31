package dev.neuralnexus.taterlib.v1_18.vanilla.mixin.listeners.network;

import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.network.CustomPayloadPacketWrapper;
import dev.neuralnexus.taterlib.v1_18.vanilla.event.network.VanillaPluginMessageEvent;

import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the plugin messages listener. */
@Mixin(ServerGamePacketListenerImpl.class)
public abstract class CustomPayloadMixin_1_18 {
    @Shadow
    public abstract ServerPlayer getPlayer();

    /**
     * Called when a custom payload packet is received. (often used for plugin messages)
     *
     * @param packet The packet.
     * @param ci The callback info.
     */
    @Inject(method = "handleCustomPayload", at = @At("HEAD"))
    public void onPluginMessage(ServerboundCustomPayloadPacket packet, CallbackInfo ci) {
        CustomPayloadPacketWrapper wrapper = new CustomPayloadPacketWrapper(packet);
        NetworkEvents.PLUGIN_MESSAGE.invoke(new VanillaPluginMessageEvent(wrapper));
        NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                new VanillaPluginMessageEvent.Player(wrapper, getPlayer()));
    }
}
