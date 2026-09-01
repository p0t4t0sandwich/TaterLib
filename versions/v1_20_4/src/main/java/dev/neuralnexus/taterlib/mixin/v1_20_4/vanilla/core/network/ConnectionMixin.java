/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_4.vanilla.core.network;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.network.Protocol;
import dev.neuralnexus.taterlib.network.ConnectionBridge;

import net.minecraft.network.Connection;
import net.minecraft.network.PacketListener;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

@AConstraint(
        mappings = Mappings.SEARGE,
        version = @Versions(min = MinecraftVersion.V17, max = MinecraftVersion.V20_4))
@Mixin(Connection.class)
public abstract class ConnectionMixin implements ConnectionBridge {
    // spotless:off
    @Shadow private SocketAddress address;
    @Shadow public abstract PacketListener shadow$getPacketListener();
    // spotless:on

    @Override
    public @NonNull InetSocketAddress bridge$address() {
        return (InetSocketAddress) this.address;
    }

    @Override
    public @Nullable Object bridge$getPacketListener() {
        return this.shadow$getPacketListener();
    }

    @AConstraint(
            mappings = Mappings.SEARGE,
            version = @Versions(min = MinecraftVersion.V20_2, max = MinecraftVersion.V20_4))
    @Override
    public Protocol bridge$protocol() {
        final PacketListener listener = this.shadow$getPacketListener();
        if (listener == null) {
            return null;
        }
        return Protocol.fromId(listener.protocol().id());
    }
}
