/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_1.vanilla.core.network;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.network.Protocol;
import dev.neuralnexus.taterlib.network.ConnectionBridge;

import io.netty.util.AttributeKey;

import net.minecraft.network.Connection;
import net.minecraft.network.ConnectionProtocol;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@AConstraint(
        mappings = Mappings.SEARGE,
        version = @Versions(min = MinecraftVersion.V17, max = MinecraftVersion.V20_1))
@Mixin(Connection.class)
public abstract class ConnectionMixin implements ConnectionBridge {
    // spotless:off
    @Shadow @Final public static AttributeKey<ConnectionProtocol> ATTRIBUTE_PROTOCOL;
    // spotless:on

    @Override
    public Protocol bridge$protocol() {
        final Object listener = this.bridge$getPacketListener();
        if (listener == null) {
            return null;
        }
        return Protocol.fromLegacyId(this.bridge$channel().attr(ATTRIBUTE_PROTOCOL).get().getId());
    }
}
