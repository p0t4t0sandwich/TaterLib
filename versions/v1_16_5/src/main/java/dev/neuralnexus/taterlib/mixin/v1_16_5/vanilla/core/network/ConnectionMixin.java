/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_16_5.vanilla.core.network;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.network.Protocol;
import dev.neuralnexus.taterlib.network.ConnectionBridge;

import io.netty.util.AttributeKey;

import net.minecraft.network.Connection;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.PacketListener;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

@AConstraint(
        mappings = Mappings.SEARGE,
        version = @Versions(min = MinecraftVersion.V7, max = MinecraftVersion.V16_5))
@Mixin(Connection.class)
public abstract class ConnectionMixin implements ConnectionBridge {
    // spotless:off
    @Shadow private SocketAddress address;
    @Shadow public abstract PacketListener shadow$getPacketListener();

    @AConstraint(version = @Versions(min = MinecraftVersion.V14, max = MinecraftVersion.V16_5))
    @Shadow @Final public static AttributeKey<ConnectionProtocol> ATTRIBUTE_PROTOCOL;
    // spotless:on

    @Override
    public @NonNull InetSocketAddress bridge$address() {
        return (InetSocketAddress) this.address;
    }

    @Override
    public @Nullable Object bridge$getPacketListener() {
        return this.shadow$getPacketListener();
    }

    @AConstraint(version = @Versions(min = MinecraftVersion.V14, max = MinecraftVersion.V16_5))
    @Override
    public Protocol bridge$protocol() {
        final Object listener = this.bridge$getPacketListener();
        if (listener == null) {
            return null;
        }
        return Protocol.fromLegacyId(this.bridge$channel().attr(ATTRIBUTE_PROTOCOL).get().getId());
    }
}
