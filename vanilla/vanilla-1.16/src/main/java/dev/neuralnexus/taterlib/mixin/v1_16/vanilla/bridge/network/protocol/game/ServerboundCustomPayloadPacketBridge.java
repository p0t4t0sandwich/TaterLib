/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_16.vanilla.bridge.network.protocol.game;

import dev.neuralnexus.taterlib.mixin.v1_16.vanilla.accessors.network.protocol.game.ServerboundCustomPayloadPacketAccessor;

import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;

public interface ServerboundCustomPayloadPacketBridge {
    default ResourceLocation bridge$getIdentifier(ServerboundCustomPayloadPacket packet) {
        return ((ServerboundCustomPayloadPacketAccessor) packet).invoker$getIdentifier();
    }
}
