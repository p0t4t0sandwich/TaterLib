package dev.neuralnexus.taterlib.vanilla.mixin.listeners.pluginmessages;

import com.mojang.authlib.GameProfile;

import dev.neuralnexus.taterlib.event.api.PluginMessageEvents;
import dev.neuralnexus.taterlib.vanilla.event.pluginmessages.CustomPayloadPacketWrapper_1_20_2;
import dev.neuralnexus.taterlib.vanilla.event.pluginmessages.VanillaPluginMessageEvent;

import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerCommonPacketListenerImpl;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the plugin messages listener. */
@Mixin(ServerCommonPacketListenerImpl.class)
public abstract class PluginMessagesMixin_1_20_2 {
    @Final @Shadow protected MinecraftServer server;

    @Shadow
    protected abstract GameProfile playerProfile();

    @Inject(method = "handleCustomPayload", at = @At("HEAD"))
    public void onPluginMessage(ServerboundCustomPayloadPacket packet, CallbackInfo ci) {
        CustomPayloadPacketWrapper_1_20_2 wrapper = new CustomPayloadPacketWrapper_1_20_2(packet);
        PluginMessageEvents.PLUGIN_MESSAGE.invoke(new VanillaPluginMessageEvent(wrapper));
        PluginMessageEvents.PLAYER_PLUGIN_MESSAGE.invoke(
                new VanillaPluginMessageEvent.Player(
                        wrapper, server.getPlayerList().getPlayer(this.playerProfile().getId())));
    }
}
