/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_7_10.fabric.listeners.player;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_7_10.fabric.event.player.FabricPlayerMessageEvent;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.client.C01PacketChatMessage;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V7_2, max = MinecraftVersion.V7_10)
@Mixin(NetHandlerPlayServer.class)
public abstract class PlayerMessageMixin {
    @Shadow public EntityPlayerMP playerEntity;

    /**
     * Called when a player sends a message.
     *
     * @param packet The packet.
     * @param ci The callback info.
     */
    @Inject(method = "processChatMessage", at = @At("HEAD"), cancellable = true)
    public void onPlayerMessage(C01PacketChatMessage packet, CallbackInfo ci) {
        if (packet.getMessage().startsWith("/")) return;
        PlayerEvents.MESSAGE.invoke(
                new FabricPlayerMessageEvent(this.playerEntity, packet.getMessage(), ci));
    }
}
