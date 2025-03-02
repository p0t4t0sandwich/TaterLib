/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_2.forge.core.client;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.client.MinecraftBridge;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.network.VanillaCustomPacketPayload;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V20_2, max = MinecraftVersion.V20_4)
@Mixin(Minecraft.class)
public abstract class MinecraftMixin_sendPacket implements MinecraftBridge {
    @Shadow @Nullable public LocalPlayer player;

    @Override
    public void bridge$sendPacket(ResourceKey channel, byte[] data) {
        if (this.player == null) return;
        this.player.connection.send(
                new ServerboundCustomPayloadPacket(new VanillaCustomPacketPayload(channel, data)));
    }
}
