package dev.neuralnexus.taterlib.vanilla.mixin.listeners.server.network.servercommonpacketListenerImpl;

import dev.neuralnexus.taterlib.event.api.NetworkEvents;
import dev.neuralnexus.taterlib.vanilla.event.pluginmessages.CustomPayloadPacketWrapper_1_20_6;
import dev.neuralnexus.taterlib.vanilla.event.pluginmessages.VanillaPluginMessageEvent;

import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the plugin messages listener. */
@Mixin(ServerCommonPacketListenerImpl.class)
public abstract class ServerCommonPacketListenerImplMixin_1_20_6 {
    @Unique
    private ServerPlayer taterLib$getPlayer() {
        ServerCommonPacketListenerImpl self = (ServerCommonPacketListenerImpl) (Object) this;
        return self.server.getPlayerList().getPlayer(self.getOwner().getId());
    }

    /** Called when a plugin message is received. */
    @Inject(method = "handleCustomPayload", at = @At("HEAD"))
    public void onPluginMessage(ServerboundCustomPayloadPacket packet, CallbackInfo ci) {
        CustomPayloadPacketWrapper_1_20_6 wrapper = new CustomPayloadPacketWrapper_1_20_6(packet);
        NetworkEvents.PLUGIN_MESSAGE.invoke(new VanillaPluginMessageEvent(wrapper));
        NetworkEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                new VanillaPluginMessageEvent.Player(wrapper, this.taterLib$getPlayer()));
    }
}
