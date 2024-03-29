package dev.neuralnexus.taterlib.v1_20_2.vanilla.fabric.mixin.listeners.network;

import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.v1_20.vanilla.event.network.VanillaPluginMessageEvent;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.event.network.CustomPayloadPacketWrapper_1_20_2;

import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the plugin messages listener. */
@Mixin(ServerCommonPacketListenerImpl.class)
public class CustomPayloadMixin_1_20_2 {
    /**
     * Called when a custom payload packet is received. (often used for plugin messages)
     *
     * @param packet The packet.
     * @param ci The callback info.
     */
    @Inject(method = "handleCustomPayload", at = @At("HEAD"))
    public void onPluginMessage(ServerboundCustomPayloadPacket packet, CallbackInfo ci) {
        CustomPayloadPacketWrapper_1_20_2 wrapper = new CustomPayloadPacketWrapper_1_20_2(packet);
        NetworkEvents.PLUGIN_MESSAGE.invoke(new VanillaPluginMessageEvent(wrapper));

        ServerCommonPacketListenerImpl self = (ServerCommonPacketListenerImpl) (Object) this;
        NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                new VanillaPluginMessageEvent.Player(
                        wrapper, self.server.getPlayerList().getPlayer(self.getOwner().getId())));
    }
}
