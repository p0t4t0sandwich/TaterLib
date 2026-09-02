/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_20_2.fabric.core.server.level;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.network.protocol.Packet;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.server.level.ServerPlayerBridge;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@AConstraint(
        mappings = Mappings.YARN_INTERMEDIARY,
        version = @Versions(min = MinecraftVersion.V20_2, max = MinecraftVersion.V20_4))
@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin_sendPacket implements ServerPlayerBridge {
    @Shadow public ServerGamePacketListenerImpl connection;

    @Override
    public int bridge$ping() {
        return this.connection.latency();
    }

    @Override
    public void bridge$sendPacket(final @NonNull Packet packet) {
        // TODO: Create accessor mixin
        throw new VersionFeatureNotSupportedException();
        // ((ConnectionBridge) this.connection.connection).bridge$send(packet);
    }
}
