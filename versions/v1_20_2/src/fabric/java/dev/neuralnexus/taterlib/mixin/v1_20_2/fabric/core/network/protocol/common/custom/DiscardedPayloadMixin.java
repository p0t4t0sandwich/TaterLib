/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_2.fabric.core.network.protocol.common.custom;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.bridge.network.protocol.common.custom.DiscardedPayloadBridge;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.DiscardedPayload;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V20_2)
@Mixin(DiscardedPayload.class)
public abstract class DiscardedPayloadMixin implements DiscardedPayloadBridge {
    @Unique private FriendlyByteBuf taterapi$data;

    @Override
    public FriendlyByteBuf bridge$buf() {
        return this.taterapi$data;
    }

    @Override
    public void bridge$setBuf(FriendlyByteBuf data) {
        this.taterapi$data = data;
    }
}
