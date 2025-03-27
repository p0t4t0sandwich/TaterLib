/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20_6.vanilla.bridge.network.protocol.common.custom;

import net.minecraft.network.FriendlyByteBuf;

public interface DiscardedPayloadBridge {
    FriendlyByteBuf bridge$buf();

    void bridge$setBuf(FriendlyByteBuf data);
}
