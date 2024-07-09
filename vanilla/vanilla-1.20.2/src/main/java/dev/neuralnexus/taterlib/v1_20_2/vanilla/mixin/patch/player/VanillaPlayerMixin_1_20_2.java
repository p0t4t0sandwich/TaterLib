/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_20_2.vanilla.mixin.patch.player;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.util.ResourceKey;
import dev.neuralnexus.taterlib.v1_20.vanilla.player.VanillaPlayer;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.network.VanillaCustomPacketPayload_1_20_2;

import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/** Patch mixin for VanillaPlayer 1.20.2. */
@ReqMCVersion(min = MinecraftVersion.V1_20_2, max = MinecraftVersion.V1_20_6)
@Mixin(value = VanillaPlayer.class, remap = false)
public class VanillaPlayerMixin_1_20_2 {
    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.2
     */
    @Overwrite
    public void sendPluginMessage(ResourceKey channel, byte[] data) {
        ((ServerPlayer) ((VanillaPlayer) (Object) this).player())
                .connection.send(
                        new ClientboundCustomPayloadPacket(
                                new VanillaCustomPacketPayload_1_20_2(channel, data)));
    }

    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.2
     */
    @Overwrite
    public int ping() {
        return ((ServerPlayer) ((VanillaPlayer) (Object) this).player()).connection.latency();
    }
}
